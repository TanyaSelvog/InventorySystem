package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {


    public Button modProdCancelBtn;
    public Button modProdRemoveBtn;
    public Button modProdSaveBtn;
    public TextField modProdID;
    public TextField modProdName;
    public TextField modProdStock;
    public TextField modProdPrice;
    public TextField modProdMax;
    public TextField modProdMin;
    public Button modProdAddPartBtn;
    public TableView<Part> partTopTable;
    public TableColumn modProdPartIDCol;
    public TableColumn modProdPartNameCol;
    public TableColumn modProdStockCol;
    public TableColumn modProdCostCol;
    public TableView modProdBtmTable;
    public TableColumn modProdAssociatedID;
    public TableColumn modProdAssociatedName;
    public TableColumn modProdAssociatedStock;
    public TableColumn modProdAssociatedCost;
    private Product selectedProduct;
    private int indexProduct;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedProduct = MainController.getModProduct();
        indexProduct = MainController.getIndexProduct();

        System.out.println(selectedProduct.toString());

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

        partTopTable.setItems(Inventory.getAllParts());

        modProdPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProdPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProdStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProdCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        modProdAssociatedID.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProdAssociatedName.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProdAssociatedStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProdAssociatedCost.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    public void onModCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
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

    public void onSaveModProd(ActionEvent actionEvent) throws IOException {
        try {
            int id = selectedProduct.getId();
            String name = modProdName.getText();
            Double price = Double.parseDouble(modProdPrice.getText());
            int stock = Integer.parseInt(modProdStock.getText());
            int min = Integer.parseInt(modProdMin.getText());
            int max = Integer.parseInt(modProdMax.getText());
            //this will need to be changed; temporary for testing
            if (min < max){
                Product newProduct = new Product(id, name, price, stock, min, max);
                Inventory.updateProduct(indexProduct, newProduct);

                System.out.println(newProduct.toString());
            }
        } catch (Exception e) {
            System.out.println("Modify product is not working like it should");
        }
        returnMainScreen(actionEvent);
    }

    public void onAddFromTop(ActionEvent actionEvent) {
        Part selectedPart = partTopTable.getSelectionModel().getSelectedItem();
        associatedParts.add(selectedPart);
        modProdBtmTable.setItems(associatedParts);
    }
}

