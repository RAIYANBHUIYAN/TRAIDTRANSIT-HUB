package com.example.guiapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CongoController extends Raiyan {

    @FXML
    private TextField feedback;

    @FXML
    private TextField rateing;

    private String feed, rat;
    curruserbd obj = new curruserbd();
    String user = obj.Active();


    public void Switch(ActionEvent event) {
        feed = feedback.getText();
        rat = rateing.getText();

        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE login.signup SET feedback =? ,rating =? WHERE username = ?;")) {
            preparedStatement.setString(1, feed);
            preparedStatement.setString(2, rat);
            preparedStatement.setString(3, user);
            preparedStatement.executeUpdate();


        } catch (SQLException e)
        {
            e.printStackTrace();


        }
        CHANGESCENE(event, "Dashboard.fxml");


    }
}