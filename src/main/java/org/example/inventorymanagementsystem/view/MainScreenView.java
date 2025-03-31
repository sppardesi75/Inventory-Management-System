package org.example.inventorymanagementsystem.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.inventorymanagementsystem.model.Inventory;
import org.example.inventorymanagementsystem.model.Part;
import org.example.inventorymanagementsystem.model.Product;
import org.example.inventorymanagementsystem.controller.MainScreenController;

public class MainScreenView extends Stage {
    private TableView<Part> partsTable;
    private TableView<Product> productsTable;
    private TextField partSearchField;
    private TextField productSearchField;
    private Button addPartButton, modifyPartButton, deletePartButton;
    private Button addProductButton, modifyProductButton, deleteProductButton;
    private Button exitButton;
    private Button searchPartButton;
    private Button searchProductButton;

    public MainScreenView() {
        setTitle("Inventory Management System - Main Screen");
        BorderPane root = new BorderPane();

        // Top: Application Title
        Label title = new Label("Inventory Management System");
        title.setStyle("-fx-font-size: 24px;");
        root.setTop(title);
        BorderPane.setMargin(title, new Insets(10));

        // Center: Two VBoxes for Parts and Products
        HBox centerBox = new HBox(20);
        centerBox.setPadding(new Insets(10));

        // Parts VBox
        VBox partsBox = new VBox(10);
        Label partsLabel = new Label("Parts");
        partSearchField = new TextField();
        partSearchField.setPromptText("Search Parts");
        searchPartButton = new Button("Search");
        partsTable = new TableView<>();
        TableColumn<Part, Integer> partIdCol = new TableColumn<>("ID");
        partIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        TableColumn<Part, String> partNameCol = new TableColumn<>("Name");
        partNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        TableColumn<Part, Integer> partStockCol = new TableColumn<>("Inventory");
        partStockCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStock()).asObject());
        TableColumn<Part, Double> partPriceCol = new TableColumn<>("Price");
        partPriceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()).asObject());
        partsTable.getColumns().addAll(partIdCol, partNameCol, partStockCol, partPriceCol);
        partsTable.setItems(Inventory.getAllParts());
        HBox partButtons = new HBox(10);
        addPartButton = new Button("Add");
        modifyPartButton = new Button("Modify");
        deletePartButton = new Button("Delete");
        partButtons.getChildren().addAll(addPartButton, modifyPartButton, deletePartButton);
        partsBox.getChildren().addAll(partsLabel, partSearchField, searchPartButton, partsTable, partButtons);

        // Products VBox
        VBox productsBox = new VBox(10);
        Label productsLabel = new Label("Products");
        productSearchField = new TextField();
        productSearchField.setPromptText("Search Products");
        searchProductButton = new Button("Search");
        productsTable = new TableView<>();
        TableColumn<Product, Integer> prodIdCol = new TableColumn<>("ID");
        prodIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        TableColumn<Product, String> prodNameCol = new TableColumn<>("Name");
        prodNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        TableColumn<Product, Integer> prodStockCol = new TableColumn<>("Inventory");
        prodStockCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStock()).asObject());
        TableColumn<Product, Double> prodPriceCol = new TableColumn<>("Price");
        prodPriceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()).asObject());
        productsTable.getColumns().addAll(prodIdCol, prodNameCol, prodStockCol, prodPriceCol);
        productsTable.setItems(Inventory.getAllProducts());
        HBox productButtons = new HBox(10);
        addProductButton = new Button("Add");
        modifyProductButton = new Button("Modify");
        deleteProductButton = new Button("Delete");
        productButtons.getChildren().addAll(addProductButton, modifyProductButton, deleteProductButton);
        productsBox.getChildren().addAll(productsLabel, productSearchField, searchProductButton, productsTable, productButtons);

        centerBox.getChildren().addAll(partsBox, productsBox);
        root.setCenter(centerBox);

        // Bottom: Exit Button
        exitButton = new Button("Exit");
        HBox bottomBox = new HBox(exitButton);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setStyle("-fx-alignment: center;");
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 800, 600);
        setScene(scene);

        // Instantiate controller so that event handlers get attached.
        new MainScreenController(this);
    }

    // Getters for controller usage
    public TableView<Part> getPartsTable() { return partsTable; }
    public TableView<Product> getProductsTable() { return productsTable; }
    public TextField getPartSearchField() { return partSearchField; }
    public TextField getProductSearchField() { return productSearchField; }
    public Button getAddPartButton() { return addPartButton; }
    public Button getModifyPartButton() { return modifyPartButton; }
    public Button getDeletePartButton() { return deletePartButton; }
    public Button getAddProductButton() { return addProductButton; }
    public Button getModifyProductButton() { return modifyProductButton; }
    public Button getDeleteProductButton() { return deleteProductButton; }
    public Button getExitButton() { return exitButton; }
    public Button getSearchPartButton() { return searchPartButton; }
    public Button getSearchProductButton() { return searchProductButton; }
}
