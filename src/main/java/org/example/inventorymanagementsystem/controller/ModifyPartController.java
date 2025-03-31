package org.example.inventorymanagementsystem.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.inventorymanagementsystem.model.InHouse;
import org.example.inventorymanagementsystem.model.Outsourced;
import org.example.inventorymanagementsystem.model.Part;
import org.example.inventorymanagementsystem.view.ModifyPartView;

public class ModifyPartController {
    private ModifyPartView view;
    private Part part;

    public ModifyPartController(ModifyPartView view, Part part) {
        this.view = view;
        this.part = part;
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

            if (view.getInHouseRadio().isSelected()) {
                int machineId = Integer.parseInt(view.getMachineOrCompanyField().getText());
                if (part instanceof InHouse) {
                    ((InHouse) part).setMachine(machineId);
                } else {
                    part = new InHouse(part.getId(), name, price, stock, min, max, machineId);
                }
            } else if (view.getOutsourcedRadio().isSelected()) {
                String companyName = view.getMachineOrCompanyField().getText();
                if (part instanceof Outsourced) {
                    ((Outsourced) part).setCompanyName(companyName);
                } else {
                    part = new Outsourced(part.getId(), name, price, stock, min, max, companyName);
                }
            }

            // Update common fields
            part.setName(name);
            part.setStock(stock);
            part.setPrice(price);
            part.setMin(min);
            part.setMax(max);

            // For simplicity, we assume Inventory holds the same reference.
            view.close();
        } catch (NumberFormatException ex) {
            showError("Please enter valid numeric values.");
        }
    }

    private void onCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cancel modifying part?");
        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            view.close();
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
    }
}
