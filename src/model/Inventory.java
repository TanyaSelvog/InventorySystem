package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    //Inventory is like 'fruitbowl class'
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    //static id
    private static int id = 0;

    public static int addID() {
        id = id + 1;
        return id;
    }

    static {
        addTestData();
    }

    public static void addTestData() {
        InHouse a = new InHouse(1, "test", 10, 100, 1, 2, 8);
        Inventory.addPart(a);
        InHouse b = new InHouse(3, "test2", 3, 8, 8, 9, 9);
        Inventory.addPart(b);
        InHouse c = new InHouse(33, "abc", 3, 2, 3, 3, 3);
        Inventory.addPart(c);
        Product d = new Product(3, "test23", 3, 8, 99, 9);
        Inventory.addProduct(d);
    }

    public static void addPart(Part newPart) {
        allParts.add(newPart);

    }


    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }


    public static void updatePart(int index, Part selectedPart) {

    }

    public static void updateProduct(int index, Product selectedProduct) {

    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


    public static Product lookupProduct(int productId) {
        Product foundProduct = null;
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                foundProduct = product;
            }
        }
        return foundProduct;
    }

    public static Part lookupPart(int partId) {
        Part foundPart = null;
        for (Part part : allParts) {
            if (part.getId() == partId) {
                foundPart = part;
            }
        }
        return foundPart;
    }

    public static ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> foundProductsList = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                foundProductsList.add(product);
            }
        }
        return foundProductsList;
    }

    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> foundPartsList = FXCollections.observableArrayList();

        for (Part partB : allParts) {
            if (partB.getName().equals(partName)) {
                foundPartsList.add(partB);
            }

        }

        return foundPartsList;
    }


}


