package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    //Inventory is like 'fruitbowl class'
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


   static{
        addTestData();
    }

    public static void addTestData(){
        InHouse a = new InHouse(1,"test",10,100,1,2,8);
        Inventory.addPart(a);
    }

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
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }


    public static ObservableList<Product> getAllProducts(){
        return allProducts;
     }

    public static Part lookupPart (int partId){
        Part foundPart = null;
        for (Part part : allParts){
            if(part.getId() == partId){
                foundPart = part;
            }
        }
        return foundPart;
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

