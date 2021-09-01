package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    //Inventory is like 'fruitbowl class'

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();



    public static void addPart(Part newPart){
        allParts.add(newPart);

    }

     public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
     }

     public static void updatePart (int index, Part selectedPart){

     }

     public static void updateProduct (int index, Product selectedProduct){

     }


    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
     }


}
