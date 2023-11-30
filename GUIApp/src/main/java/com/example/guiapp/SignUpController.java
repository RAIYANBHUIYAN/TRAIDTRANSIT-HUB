package com.example.guiapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController extends Raiyan
{

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField licenseField;

    @FXML
    private Label warn;

    private String user, pass, email, lice;

    private void valid() throws EUE , PE
    {
       if(user.contains(".")||email.contains(".") && !email.endsWith(".com"))
       {
           System.out.println("1");
           throw new EUE("INVALID EMAIL AND USER NAME");
       }
       if(pass.replaceAll("\\D", "").length() >4)
       {
           System.out.println("2");
           throw new PE("ENTER VALID PASSWORD");
       }
    }

    public void Confirm(ActionEvent event) throws IOException {
        user = usernameField.getText();
        pass = passwordField.getText();
        email = emailField.getText();
        lice = licenseField.getText();
        try {
            valid();
            warn.setVisible(false);
            try (Connection con = DB.getConnection();
                 PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO signup(username, email, license, pass ,reward) VALUES (?, ?, ?, ?,0)")) {
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, lice);
                preparedStatement.setString(4, pass);
                preparedStatement.executeUpdate();


            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            licenseField.setText("");
            CHANGESCENE(event, "hello-view.fxml");
        }
        catch(EUE|PE e)
        {
            warn.setVisible(true);
            warn.setText( e.getMessage());
        }

    }
}