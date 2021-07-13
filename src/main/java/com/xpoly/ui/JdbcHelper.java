/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpoly.ui;

/**
 *
 * @author Dell
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcHelper {

    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String user, pass;

    public static Connection ketNoi(String db) {
        try {
            Class.forName(driver);
            String dbURL = "jdbc:sqlserver://localhost;database=" + db;
            Connection conn = null;
            try {
                try {
                    // thay user va password o file config.txt
                    BufferedReader out = new BufferedReader(new FileReader("config.txt"));
                    try {
                        user = out.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        pass = out.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
                conn = DriverManager.getConnection(dbURL, user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            return conn;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
