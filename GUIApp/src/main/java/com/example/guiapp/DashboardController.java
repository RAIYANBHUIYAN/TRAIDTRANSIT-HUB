package com.example.guiapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DashboardController extends Raiyan implements DatabaseLoader
{
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label reward;
    @FXML
    private Label liceense;
    curruserbd obj =new curruserbd();
    String storedUsername = obj.Active();
    @Override
    public void loadDatabaseLoader() throws SQLException
    {
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "SELECT * FROM signup JOIN sessio ON signup.username = sessio.per WHERE sessio.per = ?"))
        {


            preparedStatement.setString(1, storedUsername);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String retrievedName = resultSet.getString("username");
                String retrievedEmail = resultSet.getString("email");
                String retrievedReward = resultSet.getString("reward");
                String retrievedLicense = resultSet.getString("license");


                name.setText(retrievedName);
                System.out.println(retrievedName);
                email.setText(retrievedEmail);
                reward.setText(retrievedReward);
                liceense.setText(retrievedLicense);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        try {
            loadDatabaseLoader();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Parking(ActionEvent event)
    {
       CHANGESCENE(event ,"Parking.fxml");
    }
    public void RideSharing(ActionEvent event)
    {

       CHANGESCENE(event,"RideSharing.fxml");
    }
    public void Recharge(ActionEvent event)
    {
        CHANGESCENE(event,"EVRecharge.fxml");
    }
    public void LogOut(ActionEvent event)
    {
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(" delete from login.sessio;")) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CHANGESCENE(event,"hello-view.fxml");

    }

    public void Listing(ActionEvent event){


        CHANGESCENE(event,"Listing.fxml");
    }

}