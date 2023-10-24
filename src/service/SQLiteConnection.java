/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/service/database.db3"; 
            conn = DriverManager.getConnection(url);
            //System.out.println("Koneksi ke SQLite berhasil.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
