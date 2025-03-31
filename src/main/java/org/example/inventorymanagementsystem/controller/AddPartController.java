package org.example.inventorymanagementsystem.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.inventorymanagementsystem.model.InHouse;
import org.example.inventorymanagementsystem.model.Outsourced;
import org.example.inventorymanagementsystem.model.Part;
import org.example.inventorymanagementsystem.model.Inventory;
import org.example.inventorymanagementsystem.view.AddPartView;

public class AddPartController {
    private AddPartView view;

    public AddPartController(AddPartView view) {
        this.view = view;
        attachEvents();
    }

    private void attachEvents() {
        view.getInHouseRadio().setOnAction(e -> view.getMachineOrCompanyLabel().setText("Machine ID:"));
        view.getOutsourcedRadio().setOnAction(e -> view.getMachineOrCompanyLabel().setText("Company Name:"));
        view.getSaveButton().setOnAction(e -> onSave());
        view.getCancelButton().setOnAction(e -> onCancel());
    }

    private void onSave() {
        try {
            int id = Integer.parseInt(view.getIdField().getText());
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

            Part newPart;
            if (view.getInHouseRadio().isSelected()) {
                int machineId = Integer.parseInt(view.getMachineOrCompanyField().getText());
                newPart = new InHouse(id, name, price, stock, min, max, machineId);
            } else if (view.getOutsourcedRadio().isSelected()) {
                String companyName = view.getMachineOrCompanyField().getText();
                newPart = new Outsourced(id, name, price, stock, min, max, companyName);
            } else {
                showError("Please select a part type.");
                return;
            }
            Inventory.addPart(newPart);
            view.close();
        } catch (NumberFormatException ex) {
            showError("Please enter valid numeric values.");
        }
    }

    private void onCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cancel adding part?");
        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            view.close();
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
    }
}
