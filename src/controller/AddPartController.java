package controller;

import javafx.collections.ObservableList;
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
import model.*;


import java.io.IOException;

import static model.Inventory.addID;


public class AddPartController implements Initializable {


    public RadioButton inHouseRadio;
    public ToggleGroup tgroup;
    public Label changeMe;
    public TextField costPartText;
    public TextField maxTextField;
    public TextField partDiffLabel;
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


    private Part getPart() {
        boolean isValid = true;
        List<String> messages = new ArrayList<>();
        //  Integer id = null;
        String name = null;
        Double price = null;
        Integer stock = null;
        Integer max = null;
        Integer min = null;
        Integer machineID = null;
        String companyName = null;
        boolean kind;
        Part newPart = null;


        name = nameTextField.getText();
        if (name.isEmpty()) {
            isValid = false;
            messages.add("No data in Name field");
        }

        String priceText = costPartText.getText();
        if (priceText.isEmpty()) {
            isValid = false;
            messages.add("No data in Price field");
        } else try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            isValid = false;
            messages.add("Price is not a double.");
        }


        String stockText = stockTextField.getText();
        if (stockText.isEmpty()) {
            isValid = false;
            messages.add("No data in Stock field");
        } else try {
            stock = Integer.parseInt(stockText);
        } catch (NumberFormatException e) {
            isValid = false;
            messages.add("Stock is not an integer");
        }

        String minText = minTextField.getText();
        if (minText.isEmpty()) {
            isValid = false;
            messages.add("No data in Min field");
        } else try {
            min = Integer.parseInt(minText);
        } catch (NumberFormatException e) {
            isValid = false;
            messages.add("Min is not an integer");
        }

        String maxText = maxTextField.getText();
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

        if (tgroup.getSelectedToggle() == null) {
            isValid = false;
            messages.add("Select either In-House or Outsourced");
            System.out.println("se");
        }

        if (inHouseRadio.isSelected()) {
            String machineText = partDiffLabel.getText();
            if (machineText.isEmpty()) {
                isValid = false;
                messages.add("No data in MachineID field");
            } else try {
                machineID = Integer.parseInt(machineText);
            } catch (NumberFormatException e) {
                isValid = false;
                messages.add("MachineID is not an integer");
            }


        }

        if (outsourcedBtn.isSelected()) {
            String company = partDiffLabel.getText();
            if (company.isEmpty()) {
                isValid = false;
                messages.add("No data in Company Name field");
            }

        }

        if (isValid) {
            
            Integer id = addID();
            if (inHouseRadio.isSelected()) {
                InHouse newPartInHouse = new InHouse(id, name, price, stock, min, max, machineID);
                return newPartInHouse;
            } else if (outsourcedBtn.isSelected()) {
                Outsourced newOutsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                return newOutsourced;
            }

        }

        Alert alert = new Alert(Alert.AlertType.ERROR, String.join("\n", messages));
        alert.showAndWait();
        
        return null;
    }
    public void onSaveBtnAddPart(ActionEvent actionEvent) throws IOException {


        Part part = getPart();

/**
       if (part != null) {
            // @TODO save the part in the parts TableView
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "we made a part!");
            alert.showAndWait();
        }
*/



         }
}
