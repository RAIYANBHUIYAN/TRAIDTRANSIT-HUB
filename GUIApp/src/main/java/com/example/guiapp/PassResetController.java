package com.example.guiapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;
import java.util.Objects;

public class PassResetController extends Raiyan {

    @FXML
    private TextField UserField;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField cpass;

    @FXML
    private Label wrn;

    public static String u, p, cp;

    public class PasswordMismatchException extends Exception {
        public PasswordMismatchException(String message) {
            super(message);
        }
    }
    private void pass_set() throws PasswordMismatchException {
        if (!Objects.equals(p, cp)) {
            throw new PasswordMismatchException("INVALID PASSWORD.");
        }
    }

    private void user_exists() throws UserNotFoundException {
        try (Connection con = DB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM login.signup WHERE username = ?")) {
            preparedStatement.setString(1, u);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new UserNotFoundException("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Confirm(ActionEvent event) {
        u = UserField.getText();
        p = pass.getText();
        cp = cpass.getText();
        try {
            user_exists();
            pass_set();
            wrn.setVisible(false);
            try (Connection con = DB.getConnection();
                 PreparedStatement preparedStatement = con.prepareStatement("UPDATE login.signup SET pass = ? WHERE username = ?")) {
                preparedStatement.setString(1, p);
                preparedStatement.setString(2, u);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Reset done");
                } else {
                    System.out.println("Failed");
                }
                CHANGESCENE(event, "hello-view.fxml");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (UserNotFoundException | PasswordMismatchException e) {
            wrn.setVisible(true);
            wrn.setText(e.getMessage());

        }
    }
}
