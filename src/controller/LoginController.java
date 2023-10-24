/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jenis;
import model.User;
import service.SQLiteConnection;

/**
 *
 * @author T450
 */
public class LoginController {
    public User getUser(String username) {
        Connection conn = SQLiteConnection.connect();
        User data = new User();
            
        if (conn != null) {
            try {
                String query = "SELECT * FROM user \n" +
                                        "WHERE nama = ?"; 

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                 preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();

                data.nama = resultSet.getString("nama"); 
                data.password = resultSet.getString("password"); 
                data.roles = resultSet.getString("roles"); 

                resultSet.close();
                preparedStatement.close();
                conn.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return data;
    }
    
}
