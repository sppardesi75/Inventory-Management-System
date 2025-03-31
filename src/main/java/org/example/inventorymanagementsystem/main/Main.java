package org.example.inventorymanagementsystem.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.inventorymanagementsystem.model.Inventory;
import org.example.inventorymanagementsystem.model.InHouse;
import org.example.inventorymanagementsystem.model.Outsourced;
import org.example.inventorymanagementsystem.model.Product;
import org.example.inventorymanagementsystem.view.LoginView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        preloadData();
        // Launch the login screen
        LoginView loginView = new LoginView();
        loginView.show();
    }

    private void preloadData() {
        // Preload parts with generic names
        Inventory.addPart(new InHouse(1, "Widget", 5.99, 10, 1, 100, 123));
        Inventory.addPart(new Outsourced(2, "Gadget", 8.99, 15, 1, 100, "Acme Corp"));

        // Preload at least 10 products; each product has at least one associated part.
        // Here we use generic product names.
        for (int i = 2; i <= 10; i++) {
            // Create a new part with a generic name.
            Inventory.addPart(new InHouse(i + 10, "Component " + i, 3.99 + i, 10 * i, 1, 100, 100 + i));
            // Create a new product with a generic name.
            Product prod = new Product(1000 + i, "Device " + i, 19.99 + i, 5 * i, 1, 50);
            // Associate the newly created part with this product.
            prod.addAssociatedPart(Inventory.getAllParts().get(Inventory.getAllParts().size() - 1));
            Inventory.addProduct(prod);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
