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


public class ParkingController extends Raiyan implements DatabaseLoader
{

    @FXML
    private TableView<ObservableList<String>> table1;

    @FXML
    private TableColumn<ObservableList<String>, String> availspot;

    @FXML
    private TableColumn<ObservableList<String>, String> time;

    @FXML
    private TableColumn<ObservableList<String>, String> price;
    public void initialize() {
        availspot.setCellValueFactory(cellData ->
                Bindings.createStringBinding(() -> cellData.getValue().get(0), cellData.getValue()));
        time.setCellValueFactory(cellData ->
                Bindings.createStringBinding(() -> cellData.getValue().get(1), cellData.getValue()));
        price.setCellValueFactory(cellData ->
                Bindings.createStringBinding(() -> cellData.getValue().get(2), cellData.getValue()));
        loadDatabaseLoader();
    }
    @Override
    public void loadDatabaseLoader() {
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT spot , tim , price  FROM  login.parking;")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList(
                        resultSet.getString("spot"),
                        resultSet.getString("tim"),
                        resultSet.getString("price")
                );
                table1.getItems().add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void PreBook(ActionEvent event) { CHANGESCENE(event,"Waitlist.fxml"); }
    public void Back(ActionEvent event) { CHANGESCENE(event,"Dashboard.fxml"); }
    public void Confirm(ActionEvent event) { CHANGESCENE(event,"Congo.fxml");}


}