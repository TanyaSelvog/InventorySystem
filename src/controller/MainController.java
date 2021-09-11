package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Product;


public class MainController implements Initializable {

    public Button exitBtn;
    public TableView partTable;
    public TableView productTable;
    public TableColumn productID;
    public TableColumn productName;
    public TableColumn prodLevel;
    public TableColumn prodCost;
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn invCol;
    public TableColumn costCol;
    public Button addProductBtn;
    public Button modifyProductBtn;
    public Button productDeleteBtn;
    public TextField prodSearch;
    public Label productsLBL;

    public Button addBtn;
    public Button modifyBtn;
    public Button deleteBtn;
    public Label partsLBL;
    public TextField searchParts;


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

    //Search function
    private ObservableList<Product> searchProductName(String partialName){
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(Product product : allProducts){
            if(product.getName().contains(partialName)){
                namedProducts.add(product);
            }
        }
        return namedProducts;
    }


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

    public void onAddProduct(ActionEvent actionEvent) throws Exception {
                Parent root = FXMLLoader.load(getClass().getResource("../view/addProductForm.fxml"));
                //set new stage
                Stage stage = new Stage();
                stage.setTitle("Add Product");
                stage.setScene(new Scene(root, 885, 561));
                stage.show();
    }

    public void onModifyProduct(ActionEvent actionEvent) throws Exception {
                Parent root = FXMLLoader.load(getClass().getResource("../view/modifyProduct.fxml"));
                //set new stage
                Stage stage = new Stage();
                stage.setTitle("Modify Product");
                stage.setScene(new Scene(root, 885, 561));
                stage.show();
    }


    public void onDeleteProduct(ActionEvent actionEvent) {
            }

     public void onProdSearch(ActionEvent actionEvent) {
        String q = prodSearch.getText();
        ObservableList<Product> products = searchProductName(q);
        productTable.setItems(products);
            }

     public void onModify(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("../view/modifyPart.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Modify Part");
                stage.setScene(new Scene(root, 645, 650));
                stage.show();
            }

            public void onDelete(ActionEvent actionEvent) {
            }


  public void onSearch(ActionEvent actionEvent) {
        String q = searchParts.getText();
        ObservableList<Part> parts = searchByName(q);
        partTable.setItems(parts);
            }

 public void onAdd(ActionEvent actionEvent) {
            }

 public void exitSystem(ActionEvent actionEvent) {
                Stage stage = (Stage) exitBtn.getScene().getWindow();
                stage.close();
            }

public void onAddPart(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("../view/addPartForm.fxml"));
                //set new stage
                Stage stage = new Stage();
                stage.setTitle("Add Part");
                stage.setScene(new Scene(root, 645, 650));
                stage.show();
}
   }
