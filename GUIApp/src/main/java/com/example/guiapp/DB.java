package com.example.guiapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    public static Connection databaseLink;

    public static Connection getConnection() {

        String databaseName = "login";
        String databaseUser = "root";
        String databasePass = "1234";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }


}