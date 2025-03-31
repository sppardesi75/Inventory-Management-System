package org.example.inventorymanagementsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part searchPartByID(int partId) {
        for (Part p : allParts) {
            if (p.getId() == partId) return p;
        }
        return null;
    }

    public static Product searchProductByID(int productId) {
        for (Product p : allProducts) {
            if (p.getId() == productId) return p;
        }
        return null;
    }

    public static ObservableList<Part> searchPartByName(String name) {
        ObservableList<Part> results = FXCollections.observableArrayList();
        for (Part p : allParts) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(p);
            }
        }
        return results;
    }

    public static ObservableList<Product> searchProductByName(String name) {
        ObservableList<Product> results = FXCollections.observableArrayList();
        for (Product p : allProducts) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(p);
            }
        }
        return results;
    }

    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct.getAllAssociatedParts().isEmpty()) {
            return allProducts.remove(selectedProduct);
        }
        return false;
    }

    public static ObservableList<Part> getAllParts() { return allParts; }
    public static ObservableList<Product> getAllProducts() { return allProducts; }
}
