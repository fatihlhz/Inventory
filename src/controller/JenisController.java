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
import service.SQLiteConnection;

/**
 *
 * @author T450
 */
public class JenisController {
    private static Inventory inventory;
    
    public void getData() {
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
                Logger.getLogger(JenisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inventory.jenisBarang.setTable(dataList);
    }
        
    public void getDataById(int id) {
        Connection conn = SQLiteConnection.connect();
        Jenis data = new Jenis();
            
        if (conn != null) {
            try {
                String query = "SELECT * FROM jenis \n" +
                                        "WHERE id_jenis = ?"; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                 preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                data.id = resultSet.getInt("id_jenis"); 
                data.nama = resultSet.getString("nama_jenis"); 

                resultSet.close();
                preparedStatement.close();
                conn.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(JenisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addData(Jenis data) {
         Connection conn = SQLiteConnection.connect();

         if (conn != null) {
             try {
                String query = "INSERT INTO jenis (nama_jenis) VALUES (?)";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, data.nama);

               preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(JenisController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    public void editData(Jenis data) {
         Connection conn = SQLiteConnection.connect();

         if (conn != null) {
             try {
                String query = "UPDATE jenis SET nama_jenis = ? \n" +
                                        "WHERE id_jenis = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

               preparedStatement.setString(1, data.nama);
               preparedStatement.setInt(2, data.id);

               preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(JenisController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    public void deleteData(int id) {
         Connection conn = SQLiteConnection.connect();
         
         if (conn != null) {
             try {
                String query = "DELETE FROM jenis \n" +
                                        "WHERE id_jenis = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, id);

               preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(JenisController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
}
