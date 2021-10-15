package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private Product selectedProduct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedProduct = MainController.getModProduct();
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


    }

    public void onModCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();
    }

    public void onSaveModProd(ActionEvent actionEvent) {
    }
}
