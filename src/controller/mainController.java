package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import javafx.scene.control.*;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class mainController implements Initializable {
    public Button exitBtn;
    public TableView partTable;
    public TableView productTable;
    public TableColumn productID;
    public TableColumn productName;
    public TableColumn prodLevel;
    public TableColumn prodCost;
    public TableColumn partID;
    public TableColumn partName;
    public TableColumn inventoryLevel;
    public TableColumn costUnit;
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

        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellFactory(new PropertyValueFactory<>("stock"));
        costUnit.setCellFactory(new PropertyValueFactory<>("price"));





    }




    public void onAddProduct(ActionEvent actionEvent)  {

    }
    public void onModifyProduct(ActionEvent actionEvent) {
    }

    public void onDeleteProduct(ActionEvent actionEvent) {
    }

    public void onProdSearch(ActionEvent actionEvent) {
    }

    public void onModify(ActionEvent actionEvent) {
    }

    public void onDelete(ActionEvent actionEvent) {
    }

    public void onSearch(ActionEvent actionEvent) {
    }

    public void onAdd(ActionEvent actionEvent) {
    }

    public void exitSystem(ActionEvent actionEvent) {

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

