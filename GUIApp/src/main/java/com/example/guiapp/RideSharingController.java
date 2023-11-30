package com.example.guiapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RideSharingController extends Raiyan implements REWARD {
    @FXML
    private Label ride;
    @FXML
    private TextField fee;

    curruserbd obj = new curruserbd();
    String storedUsername = obj.Active();

    @Override
    public void increasescore(int r) throws SQLException {
        String desiredUsername = storedUsername;
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE login.signup SET reward = reward + ? WHERE username = ?")) {
            preparedStatement.setInt(1, r);
            preparedStatement.setString(2, desiredUsername);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void increasescore(int r, String re) throws SQLException {
        String desiredUsername = storedUsername;
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE login.signup SET fee = ? WHERE username = ?")) {
            preparedStatement.setString(1, re);
            preparedStatement.setString(2, desiredUsername);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void Voluntary(ActionEvent event) {
        try {
            ride.setVisible(true);
            ride.setText("YOU HAVE BEEN REWARDED");
            increasescore(100);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleEnterKey(ActionEvent event) {
        try {
            String monetaryValue = fee.getText();
            increasescore(0, monetaryValue);
            CHANGESCENE(event, "Congo.fxml");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void Monitary(ActionEvent event)
    {
        try {

            fee.setVisible(true);
            fee.setOnAction(e -> handleEnterKey(event));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void Back(ActionEvent event) {
        CHANGESCENE(event, "Dashboard.fxml");
    }
}
