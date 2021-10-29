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
     * @param partialName Partial name of part to be searched
     * @return list of named parts
     */
    private ObservableList<Part> searchByName(String partialName){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();
        for(Part part : allParts){
            if(part.getName().contains(partialName)){
                namedParts.add(part);
            }
        }

        return namedParts;
    }

    /**
     * This method initializes the controller.
     * It also populates the data in the tables from the MainController.
     * @param url Used to resolve relative paths for the root object, or null if the location is not known.
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
     * @return A new product
     */
   private Product getProduct(){
        boolean isValid = true;
        List<String> messages = new ArrayList<>();
        Double price = null;
        Integer stock = null;
        Integer max = null;
        Integer min = null;


        String name = addProductNameTF.getText();
            if (name.isEmpty()) {
                isValid = false;
                messages.add("No data in Name field");
            }

            String priceText = addPriceProdTF.getText();
            if (priceText.isEmpty()) {
                isValid = false;
                messages.add("No data in Price field");
            } else try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException e) {
                isValid = false;
                messages.add("Price is not a double.");
            }


            String stockText = addStockProdTF.getText();
            if (stockText.isEmpty()) {
                isValid = false;
                messages.add("No data in Stock field");
            } else try {
                stock = Integer.parseInt(stockText);
            } catch (NumberFormatException e) {
                isValid = false;
                messages.add("Stock is not an integer");
            }

            String minText = addMinProdTF.getText();
            if (minText.isEmpty()) {
                isValid = false;
                messages.add("No data in Min field");
            } else try {
                min = Integer.parseInt(minText);
            } catch (NumberFormatException e) {
                isValid = false;
                messages.add("Min is not an integer");
            }

            String maxText = addMaxProd.getText();
            if (maxText.isEmpty()) {
                isValid = false;
                messages.add("No data in Max field");
            } else try {
                max = Integer.parseInt(maxText);
            } catch (NumberFormatException e) {
                isValid = false;
                messages.add("Max is not an integer");
            }

            if (min != null && max != null && min >= max) {
                isValid = false;
                messages.add("Min value needs to be less than Max value");
            }

            if (isValid) {

                Integer id = Inventory.addID();

                    Product newProduct = new Product(id, name, price, stock, min, max);
                    for (Part p : associatedParts){
                        newProduct.addAssociatedPart(p);
                    }
                    Inventory.addProduct(newProduct);
                    System.out.println(newProduct.toString());
                    return newProduct;

            }

            Alert alert = new Alert(Alert.AlertType.ERROR, String.join("\n", messages));
            alert.showAndWait();
            return null;
    }

    /**
     * This method returns users to main window when they click on cancel button.
     * @param actionEvent Cancel button click
     * @throws IOException From FXMLLoader
     */
    public void onCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();
    }

    /**
     * This method returns users to main window.
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
     * @param actionEvent On Save button click
     * @throws IOException From FXMLLoader
     */

    public void onSaveProdAdd(ActionEvent actionEvent) throws IOException {
        Product product = getProduct();
        if (product != null){
        returnMainScreen(actionEvent);
        }
    }
    /**
     * This method adds selected part from parts table to associated parts table.
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
     * @param actionEvent On Remove button click
     */
    public void onRemovePart(ActionEvent actionEvent) {
        Part selectedPart = addProdBtmTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("No part has been selected."));
            alert.showAndWait();
        } else {
            associatedParts.remove(selectedPart);
        }
    }
}
