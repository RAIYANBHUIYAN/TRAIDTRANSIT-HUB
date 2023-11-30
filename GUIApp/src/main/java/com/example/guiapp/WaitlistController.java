package com.example.guiapp;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WaitlistController extends Raiyan  implements DatabaseLoader
{
    @FXML
    private TextField spt;
    @FXML
    private TableView<ObservableList<String>> t3;

    @FXML
    private TableColumn<ObservableList<String>, String> as;

    @FXML
    private TableColumn<ObservableList<String>, String> pt;
    private String spot;
    curruserbd obj =new curruserbd();
    String storedUsername = obj.Active();

    public void initialize() {
        as.setCellValueFactory(cellData ->
                Bindings.createStringBinding(() -> cellData.getValue().get(0), cellData.getValue()));
        pt.setCellValueFactory(cellData ->
                Bindings.createStringBinding(() -> cellData.getValue().get(1), cellData.getValue()));
        executeUpdateQuery();
        loadDatabaseLoader();
    }
    @Override
    public void loadDatabaseLoader() {
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT spot,probt FROM login.parking WHERE stat is null AND u is null;")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList(
                        resultSet.getString("spot"),
                        resultSet.getString("probt")
                );
                t3.getItems().add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Confirm(ActionEvent event) {
        spot=spt.getText();



        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE login.parking SET u = ?, stat = 'booked' WHERE spot = ? AND stat IS NULL;")) {

            preparedStatement.setString(1, storedUsername);
            preparedStatement.setString(2, spot);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        CHANGESCENE(event, "Congo.fxml");

    }
    public void Back(ActionEvent event) { CHANGESCENE(event ,"Parking.fxml"); }
    private void executeUpdateQuery() {
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE login.parking SET ...")) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
