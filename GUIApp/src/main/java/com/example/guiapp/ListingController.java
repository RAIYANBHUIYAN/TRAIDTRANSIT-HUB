package com.example.guiapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


public class ListingController extends Raiyan
{

    @FXML
    private TextField loc;
    @FXML
    private  TextField ti;
    @FXML
    private TextField prc;
    @FXML
    private TextField ev;
    @FXML
    private TextField ep;
    @FXML
    private Label stat;


    private  String l , t , p ,e ,pe;
    public void Back(ActionEvent event) {CHANGESCENE(event,"Dashboard.fxml");}
    public void confirm(ActionEvent event)
    {
        stat.setVisible(false);
        l=loc.getText();
        t=ti.getText();
        p=prc.getText();
        e=ev.getText();
        pe=ep.getText();
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO login.parking(spot, tim, price, ev,evp,probt) VALUES (?, ?, ?, ? ,?,4)")) {

            preparedStatement.setString(1, l);
            preparedStatement.setString(2, t);
            preparedStatement.setString(3, p);
            preparedStatement.setString(4, e);
            preparedStatement.setString(5, pe);
            preparedStatement.executeUpdate();
            stat.setVisible(true);
            stat.setText("welcome to transit traid hub");
            loc.setText("");
            ti.setText("");
            prc.setText("");
            ev.setText("");
            ep.setText("");



        }
        catch (SQLIntegrityConstraintViolationException e) {

            stat.setVisible(true);
            stat.setText("Error: Spot already exist.");
            System.out.println("Duplicate spot value detected: " );
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}