package org.example.inventorymanagementsystem.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.inventorymanagementsystem.model.Inventory;
import org.example.inventorymanagementsystem.model.Part;
import org.example.inventorymanagementsystem.model.Product;
import org.example.inventorymanagementsystem.view.MainScreenView;

public class MainScreenController {
    private MainScreenView mainView;

    public MainScreenController(MainScreenView mainView) {
        this.mainView = mainView;
        attachEvents();
    }

    private void attachEvents() {
        mainView.getAddPartButton().setOnAction(e -> openAddPart());
        mainView.getModifyPartButton().setOnAction(e -> openModifyPart());
        mainView.getDeletePartButton().setOnAction(e -> deletePart());
        mainView.getAddProductButton().setOnAction(e -> openAddProduct());
        mainView.getModifyProductButton().setOnAction(e -> openModifyProduct());
        mainView.getDeleteProductButton().setOnAction(e -> deleteProduct());
        mainView.getExitButton().setOnAction(e -> mainView.close());

        // Attach search functionality for parts and products.
        mainView.getSearchPartButton().setOnAction(e -> performPartSearch());
        mainView.getSearchProductButton().setOnAction(e -> performProductSearch());

        // Alternatively, trigger search when Enter is pressed in the search fields.
        mainView.getPartSearchField().setOnAction(e -> performPartSearch());
        mainView.getProductSearchField().setOnAction(e -> performProductSearch());
    }

    private void performPartSearch() {
        String query = mainView.getPartSearchField().getText().trim();
        ObservableList<Part> results = Inventory.searchPartByName(query);
        if (results.isEmpty()) {
            try {
                int id = Integer.parseInt(query);
                Part p = Inventory.searchPartByID(id);
                if (p != null) {
                    results.add(p);
                }
            } catch(NumberFormatException ex) {
                // Query is not numeric.
            }
        }
        if (results.isEmpty()) {
            showAlert("No parts found", "No matching parts found for \"" + query + "\".");
        }
        mainView.getPartsTable().setItems(results);
    }

    private void performProductSearch() {
        String query = mainView.getProductSearchField().getText().trim();
        ObservableList<Product> results = Inventory.searchProductByName(query);
        if (results.isEmpty()) {
            try {
                int id = Integer.parseInt(query);
                Product p = Inventory.searchProductByID(id);
                if (p != null) {
                    results.add(p);
                }
            } catch(NumberFormatException ex) {
                // Query is not numeric.
            }
        }
        if (results.isEmpty()) {
            showAlert("No products found", "No matching products found for \"" + query + "\".");
        }
        mainView.getProductsTable().setItems(results);
    }

    private void openAddPart() {
        new org.example.inventorymanagementsystem.view.AddPartView().showAndWait();
        mainView.getPartsTable().setItems(Inventory.getAllParts());
    }

    private void openModifyPart() {
        Part selected = mainView.getPartsTable().getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Part Selected", "Please select a part to modify.");
            return;
        }
        new org.example.inventorymanagementsystem.view.ModifyPartView(selected).showAndWait();
        mainView.getPartsTable().refresh();
    }

    private void deletePart() {
        Part selected = mainView.getPartsTable().getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Part Selected", "Please select a part to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected part?");
        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            Inventory.deletePart(selected);
        }
    }

    private void openAddProduct() {
        new org.example.inventorymanagementsystem.view.AddProductView().showAndWait();
        mainView.getProductsTable().setItems(Inventory.getAllProducts());
    }

    private void openModifyProduct() {
        Product selected = mainView.getProductsTable().getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Product Selected", "Please select a product to modify.");
            return;
        }
        new org.example.inventorymanagementsystem.view.ModifyProductView(selected).showAndWait();
        mainView.getProductsTable().refresh();
    }

    private void deleteProduct() {
        Product selected = mainView.getProductsTable().getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Product Selected", "Please select a product to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected product?");
        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            boolean deleted = Inventory.deleteProduct(selected);
            if (!deleted) {
                showAlert("Deletion Error", "Cannot delete product with associated parts.");
            }
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
