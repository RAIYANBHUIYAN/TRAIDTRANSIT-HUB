package com.example.guiapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class curruserbd {

    public String Active()
    {
        String ac = null;

        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT per FROM login.sessio")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ac = resultSet.getString("per");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ac;
    }
    public String ActiveEmail()
    {
        String acemail = null;

        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("USE login; SELECT signup.email FROM signup JOIN sessio ON signup.username = sessio.per WHERE sessio.per = ?;")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    acemail = resultSet.getString("email");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return acemail;
    }


}
