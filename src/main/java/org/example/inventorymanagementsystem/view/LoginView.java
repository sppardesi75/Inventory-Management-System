package org.example.inventorymanagementsystem.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.inventorymanagementsystem.controller.LoginController;

public class LoginView extends Stage {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button cancelButton;

    public LoginView() {
        setTitle("Inventory Management System - Login");
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        Label title = new Label("Login");
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        HBox buttonBox = new HBox(10);
        loginButton = new Button("Login");
        cancelButton = new Button("Cancel");
        buttonBox.getChildren().addAll(loginButton, cancelButton);
        root.getChildren().addAll(title, usernameField, passwordField, buttonBox);

        Scene scene = new Scene(root, 300, 200);
        setScene(scene);

        // Attach controller logic
        new LoginController(this);
    }

    // Getters for controller
    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }
    public Button getLoginButton() { return loginButton; }
    public Button getCancelButton() { return cancelButton; }
}
