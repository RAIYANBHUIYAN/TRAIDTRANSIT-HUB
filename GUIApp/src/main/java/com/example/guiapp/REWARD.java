package com.example.guiapp;
import java.sql.SQLException;

public interface REWARD {
     void increasescore(int r) throws SQLException;

     default void increasescore(int r, String reason) throws SQLException {
          increasescore(r);
          System.out.println("Default Reason: " + reason);
     }
}
