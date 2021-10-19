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

public class AddProductController implements Initializable {
    public Button productCancelBtn;
    public TextField addProductNameTF;
    public TextField addStockProdTF;
    public TextField addPriceProdTF;
    public TextField addMaxProd;
    public TextField addMinProdTF;
    public Button saveProdBtn;
    public TableView<Part> addProdTopTable;
    public TableColumn idCol;
    public TableColumn nameCol;
    public TableColumn invCol;
    public TableColumn costCol;
    public TableView addProdBtmTable;
    public TableColumn idColAssociated;
    public TableColumn nameColAssociated;
    public TableColumn invColAssociated;
    public TableColumn costColAssociated;
    public Button addBtn;
    public TextField searchField;
    private static Part partToAdd;

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();



    //to search for parts based on name


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
                    Inventory.addProduct(newProduct);
                    System.out.println(newProduct.toString());
                    return newProduct;


            }

            Alert alert = new Alert(Alert.AlertType.ERROR, String.join("\n", messages));
            alert.showAndWait();
            return null;
    }

    public void onCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();
    }
    public void returnMainScreen(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public void onSaveProdAdd(ActionEvent actionEvent) throws IOException {
      //  partToAdd = (Part) addProdTopTable.getSelectionModel().getSelectedItem();
        //System.out.println(partToAdd.toString());
        Product testProd = getProduct();
        returnMainScreen(actionEvent);
    }

    public void onAddFromTopTableToBtm(ActionEvent actionEvent) {
        //this btn is for adding a row from top table to bottom (associating with the product)
        Part selectedPart = addProdTopTable.getSelectionModel().getSelectedItem();
        associatedParts.add(selectedPart);
        addProdBtmTable.setItems(associatedParts);

    }

    public void onSearch(ActionEvent actionEvent) {
        String q = searchField.getText();
        ObservableList<Part> parts = searchByName(q);
        addProdTopTable.setItems(parts);
    }
}
