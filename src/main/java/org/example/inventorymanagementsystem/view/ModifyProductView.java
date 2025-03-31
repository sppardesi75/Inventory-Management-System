package org.example.inventorymanagementsystem.view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.inventorymanagementsystem.model.Inventory;
import org.example.inventorymanagementsystem.model.Part;
import org.example.inventorymanagementsystem.model.Product;
import org.example.inventorymanagementsystem.controller.ModifyProductController;

public class ModifyProductView extends Stage {
    private TextField idField, nameField, invField, priceField, maxField, minField;
    private TableView<Part> availablePartsTable, associatedPartsTable;
    private TextField partSearchField;
    private Button searchButton, addPartButton, removePartButton;
    private Button saveButton, cancelButton;
    private Product product;

    public ModifyProductView(Product product) {
        this.product = product;
        setTitle("Modify Product");
        initModality(Modality.APPLICATION_MODAL);
        BorderPane root = new BorderPane();

        // Top: Title
        Label title = new Label("Modify Product");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        BorderPane.setMargin(title, new Insets(10));
        root.setTop(title);

        // Center: Form and Tables
        VBox centerBox = new VBox(15);
        centerBox.setPadding(new Insets(10));

        // Form GridPane for product details
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.add(new Label("ID:"), 0, 0);
        idField = new TextField();
        idField.setDisable(true);
        form.add(idField, 1, 0);
        form.add(new Label("Name:"), 0, 1);
        nameField = new TextField();
        form.add(nameField, 1, 1);
        form.add(new Label("Inventory:"), 0, 2);
        invField = new TextField();
        form.add(invField, 1, 2);
        form.add(new Label("Price:"), 0, 3);
        priceField = new TextField();
        form.add(priceField, 1, 3);
        form.add(new Label("Max:"), 0, 4);
        maxField = new TextField();
        form.add(maxField, 1, 4);
        form.add(new Label("Min:"), 0, 5);
        minField = new TextField();
        form.add(minField, 1, 5);

        // Pre-populate fields from the product
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        invField.setText(String.valueOf(product.getStock()));
        priceField.setText(String.valueOf(product.getPrice()));
        maxField.setText(String.valueOf(product.getMax()));
        minField.setText(String.valueOf(product.getMin()));

        // Available parts section
        Label availablePartsLabel = new Label("Available Parts");
        availablePartsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        partSearchField = new TextField();
        partSearchField.setPromptText("Search Parts");
        searchButton = new Button("Search");
        availablePartsTable = new TableView<>();
        availablePartsTable.setPrefWidth(550);
        availablePartsTable.setPrefHeight(250);
        TableColumn<Part, Integer> partIdCol = new TableColumn<>("ID");
        partIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        TableColumn<Part, String> partNameCol = new TableColumn<>("Name");
        partNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        availablePartsTable.getColumns().addAll(partIdCol, partNameCol);
        availablePartsTable.setItems(Inventory.getAllParts());

        addPartButton = new Button("Add Part");

        // Associated parts section
        Label associatedPartsLabel = new Label("Associated Parts");
        associatedPartsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        associatedPartsTable = new TableView<>();
        associatedPartsTable.setPrefWidth(550);
        associatedPartsTable.setPrefHeight(250);
        TableColumn<Part, Integer> assocIdCol = new TableColumn<>("ID");
        assocIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        TableColumn<Part, String> assocNameCol = new TableColumn<>("Name");
        assocNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        associatedPartsTable.getColumns().addAll(assocIdCol, assocNameCol);
        associatedPartsTable.setItems(FXCollections.observableArrayList(product.getAllAssociatedParts()));
        removePartButton = new Button("Remove Part");

        centerBox.getChildren().addAll(form,
                availablePartsLabel, partSearchField, searchButton, availablePartsTable, addPartButton,
                associatedPartsLabel, associatedPartsTable, removePartButton);
        root.setCenter(centerBox);

        // Bottom: Save and Cancel buttons
        HBox bottomBox = new HBox(15);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setStyle("-fx-alignment: center;");
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        bottomBox.getChildren().addAll(saveButton, cancelButton);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 700, 900);
        setScene(scene);

        // Instantiate controller so that event handlers are attached.
        new ModifyProductController(this, product);
    }

    // Getters for controller usage
    public TextField getIdField() { return idField; }
    public TextField getNameField() { return nameField; }
    public TextField getInvField() { return invField; }
    public TextField getPriceField() { return priceField; }
    public TextField getMaxField() { return maxField; }
    public TextField getMinField() { return minField; }
    public TableView<Part> getAvailablePartsTable() { return availablePartsTable; }
    public TableView<Part> getAssociatedPartsTable() { return associatedPartsTable; }
    public TextField getPartSearchField() { return partSearchField; }
    public Button getSearchButton() { return searchButton; }
    public Button getAddPartButton() { return addPartButton; }
    public Button getRemovePartButton() { return removePartButton; }
    public Button getSaveButton() { return saveButton; }
    public Button getCancelButton() { return cancelButton; }
}
