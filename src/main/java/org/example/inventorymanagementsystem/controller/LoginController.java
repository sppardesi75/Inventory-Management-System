package org.example.inventorymanagementsystem.controller;

import javafx.scene.control.Alert;
import org.example.inventorymanagementsystem.view.LoginView;
import org.example.inventorymanagementsystem.view.MainScreenView;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        attachEvents();
    }

    private void attachEvents() {
        loginView.getLoginButton().setOnAction(e -> onLogin());
        loginView.getCancelButton().setOnAction(e -> onCancel());
    }

    private void onLogin() {
        String username = loginView.getUsernameField().getText();
        String password = loginView.getPasswordField().getText();
        // Simple authentication (for demo use "admin"/"admin")
        if (username.equals("admin") && password.equals("1234")) {
            MainScreenView mainView = new MainScreenView();
            mainView.show();
            loginView.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Please enter a valid username and password.");
            alert.showAndWait();
        }
    }

    private void onCancel() {
        loginView.close();
    }
}
