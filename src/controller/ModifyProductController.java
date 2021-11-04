package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is a controller class that provides logic for the modify product screen.
 */
public class ModifyProductController implements Initializable {

    /**
     * Cancel button
     */
    public Button modProdCancelBtn;
    /**
     * Remove associated parts button
     */
    public Button modProdRemoveBtn;
    /**
     * Save button
     */
    public Button modProdSaveBtn;
    /**
     * Text field for product ID (auto-generated)
     */
    public TextField modProdID;
    /**
     * Text field for name
     */
    public TextField modProdName;
    /**
     * Text field for stock
     */
    public TextField modProdStock;
    /**
     * Text field for price
     */
    public TextField modProdPrice;
    /**
     * Text field for product maximum
     */
    public TextField modProdMax;
    /**
     * Text field for product minimium
     */
    public TextField modProdMin;
    /**
     * Add associated parts button
     */
    public Button modProdAddPartBtn;
    /**
     * All parts table
     */
    public TableView<Part> partTopTable;
    /**
     * Table column for Product ID for all parts table
     */
    public TableColumn modProdPartIDCol;
    /**
     * Table column for Name for all parts table
     */
    public TableColumn modProdPartNameCol;
    /**
     * Table column for Stock for all parts table
     */
    public TableColumn modProdStockCol;
    /**
     * Table column for cost for all parts table
     */
    public TableColumn modProdCostCol;
    /**
     * Table for associated parts
     */
    public TableView <Part> modProdBtmTable;
    /**
     * Table column for Product ID for associated parts
     */
    public TableColumn modProdAssociatedID;
    /**
     * Table column for Name for associated parts table
     */
    public TableColumn modProdAssociatedName;
    /**
     * Table column for Stock for associated parts table
     */
    public TableColumn modProdAssociatedStock;
    /**
     * Table column for cost for associated parts table
     */
    public TableColumn modProdAssociatedCost;
    /**
     * Text field for search field
     */
    public TextField modProdSearchTF;
    /**
     * selected product object
     */
    private Product selectedProduct;
    /**
     * product index
     */
    private int indexProduct;
    /**
     * list of all associated parts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * This method initializes the controller. It also populates the data in the tables from the
     * MainController. It also populates the text fields from the selected product chosen from the main window.*
     * @param url Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedProduct = MainController.getModProduct();
        associatedParts= selectedProduct.getAllAssociatedParts();
        indexProduct = MainController.getIndexProduct();


        partTopTable.setItems(Inventory.getAllParts());

        modProdPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProdPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProdStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProdCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        modProdBtmTable.setItems(associatedParts);

        modProdAssociatedID.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProdAssociatedName.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProdAssociatedStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProdAssociatedCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        String nameTest = selectedProduct.getName();
        modProdName.setText(nameTest);

        Double priceTest = selectedProduct.getPrice();
        modProdPrice.setText(String.valueOf(priceTest));

        Integer stockTest = selectedProduct.getStock();
        modProdStock.setText(String.valueOf(stockTest));

        Integer minTest = selectedProduct.getMin();
        modProdMin.setText(String.valueOf(minTest));

        Integer maxTest = selectedProduct.getMax();
        modProdMax.setText(String.valueOf(maxTest));
    }

    /**
     * This method returns users to main window when cancel button is pressed.
     * @param actionEvent Cancel button
     * @throws IOException IOException from FXMLLoader
     */
    public void onModCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();

    }

    /**
     * This method gets a modified product
     * @return Modified product
     */

    private Product getProduct(){
        try {
            String name = modProdName.getText();
            Double price = Double.parseDouble(modProdPrice.getText());
            int min = Integer.parseInt(modProdMin.getText());
            int max = Integer.parseInt(modProdMax.getText());
            int stock = Integer.parseInt(modProdStock.getText());


            if (inventoryValid(min, max, stock) && minMaxValid(min, max)) {
                int id = selectedProduct.getId();
                Product newProduct = new Product(id, name, price, stock, min, max);
                for (Part p : associatedParts){
                    newProduct.addAssociatedPart(p);
                }
                Inventory.updateProduct(indexProduct, newProduct);
                return newProduct;

            }

        } catch (Exception displayE) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Data is missing or contains invalid values."));
            alert.showAndWait();

        }

        return null;
    }




    /**
     * This method checks if inventory is less than max and more than min.
     * @param min  minimum level
     * @param max  maximum level
     * @param stock Stock level
     * @return True if valid
     */

    private boolean inventoryValid(int min, int max, int stock) {

        boolean isInventoryValid = true;
        if (stock < min || stock > max) {
            isInventoryValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Stock needs to be less than max and more than min."));
            alert.showAndWait();
        }
        System.out.println("Test");
        return isInventoryValid;
    }
    /**
     * This method checks if minimum is less than max.
     * @param min Minimum level of product
     * @param max Maximum product level
     * @return True if minimum is less than max
     */
    private boolean minMaxValid(int min, int max){
        boolean isMinMaxValid = true;
        if (min >= max){
            isMinMaxValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Min needs to be less than max."));
            alert.showAndWait();
        }
        return isMinMaxValid;
    }

    /**
     * This method returns users to main window
     * @param actionEvent action
     * @throws IOException From FXMLLoader
     */
            public void returnMainScreen(ActionEvent actionEvent) throws IOException {
                Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }
    /**
     * This method adds selected part from parts table to associated parts table.
     * @param actionEvent On Add button click
     */
    public void onAddFromTop(ActionEvent actionEvent) {
        Part selectedPart = partTopTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("No part has been selected."));
            alert.showAndWait();
        } else {
            associatedParts.add(selectedPart);
            modProdBtmTable.setItems(associatedParts);
        }
    }

    /**
     * This method removes a part from associated parts table.
     * @param actionEvent On Remove button click
     */
    public void onRemoveBtn(ActionEvent actionEvent) {
        Part selectedPart = modProdBtmTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("No part has been selected."));
            alert.showAndWait();
        } else {
            Alert alertTwo = new Alert(Alert.AlertType.CONFIRMATION, ("Are you sure you want to remove the Part?"));
            Optional<ButtonType> userAnswer = alertTwo.showAndWait();
            if (userAnswer.isPresent() && userAnswer.get() == ButtonType.OK) {
                associatedParts.remove(selectedPart);
            }
        }
    }
    /**
     * This method searches all parts based on ID or name.
     * @param actionEvent On Search button click
     */
    public void onSearch(ActionEvent actionEvent) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> searchList = FXCollections.observableArrayList();

        String searchParts = modProdSearchTF.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchParts) || part.getName().contains(searchParts)) {
                searchList.add(part);
            }
            partTopTable.setItems(searchList);
        }
    }

    /**
     * This method saves modified product if not null and returns users to main window.
     * @param actionEvent On Save button click
     * @throws IOException From FXMLLoader
     */
    public void onSaveModProd(ActionEvent actionEvent) throws IOException {
        Product product = getProduct();
        if (product != null){
            returnMainScreen(actionEvent);
        }


    }
}


