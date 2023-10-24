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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Barang;
import model.Transaksi;
import service.SQLiteConnection;

/**
 *
 * @author T450
 */
public class BarangKeluarController {
    private static Inventory inventory;
    
    public void getData() {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Connection conn = SQLiteConnection.connect();
        List<Transaksi> dataList = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT m.*, b.nama_barang \n" +
                                        "FROM keluar m \n" +
                                        "INNER JOIN barang b ON m.id_barang = b.id_barang \n" +
                                        "ORDER BY tanggal_keluar DESC"; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Transaksi data = new Transaksi();
                    Date inputDate = new Date();

                    try {
                        inputDate = inputDateFormat.parse(resultSet.getString("tanggal_keluar"));
                    } catch (ParseException e) {
                        System.err.println("Error parsing date: " + e.getMessage());
                    }

                   data.id = resultSet.getInt("id_keluar"); 
                   data.idBarang = resultSet.getInt("id_barang"); 
                   data.namaBarang = resultSet.getString("nama_barang"); 
                   data.tanggal = outputDateFormat.format(inputDate);
                   data.jumlah = resultSet.getInt("jumlah_keluar"); 

                   dataList.add(data);
               }

                resultSet.close();
                preparedStatement.close();
                conn.close();
            } catch (SQLException ex ) {
                Logger.getLogger(BarangKeluarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inventory.barangKeluar.setTable(dataList);
    }
        
    public void addData(Transaksi data) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyy");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Connection conn = SQLiteConnection.connect();
        Date inputDate = new Date();
                
        try {
            inputDate = inputDateFormat.parse(data.tanggal);
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
                
        if (conn != null) {
            try {
                String query = "INSERT INTO keluar (id_barang, jumlah_keluar, tanggal_keluar) VALUES (?, ?, ?)";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, data.idBarang);
                preparedStatement.setInt(2, data.jumlah);
                preparedStatement.setString(3, outputDateFormat.format(inputDate));

                preparedStatement.executeUpdate();
            } catch (SQLException ex ) {
                Logger.getLogger(BarangKeluarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void updateJumlah(int id, int jumlah, boolean add) {
         Connection conn = SQLiteConnection.connect();
         
         if (conn != null) {
             try {
                String query;
                
                if(add) {
                    query =  "UPDATE barang SET stok = stok - ? \n" +
                                  "WHERE id_barang = ?";
                } else {
                    query =  "UPDATE barang SET stok = stok + ? \n" +
                                  "WHERE id_barang = ?";
                }

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, jumlah);
                preparedStatement.setInt(2, id);

               preparedStatement.executeUpdate();
               
            } catch (SQLException ex ) {
                Logger.getLogger(BarangKeluarController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
     
    public void deleteData(int id) {
         Connection conn = SQLiteConnection.connect();
         
         if (conn != null) {
             try {
                String query = "DELETE FROM keluar \n" +
                                        "WHERE id_keluar = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, id);

               preparedStatement.executeUpdate();
            } catch (SQLException ex ) {
                Logger.getLogger(BarangKeluarController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    public void getDataBarang() {
        Connection conn = SQLiteConnection.connect();
        List<Barang> dataList = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT * FROM barang "; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                   Barang data = new Barang();

                   data.id = resultSet.getInt("id_barang"); 
                   data.nama = resultSet.getString("nama_barang"); 

                   dataList.add(data);
               }

                resultSet.close();
                preparedStatement.close();
                conn.close();
            } catch (SQLException ex ) {
                Logger.getLogger(BarangKeluarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inventory.formBrgKeluar.setBarang(dataList);
    }
}
