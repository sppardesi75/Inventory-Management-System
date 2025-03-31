package org.example.inventorymanagementsystem.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.inventorymanagementsystem.model.Part;
import org.example.inventorymanagementsystem.model.InHouse;
import org.example.inventorymanagementsystem.model.Outsourced;
import org.example.inventorymanagementsystem.controller.ModifyPartController;

public class ModifyPartView extends Stage {
    private TextField idField, nameField, invField, priceField, maxField, minField, machineOrCompanyField;
    private RadioButton inHouseRadio, outsourcedRadio;
    private Button saveButton, cancelButton;
    private Label machineOrCompanyLabel;
    private Part part;

    public ModifyPartView(Part part) {
        this.part = part;
        setTitle("Modify Part");
        initModality(Modality.APPLICATION_MODAL);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label title = new Label("Modify Part");
        grid.add(title, 0, 0, 2, 1);

        grid.add(new Label("ID:"), 0, 1);
        idField = new TextField();
        idField.setDisable(true);
        grid.add(idField, 1, 1);

        grid.add(new Label("Name:"), 0, 2);
        nameField = new TextField();
        grid.add(nameField, 1, 2);

        grid.add(new Label("Inventory:"), 0, 3);
        invField = new TextField();
        grid.add(invField, 1, 3);

        grid.add(new Label("Price:"), 0, 4);
        priceField = new TextField();
        grid.add(priceField, 1, 4);

        grid.add(new Label("Max:"), 0, 5);
        maxField = new TextField();
        grid.add(maxField, 1, 5);

        grid.add(new Label("Min:"), 0, 6);
        minField = new TextField();
        grid.add(minField, 1, 6);

        HBox radioBox = new HBox(10);
        inHouseRadio = new RadioButton("In-House");
        outsourcedRadio = new RadioButton("Outsourced");
        ToggleGroup tg = new ToggleGroup();
        inHouseRadio.setToggleGroup(tg);
        outsourcedRadio.setToggleGroup(tg);
        radioBox.getChildren().addAll(inHouseRadio, outsourcedRadio);
        grid.add(radioBox, 0, 7, 2, 1);

        machineOrCompanyLabel = new Label("Machine ID:");
        grid.add(machineOrCompanyLabel, 0, 8);
        machineOrCompanyField = new TextField();
        grid.add(machineOrCompanyField, 1, 8);

        HBox buttonBox = new HBox(10);
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        buttonBox.getChildren().addAll(saveButton, cancelButton);
        grid.add(buttonBox, 0, 9, 2, 1);

        Scene scene = new Scene(grid, 350, 400);
        setScene(scene);

        // Pre-populate fields
        idField.setText(String.valueOf(part.getId()));
        nameField.setText(part.getName());
        invField.setText(String.valueOf(part.getStock()));
        priceField.setText(String.valueOf(part.getPrice()));
        maxField.setText(String.valueOf(part.getMax()));
        minField.setText(String.valueOf(part.getMin()));

        if (part instanceof InHouse) {
            inHouseRadio.setSelected(true);
            machineOrCompanyField.setText(String.valueOf(((InHouse) part).getMachine()));
            machineOrCompanyLabel.setText("Machine ID:");
        } else if (part instanceof Outsourced) {
            outsourcedRadio.setSelected(true);
            machineOrCompanyField.setText(((Outsourced) part).getCompanyName());
            machineOrCompanyLabel.setText("Company Name:");
        }

        new ModifyPartController(this, part);
    }

    // Getters for controller
    public TextField getIdField() { return idField; }
    public TextField getNameField() { return nameField; }
    public TextField getInvField() { return invField; }
    public TextField getPriceField() { return priceField; }
    public TextField getMaxField() { return maxField; }
    public TextField getMinField() { return minField; }
    public TextField getMachineOrCompanyField() { return machineOrCompanyField; }
    public RadioButton getInHouseRadio() { return inHouseRadio; }
    public RadioButton getOutsourcedRadio() { return outsourcedRadio; }
    public Button getSaveButton() { return saveButton; }
    public Button getCancelButton() { return cancelButton; }
    public Label getMachineOrCompanyLabel() { return machineOrCompanyLabel; }
}
