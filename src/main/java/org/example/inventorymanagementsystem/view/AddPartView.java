package org.example.inventorymanagementsystem.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.inventorymanagementsystem.controller.AddPartController;
import org.example.inventorymanagementsystem.model.Inventory;

public class AddPartView extends Stage {
    private TextField idField, nameField, invField, priceField, maxField, minField, machineOrCompanyField;
    private RadioButton inHouseRadio, outsourcedRadio;
    private Button saveButton, cancelButton;
    private Label machineOrCompanyLabel;

    public AddPartView() {
        setTitle("Add Part");
        initModality(Modality.APPLICATION_MODAL);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Title
        Label title = new Label("Add Part");
        grid.add(title, 0, 0, 2, 1);

        // ID (auto-generated)
        grid.add(new Label("ID:"), 0, 1);
        idField = new TextField();
        idField.setDisable(true);
        grid.add(idField, 1, 1);

        // Name
        grid.add(new Label("Name:"), 0, 2);
        nameField = new TextField();
        grid.add(nameField, 1, 2);

        // Inventory
        grid.add(new Label("Inventory:"), 0, 3);
        invField = new TextField();
        grid.add(invField, 1, 3);

        // Price
        grid.add(new Label("Price:"), 0, 4);
        priceField = new TextField();
        grid.add(priceField, 1, 4);

        // Max
        grid.add(new Label("Max:"), 0, 5);
        maxField = new TextField();
        grid.add(maxField, 1, 5);

        // Min
        grid.add(new Label("Min:"), 0, 6);
        minField = new TextField();
        grid.add(minField, 1, 6);

        // Radio Buttons for Part Type
        HBox radioBox = new HBox(10);
        inHouseRadio = new RadioButton("In-House");
        outsourcedRadio = new RadioButton("Outsourced");
        ToggleGroup tg = new ToggleGroup();
        inHouseRadio.setToggleGroup(tg);
        outsourcedRadio.setToggleGroup(tg);
        radioBox.getChildren().addAll(inHouseRadio, outsourcedRadio);
        grid.add(radioBox, 0, 7, 2, 1);

        // Machine ID / Company Name field
        machineOrCompanyLabel = new Label("Machine ID:");
        grid.add(machineOrCompanyLabel, 0, 8);
        machineOrCompanyField = new TextField();
        grid.add(machineOrCompanyField, 1, 8);

        // Save and Cancel buttons
        HBox buttonBox = new HBox(10);
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        buttonBox.getChildren().addAll(saveButton, cancelButton);
        grid.add(buttonBox, 0, 9, 2, 1);

        Scene scene = new Scene(grid, 350, 400);
        setScene(scene);

        // Auto-generate ID (for demo, using parts count + 1)
        int autoId = Inventory.getAllParts().size() + 1;
        idField.setText(String.valueOf(autoId));

        // Attach controller logic
        new AddPartController(this);
    }

    // Getters for controller access
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
