package com.example.guiapp;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class EVController extends Raiyan implements DatabaseLoader
{

    @FXML
    private TableView<ObservableList<String>> evtable;

    @FXML
    private TableColumn<ObservableList<String>, String> aspc;

    @FXML
    private TableColumn<ObservableList<String>, String> ep;

    public void initialize() {

        aspc.setCellValueFactory(cellData ->
                Bindings.createStringBinding(() -> cellData.getValue().get(0), cellData.getValue()));
        ep.setCellValueFactory(cellData ->
                Bindings.createStringBinding(() -> cellData.getValue().get(1), cellData.getValue()));
        loadDatabaseLoader();
    }

    @Override
    public void loadDatabaseLoader() {
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT spot, price FROM login.parking WHERE ev = 'y'")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList(
                        resultSet.getString("spot"),
                        resultSet.getString("price")
                );
                evtable.getItems().add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Confirm(ActionEvent e) {
        CHANGESCENE(e,"congo.fxml");
    }
    public void Back(ActionEvent e)  {
       CHANGESCENE(e,"Dashboard.fxml");
    }
}