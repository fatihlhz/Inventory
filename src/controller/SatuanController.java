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
import model.Jenis;
import model.Satuan;
import service.SQLiteConnection;

/**
 *
 * @author T450
 */
public class SatuanController {
    private static Inventory inventory;
    
    public void getData() {
        Connection conn = SQLiteConnection.connect();
        List<Satuan> dataList = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT * FROM satuan"; 

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
                Logger.getLogger(SatuanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inventory.satuanBarang.setTable(dataList);
    }
        
    public void getDataById(int id) {
        Connection conn = SQLiteConnection.connect();
        Jenis data = new Jenis();
            
        if (conn != null) {
            try {
                String query = "SELECT * FROM satuan \n" +
                                        "WHERE id_satuan = ?"; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                 preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                data.id = resultSet.getInt("id_satuan"); 
                data.nama = resultSet.getString("nama_satuan"); 

                resultSet.close();
                preparedStatement.close();
                conn.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(SatuanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addData(Satuan data) {
         Connection conn = SQLiteConnection.connect();

         if (conn != null) {
             try {
                String query = "INSERT INTO satuan (nama_satuan) VALUES (?)";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, data.nama);

               preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
                Logger.getLogger(SatuanController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    public void editData(Satuan data) {
         Connection conn = SQLiteConnection.connect();

         if (conn != null) {
             try {
                String query = "UPDATE satuan SET nama_satuan = ? \n" +
                                        "WHERE id_satuan = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

               preparedStatement.setString(1, data.nama);
               preparedStatement.setInt(2, data.id);

               preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
                Logger.getLogger(SatuanController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    public void deleteData(int id) {
         Connection conn = SQLiteConnection.connect();
         
         if (conn != null) {
             try {
                String query = "DELETE FROM satuan \n" +
                                        "WHERE id_satuan = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, id);

               preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(SatuanController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
}
