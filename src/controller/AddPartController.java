package controller;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    public void onCancelPart(ActionEvent event) throws IOException {
        returnMainScreen(event);
    }

    public void returnMainScreen(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }


    public void onSaveBtnAddPart(ActionEvent actionEvent) throws IOException {


        String name = nameTextField.getText().trim();
        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data is missing in name field");
            alert.showAndWait();
        } else {
            try {
                int stock = Integer.parseInt(stockTextField.getText());
                int max = Integer.parseInt(maxTextField.getText().trim());
                int min = Integer.parseInt(minTextField.getText().trim());
                if (min >= max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Min value needs to be less than Max value");
                }
                // int machineID = Integer.parseInt(machineIDField.getText().trim());
                double price = Double.parseDouble(costPartText.getText().trim());

            } catch (NumberFormatException nfe) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Data is missing in a field");
                alert.showAndWait();
            }


            //   Part part = getPart();
            returnMainScreen(actionEvent);


        }

    }
    /**    private void minMaxCompare(){
            if (min != null && max != null && min >= max) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Min is more than max");
                alert.showAndWait();
            }

        }


        if (tgroup.getSelectedToggle() == null) {
        isValid = false;
        messages.add("Select either In-House or Outsourced");
    }
}*/
    }

