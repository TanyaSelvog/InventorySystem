package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Product;


public class MainController implements Initializable {

    private static Part modPart;

    public static Part getModPart() {
        return modPart;
    }
    private static Product modProduct;

    public static Product getModProduct() {
        return modProduct;
    }

    private static Part deletedPart;
    private static int index;
    public static int getIndexPart(){
        return index;
    }

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

        modProduct = (Product) productTable.getSelectionModel().getSelectedItem();
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

        modPart = (Part) partTable.getSelectionModel().getSelectedItem();
        index = partTable.getSelectionModel().getSelectedIndex();
        System.out.println("Index for this part is: " + index);

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
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Select a part to delete."));
            alert.showAndWait();
        }


        }




  public void onSearch(ActionEvent actionEvent) {
        String q = searchParts.getText();
        ObservableList<Part> parts = searchByName(q);
        partTable.setItems(parts);
            }

 public void onAdd(ActionEvent actionEvent) throws IOException{
     Parent root = FXMLLoader.load(getClass().getResource("/view/addProductForm.fxml"));
     Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
     stage.setTitle("Add Part");
     Scene scene = new Scene (root, 885, 561);
     stage.setScene(scene);
     stage.show();

 }



 public void exitSystem(ActionEvent actionEvent) {
                Stage stage = (Stage) exitBtn.getScene().getWindow();
                stage.close();
            }

public void onAddPart(ActionEvent actionEvent) throws IOException {
    //setting new scene without new window
  Parent root = FXMLLoader.load(getClass().getResource("/view/addPartForm.fxml"));
  Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
  stage.setTitle("Add Part");
  Scene scene = new Scene (root, 645, 650);
  stage.setScene(scene);
  stage.show();

          }

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




            }
