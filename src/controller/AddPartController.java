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

/**
 * This class is a controller class that provides logic for the add part screen.
 */

public class AddPartController implements Initializable {

    /**
     * Radio button for In House Parts
     */
    public RadioButton inHouseRadio;
    /**
     * Toggle group for radio buttons
     */
    public ToggleGroup tgroup;
    /**
     * Label for Company Name or Machine ID
     */
    public Label changeMe;
    /**
     * Cost text field
     */
    public TextField costPartText;
    /**
     * Max text field
     */
    public TextField maxTextField;
    /**
     * Text field for Company Name or Machine ID
     */
    public TextField partDiffLabel;
    /**
     * Min text field
     */
    public TextField minTextField;
    /**
     * Save button
     */
    public Button savePartBtn;
    /**
     * Cancel button
     */
    public Button cancelPartAddBtn;
    /**
     * Text field for stock/inventory
     */
    public TextField stockTextField;
    /**
     * Name text field
     */
    public TextField nameTextField;
    /**
     * ID Text Field (auto generated)
     */
    public TextField idTextField;
    /**
     * Radio button for Outsourced Part
     */
    public RadioButton outsourcedBtn;
    /**
     * Label for Add Part window
     */
    public Label addPartLBL;

    /**
     * This method initializes the controller.
     *
     * @param url            Used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method changes text to Machine ID when In-House radio button is selected.
     *
     * @param actionEvent In-House radio button is selected
     */

    public void onInHouseAdd(ActionEvent actionEvent) {
        changeMe.setText("Machine ID");

    }

    /**
     * This method changes text to Company Name when Outsourced is selected.
     *
     * @param actionEvent Outsourced radio button is selected
     */
    public void onOutsourcedAdd(ActionEvent actionEvent) {
        changeMe.setText("Company Name");
    }

    /**
     * This method returns user to main window when they click on cancel.
     *
     * @param event On Cancel button click
     * @throws IOException From FXMLLoader
     */
    public void onCancelPart(ActionEvent event) throws IOException {
        returnMainScreen(event);
    }

    /**
     * This method returns users to main window
     *
     * @param actionEvent Event representing some type of action
     * @throws IOException From FXMLLoader
     */
    public void returnMainScreen(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method returns a part
     *
     * @return new part
     * <p><b>
     * FUTURE ENHANCEMENTS</b>
     * I would make design changes like where the 'min' and 'max' are placed. I would
     * also make the app more accessible and include tooltips and explanations for fields.</p>
     */

    private Part getPart() {
        if (tgroup.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("In-House or Outsourced need to be checked."));
            alert.showAndWait();
        }
        try {
            String name = nameTextField.getText();
            Double price = Double.parseDouble(costPartText.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int stock = Integer.parseInt(stockTextField.getText());
            int machineID;
            String companyName;



            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ("Name is field missing data."));
                alert.showAndWait();

            } else {
                if (inventoryValid(min, max, stock) && minMaxValid(min, max)) {

                    Integer id = Inventory.addID();

                    if (inHouseRadio.isSelected()) {
                        machineID = Integer.parseInt(partDiffLabel.getText());
                        InHouse newPartInHouse = new InHouse(id, name, price, stock, min, max, machineID);
                        Inventory.addPart(newPartInHouse);
                        return newPartInHouse;

                    } else if (outsourcedBtn.isSelected()) {
                        companyName = partDiffLabel.getText();
                        Outsourced newOutsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                        Inventory.addPart(newOutsourced);
                        return newOutsourced;
                    }
                }
                }
            } catch(Exception displayE){
                Alert alert = new Alert(Alert.AlertType.ERROR, ("Data is missing or contains invalid values."));
                alert.showAndWait();

            }

            return null;
        }



    /**
     * This method checks if inventory is less than max and more than min.
     * @param min  Minimum level
     * @param max  Maximum level
     * @param stock Stock level
     * @return True if valid
     */

    private boolean inventoryValid(int min, int max, int stock) {

        boolean isInventoryValid = true;
        if (stock < min || stock > max) {
            isInventoryValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Stock needs to be less than max and more than min."));
            alert.showAndWait();
        }
        System.out.println("Test");
        return isInventoryValid;
    }
    /**
     * This method checks if minimum is less than maximum.
     * @param min  Minimum level
     * @param max  Maximum level
     * @return True if minimum is less than maximum
     */
    private boolean minMaxValid(int min, int max){
        boolean isMinMaxValid = true;
        if (min >= max){
            isMinMaxValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, ("Min needs to be less than max."));
            alert.showAndWait();
        }
        return isMinMaxValid;
        }
    /**
     * This method gets saves a new part and returns users to main window
     *
     * @param actionEvent On Save button click
     * @throws IOException From FXMLLoader
     *                     <p><b>
     *                     RUNTIME/LOGICAL ERRORS</b>
     *                     Initially I made this method so that it would run the getPart method and
     *                     immediately close/return user to main window. I realized that if there were errors with the part and the
     *                     user just saw the error messages, that they wouldn't be able to edit the data without the window closing and returning
     *                     them to the main window. Since my getPart method returns null if there is an error, I added an if statement so
     *                     that way the user is returned to the main window only in the cases that there is an actual valid part.</p>
     */

    public void onSaveBtnAddPart(ActionEvent actionEvent) throws IOException {
        Part part = getPart();

            if (part != null) {
                returnMainScreen(actionEvent);
            }
        }
    }





