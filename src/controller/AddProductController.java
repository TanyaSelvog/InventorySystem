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
 * This class is a controller class that provides logic for the add product screen.
 */
public class AddProductController implements Initializable {
    /**
     * Cancel button
     */
    public Button productCancelBtn;
    /**
     * Text field for product name
     */
    public TextField addProductNameTF;
    /**
     * Text field for product stock
     */
    public TextField addStockProdTF;
    /**
     * Text field for price
     */
    public TextField addPriceProdTF;
    /**
     * Text field for max level
     */
    public TextField addMaxProd;
    /**
     * Text field for min level
     */
    public TextField addMinProdTF;
    /**
     * Save button
     */
    public Button saveProdBtn;
    /**
     * Table for all Parts
     */
    public TableView<Part> addProdTopTable;
    /**
     * Table column for ID for all parts table
     */
    public TableColumn idCol;
    /**
     * Table column for name for all parts table
     */
    public TableColumn nameCol;
    /**
     * Table column for inventory level for all parts table
     */
    public TableColumn invCol;
    /**
     * Table column for cost for all parts table
     */
    public TableColumn costCol;
    /**
     * Table for associated Parts
     */
    public TableView<Part> addProdBtmTable;
    /**
     * Table column for ID for associated parts table
     */
    public TableColumn idColAssociated;
    /**
     * Table column for name for associated parts table
     */
    public TableColumn nameColAssociated;
    /**
     * Table column for inventory for associated parts table
     */
    public TableColumn invColAssociated;
    /**
     * Table column for cost for associated parts table
     */
    public TableColumn costColAssociated;
    /**
     * Add associated parts button
     */
    public Button addBtn;
    /**
     * Search text field
     */
    public TextField searchField;
    /**
     * Button to remove associated parts
     */
    public Button removeAssociatedPartBtn;
    /**
     * List of all associated parts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * This method searches by name all parts.
     *
     * @param partialName Partial name of part to be searched
     * @return list of named parts
     */
    private ObservableList<Part> searchByName(String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part part : allParts) {
            if (part.getName().contains(partialName)) {
                namedParts.add(part);
            }
        }

        return namedParts;
    }

    /**
     * This method initializes the controller.
     * It also populates the data in the tables from the MainController.
     *
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addProdTopTable.setItems(Inventory.getAllParts());

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        idColAssociated.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColAssociated.setCellValueFactory(new PropertyValueFactory<>("name"));
        invColAssociated.setCellValueFactory(new PropertyValueFactory<>("stock"));
        costColAssociated.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * This method adds a product in inventory. If text fields  have
     * invalid data, error messages are produced.
     *
     * @return A new product
     */


    private Product getProduct() {

        try {
            String name = addProductNameTF.getText();
            Double price = Double.parseDouble(addPriceProdTF.getText());
            int min = Integer.parseInt(addMinProdTF.getText());
            int max = Integer.parseInt(addMaxProd.getText());
            int stock = Integer.parseInt(addStockProdTF.getText());


            if (inventoryValid(min, max, stock) && minMaxValid(min, max)) {
                Integer id = Inventory.addProductID();
                Product newProduct = new Product(id, name, price, stock, min, max);
                for (Part p : associatedParts) {
                    newProduct.addAssociatedPart(p);
                }
                Inventory.addProduct(newProduct);
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
     *
     * @param min   Minimum level
     * @param max   Maximum level
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
     *
     * @param min Minimum level of product
     * @param max Maximum product level
     * @return True if minimum is less than max
     */
    private boolean minMaxValid(int min, int max) {
        boolean isMinMaxValid = true;
        if (min >= max) {
            isMinMaxValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Min needs to be less than max."));
            alert.showAndWait();
        }
        return isMinMaxValid;
    }

    /**
     * This method returns users to main window when they click on cancel button.
     *
     * @param actionEvent Cancel button click
     * @throws IOException From FXMLLoader
     */
    public void onCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();
    }


    /**
     * This method returns users to main window.
     *
     * @param actionEvent Some type of action
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
     * This method adds saves new product and returns users to main window.
     *
     * @param actionEvent On Save button click
     * @throws IOException From FXMLLoader
     */

    public void onSaveProdAdd(ActionEvent actionEvent) throws IOException {
        Product product = getProduct();
        if (product != null) {
            returnMainScreen(actionEvent);
        }
    }

    /**
     * This method adds selected part from parts table to associated parts table.
     *
     * @param actionEvent On Add button click
     */
    public void onAddFromTopTableToBtm(ActionEvent actionEvent) {
        Part selectedPart = addProdTopTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("No part has been selected."));
            alert.showAndWait();
        } else {
            associatedParts.add(selectedPart);
            addProdBtmTable.setItems(associatedParts);
        }
    }

    /**
     * This method searches all parts based on ID or name.
     *
     * @param actionEvent On Search button click
     */
    public void onSearch(ActionEvent actionEvent) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> searchList = FXCollections.observableArrayList();

        String searchProds = searchField.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchProds) || part.getName().contains(searchProds)) {

                searchList.add(part);
            }
            addProdTopTable.setItems(searchList);
        }
    }

    /**
     * This method removes a part from associated parts table.
     *
     * @param actionEvent On Remove button click
     */
    public void onRemovePart(ActionEvent actionEvent) {
        Part selectedPart = addProdBtmTable.getSelectionModel().getSelectedItem();
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
}
