package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Part;


import java.io.IOException;



public class AddPartController implements Initializable {


    public RadioButton inHouseRadio;
    public ToggleGroup tgroup;
    public Label changeMe;
    public TextField costPartText;
    public TextField maxTextField;
    public TextField machineIDText;
    public TextField minTextField;
    public Button savePartBtn;
    public Button cancelPartAddBtn;
    public TextField stockTextField;
    public TextField nameTextField;
    public TextField idTextField;
    public RadioButton outsourcedBtn;
    public Label addPartLBL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onInHouseAdd(ActionEvent actionEvent) {
        changeMe.setText("Machine ID");

    }

    public void onOutsourcedAdd(ActionEvent actionEvent) {
        changeMe.setText("Company Name");
    }

    public void onCancelPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();
    }

    public Integer getStock() {
        String stockText = stockTextField.getText();

        if (stockText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Enter a stock.");
            alert.showAndWait();
            return null;
        }

        try {
            return Integer.parseInt(stockText);
        } catch(NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Stock must be an integer");
            alert.showAndWait();
            return null;
        }
    }

    public String getName() {
        String nameText = nameTextField.getText();

        if (nameText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Enter a name");
            alert.showAndWait();
            return null;
        } else {
            return nameText;
        }
    }




 /**   public String getMachineIDTest(){
    try {
        String machineID = machineIDText.getText();
        if (machineID.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Enter a machine info");
            alert.showAndWait();
        }
        // @TODO try parsing it
        return machineID;
    }
        catch(NumberFormatException nfe){
        // @TODO display error
            return null;
        }
    }


    public int getStockTest() {
        try {
            int stock = Integer.parseInt(stockTextField.getText());
            if (stock < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Enter a number.");
                alert.showAndWait();
            }
            // @TODO try parsing it
            return stock;
        } catch(NumberFormatException nfe) {
            // @TODO display error
            return null;
    }

    public int getMinTest() {
            try {
             int min = Integer.parseInt(minTextField.getText());
             if (min < 0) {
                 Alert alert = new Alert(Alert.AlertType.ERROR,
                         "Enter a number.");
                 alert.showAndWait();
             }
                // @TODO try parsing it
                return min;
            } catch(NumberFormatException nfe) {
                // @TODO display error
                return null;

        }

    }

    public double getPriceTest()  {
            try{
                double price = Double.parseDouble(costPartText.getText());

        if (price < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Enter a number.");
            alert.showAndWait();
        }
                // @TODO try parsing it
                return price;
            } catch(NumberFormatException nfe) {
                // @TODO display error
                return null;

            }



    }

    public int getMaxTest() {
            try {

                int max = Integer.parseInt(maxTextField.getText());

                if (max < 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,
                            "Enter a number.");
                    alert.showAndWait();
                }
                // @TODO try parsing it
                return max;
            } catch (NumberFormatException nfe) {
                // @TODO display error
                return null;

            }
        }
*/

    private Part getPart() {
        boolean isValid = true;
        List<String> messages = new ArrayList<>();
        String name = null;
        Integer id = null;
        Integer stock = null;
        Integer max = null;
        Integer min = null;

        name = nameTextField.getText();
        if (name.isEmpty()) {
            isValid = false;
            messages.add("No data in name field");
        }

        String stockText = stockTextField.getText();
        if (stockText.isEmpty()) {
            isValid = false;
            messages.add("No data in stock field");
        } else try {
            stock = Integer.parseInt(stockText);
        } catch(NumberFormatException e) {
            isValid = false;
            messages.add("stock is not an integer");
        }

        if (isValid) {
            /** @TODO construct and return InHouse part, or Outsourced part */
            /** @implNote we're hardcoding and assuming this is an InHouse */
            return new InHouse(0, name, 0, stock, 0, 10, 555);
        }

        Alert alert = new Alert(Alert.AlertType.ERROR, String.join("\n", messages));
        alert.showAndWait();
        return null;
    }

    public void onSaveBtnAddPart(ActionEvent actionEvent) throws IOException {
       // String name = nameTextField.getText();
       // getMachineIDTest();
        Part part = getPart();

        if (part != null) {
            // @TODO save the part in the parts TableView
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "we made a part!");
            alert.showAndWait();
        }

       /** int stock = Integer.parseInt(stockTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int min = Integer.parseInt(minTextField.getText());
        double price = Double.parseDouble(costPartText.getText());
        String machineID = machineIDText.getText();

 //
   //     InHouse newInHouse = new InHouse(id, name, price, stock, min, max, machineID);
/**

        if (!name.isEmpty()){
            System.out.println("Good");
            else (
                Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Enter a positive hourly wage value.");
            alert.showAndWait();)
        }
    }
}





        /**String name;
        int stock;
        int max;
        try {
           name = getNameTest();
            System.out.println("awesome");
            stock = getStockTest();
            System.out.println("awesome stock");
            max = getMaxTest();
        } catch (Exception except) {
            System.out.println(except.getMessage());
            System.out.println("test");
        }
    }
}




        /**  try {
            String name = nameTextField.getText();
            int stock = Integer.parseInt(stockTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            double price = Double.parseDouble(costPartText.getText());
            String machineID = machineIDText.getText();

            if (name.isEmpty()){
                throw new Exception ("Name is empty");

            }
            else {
                System.out.println("test");
            }
        } catch (Exception except) {
            System.out.println("Name is not empty");
        }
    }
}
         */

    }
}
