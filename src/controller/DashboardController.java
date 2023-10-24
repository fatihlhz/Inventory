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
import service.SQLiteConnection;
import view.Dashboard;

/**
 *
 * @author T450
 */
public class DashboardController {
     private static Inventory inventory;
    
    public void getData()  {
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
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inventory.dashboard.setTable(dataList);
    }
    
    public String getBarang() {
        Connection conn = SQLiteConnection.connect();
        String result = "";
        
        if (conn != null) {
            try {
                String query = "SELECT COUNT(*) AS jumlah FROM barang"; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                result = resultSet.getString("jumlah");

                resultSet.close();
                preparedStatement.close();
                conn.close();
                 
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    
    public String getMasuk() {
        Connection conn = SQLiteConnection.connect();
        String result = "";

        if (conn != null) {
            try {
                String query = "SELECT COUNT(*) AS jumlah FROM masuk"; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                result = resultSet.getString("jumlah");

                resultSet.close();
                preparedStatement.close();
                conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
     
    public String getKeluar() {
       Connection conn = SQLiteConnection.connect();
       String result = "";

        if (conn != null) {
            try {
                String query = "SELECT COUNT(*) AS jumlah FROM keluar"; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                result = resultSet.getString("jumlah");

                resultSet.close();
                preparedStatement.close();
                conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
     
    public String getUser() {
        Connection conn = SQLiteConnection.connect();
        String result = "";
        
        if (conn != null) {
            try {
                String query = "SELECT COUNT(*) AS jumlah FROM user"; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                result = resultSet.getString("jumlah");

                resultSet.close();
                preparedStatement.close();
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    
}
