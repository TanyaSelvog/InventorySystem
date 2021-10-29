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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Product;

/**
 * This class is a controller class that provides logic for main window.
 */
public class MainController implements Initializable {
    /**
     * modified part object
     */
    private static Part modPart;
    /**
     * Search button for parts
     */
    public Button partsSearchBtn;
    /**
     * Search button for products
     */
    public Button productsSearchBtn;

    /**
     * This method returns a modified part
     * @return modified part
     */
    public static Part getModPart() {
        return modPart;
    }

    /**
     * Modified product object
     */

    private static Product modProduct;

    /**
     * This method returns a modified product
     * @return modified product
     */
    public static Product getModProduct() {
        return modProduct;
    }

    /**
     * Associated parts list
     */

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**
     * Part to be deleted
     */
    private static Part deletedPart;
    /**
     * Product to be deleted
     */
    private Product deletedProduct;
    /**
     * Index
     */
    private static int index;

    /**
     * This method returns index of part
     * @return index
     */
    public static int getIndexPart() {
        return index;
    }

    /**
     * Index of product
     */
    private static int indexProduct;

    /**
     * This method returns product's index
     * @return index of product
     */
    public static int getIndexProduct() {
        return indexProduct;
    }

    /**
     * Exit button
     */
    public Button exitBtn;
    /**
     * Table view of Parts table
     */
    public TableView partTable;
    /**
     * Table view of Products table
     */
    public TableView productTable;
    /**
     * Column for ID for Products table
     */
    public TableColumn productID;
    /**
     * Column for Name for Products table
     */
    public TableColumn productName;
    /**
     * Column for Inventory level for Products table
     */
    public TableColumn prodLevel;
    /**
     * Column for Cost for Products table
     */
    public TableColumn prodCost;
    /**
     * Column for ID for Parts table
     */

    public TableColumn partIDCol;
    /**
     * Column for Name for Parts table
     */
    public TableColumn partNameCol;
    /**
     * Inventory level column for Parts table
     */
    public TableColumn invCol;
    /**
     * Cost column for Parts table
     */
    public TableColumn costCol;
    /**
     * Add button for Products
     */
    public Button addProductBtn;
    /**
     * Modify button for Products
     */
    public Button modifyProductBtn;
    /**
     * Delete button for products
     */
    public Button productDeleteBtn;
    /**
     * Text field for products search
     */
    public TextField prodSearchTF;
    /**
     * Label for Products
     */
    public Label productsLBL;
    /**
     * Parts Add button
     */
    public Button addBtn;
    /**
     * Modify button for Parts
     */
    public Button modifyBtn;
    /**
     * Delete button for Parts
     */
    public Button deleteBtn;
    /**
     * Label for Parts
     */
    public Label partsLBL;
    /**
     * Text field for search parts
     */
    public TextField searchParts;

    /**
     * This method displays the Add Product window when user clicks on Add button
     * @param actionEvent Add button click
     * @throws Exception From FXMLLoader
     */
    public void onAddProduct(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addProductForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Product");
        Scene scene = new Scene(root, 885, 561);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method displays Modify Product window when user selects product to modify, or message if no product
     * is selected.
     * @param actionEvent Modify button click
     * @throws Exception From FXMLLoader
     */
    public void onModifyProduct(ActionEvent actionEvent) throws Exception {

        modProduct = (Product) productTable.getSelectionModel().getSelectedItem();
        indexProduct = productTable.getSelectionModel().getSelectedIndex();

        if (modProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select a Product to modify."));
            alert.showAndWait();
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/modifyProduct.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * This method deletes user selected product if no associated parts are found with product. It also displays messages
     * if null and to confirm delete.
     * @param actionEvent Delete button click
     */
    public void onDeleteProduct(ActionEvent actionEvent) {
        deletedProduct = (Product) productTable.getSelectionModel().getSelectedItem();
        //   ObservableList<Part> associatedParts = deletedProduct.getAllAssociatedParts();

        if (deletedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select a Product to delete."));
            alert.showAndWait();
        } else {
            Alert alertTwo = new Alert(Alert.AlertType.CONFIRMATION, ("Do you want to delete the Product?"));
            Optional<ButtonType> userAnswer = alertTwo.showAndWait();
            if (userAnswer.isPresent() && userAnswer.get() == ButtonType.OK) {
                ObservableList<Part> associatedParts = deletedProduct.getAllAssociatedParts();
                if (associatedParts.size() >= 1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, ("This Product has Part(s) associated. It can not be deleted."));
                    alert.showAndWait();
                } else {
                    Inventory.deleteProduct(deletedProduct);
                    }
                }
            }
        }

    /**
     * This method displays Modify Part window if user selects a part to modify.
     * If no part is selected, error message is displayed.
     * @param actionEvent Modify button
     * @throws IOException From FXMLloader
     */

     public void onModify(ActionEvent actionEvent) throws IOException {

        modPart = (Part) partTable.getSelectionModel().getSelectedItem();
        index = partTable.getSelectionModel().getSelectedIndex();

         if (modPart == null) {
             Alert alert = new Alert(Alert.AlertType.ERROR, ("Select a Part to modify."));
             alert.showAndWait();
         } else {
             Parent parent = FXMLLoader.load(getClass().getResource("../view/modifyPart.fxml"));
             Scene scene = new Scene(parent);
             Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
         }
     }

    /**
     * This method deletes a part when a user selects one. It confirms with user before deletion.
     * If no part is selected, error message is displayed.
     * @param actionEvent Delete button
     */
    public void onDelete(ActionEvent actionEvent) {
        deletedPart = (Part) partTable.getSelectionModel().getSelectedItem();
        if (deletedPart != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> userAnswer = alert.showAndWait();

            if (userAnswer.isPresent() && userAnswer.get() == ButtonType.OK) {
                Inventory.deletePart(deletedPart);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select a Part to delete."));
            alert.showAndWait();
        }
    }

    /**
     * This method closes application after asking user if they want to exit
     * @param actionEvent Exit button
     */
    public void exitSystem(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> userAnswer = alert.showAndWait();

        if (userAnswer.isPresent() && userAnswer.get() == ButtonType.OK) {
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.close();
        }
    }
    /**
     * This method displays Add Part window when user clicks on button
     * @param actionEvent Add button
     * @throws IOException From FXMLLoader
     */
   public void onAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addPartForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Part");
        Scene scene = new Scene (root, 645, 650);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method initializes the controller
     * @param url Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partTable.setItems(Inventory.getAllParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.getAllProducts());

        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodCost.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * This method searches list of all products and returns results based on name or ID
     * @param actionEvent Search button
     */

    public void onProdSearch(ActionEvent actionEvent) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> searchList = FXCollections.observableArrayList();

        String searchProds = prodSearchTF.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchProds) || product.getName().contains(searchProds)) {

                searchList.add(product);
            }

            productTable.setItems(searchList);
        }
        if (searchList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("No results found."));
            alert.showAndWait();
        }
    }

    /**
     * This method clears table after product search
     * @param actionEvent keypressed
     */
    public void onProductsSearch(KeyEvent actionEvent) {
        if (prodSearchTF.getText().isEmpty()){
            productTable.setItems(Inventory.getAllProducts());
        }
    }

    /**
     * This method clears table after part search
     * @param actionEvent Keypressed
     */
    public void onSearch(KeyEvent actionEvent) {
        if (searchParts.getText().isEmpty()) {
            partTable.setItems(Inventory.getAllParts());
        }
    }

    /**
     * This method searches list of all parts based on name or ID
     * @param actionEvent Search button
     */
    public void onSearchPartsBtn(ActionEvent actionEvent) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        String queryPart = searchParts.getText();

        for(Part part : allParts){
            if(part.getName().contains(queryPart) || String.valueOf(part.getId()).contains(queryPart)){
                namedParts.add(part);
            }
        }

        partTable.setItems(namedParts);
        if (namedParts.isEmpty()){
              Alert alert = new Alert(Alert.AlertType.ERROR, ("No results found."));
            alert.showAndWait();
        }
    }
}


