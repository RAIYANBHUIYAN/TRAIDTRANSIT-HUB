package com.example.guiapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserSession {
    private static String username;

    public static String getUsername() {
        return username;
    }

public static void setUsername(String username) {
      UserSession.username = username;
      try (Connection con = DB.getConnection();
           PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO login.sessio(per) VALUES (?)")) {

          preparedStatement.setString(1, username);
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
         e.printStackTrace();
        }
 }
}
