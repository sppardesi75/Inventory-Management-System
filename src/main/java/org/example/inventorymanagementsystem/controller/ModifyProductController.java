package org.example.inventorymanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.inventorymanagementsystem.model.Inventory;
import org.example.inventorymanagementsystem.model.Part;
import org.example.inventorymanagementsystem.model.Product;
import org.example.inventorymanagementsystem.view.ModifyProductView;

public class ModifyProductController {
    private ModifyProductView view;
    private Product product;
    private ObservableList<Part> associatedParts;

    public ModifyProductController(ModifyProductView view, Product product) {
        this.view = view;
        this.product = product;
        associatedParts = FXCollections.observableArrayList(product.getAllAssociatedParts());
        attachEvents();
    }

    private void attachEvents() {
        // Attach search functionality for available parts.
        view.getSearchButton().setOnAction(e -> performSearch());
        view.getPartSearchField().setOnAction(e -> performSearch());

        view.getAddPartButton().setOnAction(e -> addPart());
        view.getRemovePartButton().setOnAction(e -> removePart());
        view.getSaveButton().setOnAction(e -> onSave());
        view.getCancelButton().setOnAction(e -> onCancel());
    }

    private void performSearch() {
        String query = view.getPartSearchField().getText().trim();
        ObservableList<Part> results = Inventory.searchPartByName(query);
        if (results.isEmpty()) {
            try {
                int id = Integer.parseInt(query);
                Part p = Inventory.searchPartByID(id);
                if (p != null) {
                    results.add(p);
                }
            } catch (NumberFormatException ex) {
                // Query is not numeric.
            }
        }
        if (results.isEmpty()) {
            showAlert("No parts found", "No matching parts found for \"" + query + "\".");
        }
        view.getAvailablePartsTable().setItems(results);
    }

    private void addPart() {
        Part selected = view.getAvailablePartsTable().getSelectionModel().getSelectedItem();
        if (selected != null && !associatedParts.contains(selected)) {
            associatedParts.add(selected);
            view.getAssociatedPartsTable().setItems(associatedParts);
        }
    }

    private void removePart() {
        Part selected = view.getAssociatedPartsTable().getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Remove selected part?");
            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                associatedParts.remove(selected);
            }
        }
    }

    private void onSave() {
        try {
            String name = view.getNameField().getText();
            int stock = Integer.parseInt(view.getInvField().getText());
            double price = Double.parseDouble(view.getPriceField().getText());
            int max = Integer.parseInt(view.getMaxField().getText());
            int min = Integer.parseInt(view.getMinField().getText());

            if (min > max) {
                showError("Min cannot be greater than Max.");
                return;
            }
            if (stock < min || stock > max) {
                showError("Inventory must be between Min and Max.");
                return;
            }
            if (associatedParts.isEmpty()) {
                showError("Product must have at least one associated part.");
                return;
            }
            double totalCost = associatedParts.stream().mapToDouble(Part::getPrice).sum();
            if (price < totalCost) {
                showError("Product price cannot be less than total cost of associated parts.");
                return;
            }

            product.setName(name);
            product.setStock(stock);
            product.setPrice(price);
            product.setMin(min);
            product.setMax(max);
            product.getAllAssociatedParts().clear();
            for (Part p : associatedParts) {
                product.addAssociatedPart(p);
            }
            view.close();
        } catch (NumberFormatException ex) {
            showError("Please enter valid numeric values.");
        }
    }

    private void onCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cancel modifying product?");
        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            view.close();
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
