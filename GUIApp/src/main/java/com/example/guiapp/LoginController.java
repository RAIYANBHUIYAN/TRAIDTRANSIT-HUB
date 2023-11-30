package com.example.guiapp;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class LoginController extends Raiyan
{
    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;
    public  boolean validateSignIn(String username, String password)
    {

        Connection connection = DB.getConnection();
        String query ="SELECT * FROM login.signup WHERE username=? AND pass=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String username;
    public static String password;
    @FXML
    public void Switch(ActionEvent event)
    {
        username = usernameField.getText();

        password = passwordField.getText();
        if (validateSignIn(username, password))
        {

            UserSession.setUsername(username);

           CHANGESCENE(event, "Dashboard.fxml");

        }
        else{

          CHANGESCENE(event ,"hello-view.fxml");
        }
    }
    public void Reset(ActionEvent event)
    {

       CHANGESCENE(event,"PasswordReset.fxml");

    }
    public void SignUp(ActionEvent event)
    {
       CHANGESCENE(event,"Signup.fxml");
    }


}