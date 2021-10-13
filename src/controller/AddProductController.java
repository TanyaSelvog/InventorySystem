package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    public void onSaveProdAdd(ActionEvent actionEvent) {
        Product testProd = getProduct();
    }
}
