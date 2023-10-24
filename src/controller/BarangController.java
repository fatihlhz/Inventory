/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import inventory.Inventory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Barang;
import model.Jenis;
import model.Satuan;
import service.SQLiteConnection;

/**
 *
 * @author T450
 */
public class BarangController {
    private static Inventory inventory;
    
    public void getData() {
        Connection conn = SQLiteConnection.connect();
        List<Barang> dataList = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT b.*, j.nama_jenis, s.nama_satuan\n" +
                                        "FROM barang b \n" +
                                        "INNER JOIN jenis j ON b.id_jenis = j.id_jenis \n" +
                                        "INNER JOIN satuan s ON b.id_satuan = s.id_satuan "; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                   Barang data = new Barang();

                   data.id = resultSet.getInt("id_barang"); 
                   data.nama = resultSet.getString("nama_barang"); 
                   data.stok = resultSet.getInt("stok"); 
                   data.jenis = resultSet.getString("nama_jenis"); 
                   data.satuan = resultSet.getString("nama_satuan"); ;

                   dataList.add(data);
               }

                resultSet.close();
                preparedStatement.close();
                conn.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inventory.dataBarang.setTable(dataList);
    }
    
    public void getDataById(int id) {
        Connection conn = SQLiteConnection.connect();
        Barang data = new Barang();
            
        if (conn != null) {
            try {
                String query = "SELECT b.*, j.nama_jenis, s.nama_satuan\n" +
                                        "FROM barang b \n" +
                                        "INNER JOIN jenis j ON b.id_jenis = j.id_jenis \n" +
                                        "INNER JOIN satuan s ON b.id_satuan = s.id_satuan \n" +
                                        "WHERE b.id_barang = ?"; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                data.id = resultSet.getInt("id_barang"); 
                data.nama = resultSet.getString("nama_barang"); 
                data.stok = resultSet.getInt("stok"); 
                data.idJenis = resultSet.getInt("id_jenis");
                data.jenis = resultSet.getString("nama_jenis"); 
                data.satuan = resultSet.getString("nama_satuan"); 
                data.idSatuan = resultSet.getInt("id_satuan"); 

                resultSet.close();
                preparedStatement.close();
                conn.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inventory.formBarang.setForm(data);
    }
    
    public void addData(Barang data) {
         Connection conn = SQLiteConnection.connect();
         
         if (conn != null) {
             try {
                String query = "INSERT INTO barang (nama_barang, id_jenis, stok, id_satuan) VALUES (?, ?, ?, ?)";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, data.nama);
                preparedStatement.setInt(2, data.idJenis);
                preparedStatement.setInt(3, data.stok);
                preparedStatement.setInt(4, data.idSatuan);

               preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    public void editData(Barang data) {
         Connection conn = SQLiteConnection.connect();
         
         if (conn != null) {
            try {
                String query = "UPDATE barang SET nama_barang = ?, id_jenis = ?, stok = ?, id_satuan = ? \n" +
                                        "WHERE id_barang = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, data.nama);
                preparedStatement.setInt(2, data.idJenis);
                preparedStatement.setInt(3, data.stok);
                preparedStatement.setInt(4, data.idSatuan);
                preparedStatement.setInt(5, data.id);

               preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    public void deleteData(int id) {
         Connection conn = SQLiteConnection.connect();
         
         if (conn != null) {
             try {
                String query = "DELETE FROM barang \n" +
                                        "WHERE id_barang = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, id);

               preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    public void getDataJenis() {
        Connection conn = SQLiteConnection.connect();
        List<Jenis> dataList = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT * FROM jenis "; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                   Jenis data = new Jenis();

                   data.id = resultSet.getInt("id_jenis"); 
                   data.nama = resultSet.getString("nama_jenis"); 

                   dataList.add(data);
               }

                resultSet.close();
                preparedStatement.close();
                conn.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inventory.formBarang.setJenis(dataList);
    }
    
    public void getDataSatuan() {
        Connection conn = SQLiteConnection.connect();
        List<Satuan> dataList = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT * FROM satuan "; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                   Satuan data = new Satuan();

                   data.id = resultSet.getInt("id_satuan"); 
                   data.nama = resultSet.getString("nama_satuan"); 

                   dataList.add(data);
               }

                resultSet.close();
                preparedStatement.close();
                conn.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inventory.formBarang.setSatuan(dataList);
    }
}
