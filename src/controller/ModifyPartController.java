package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class is a controller class that provides logic for the modify part screen.
 */
public class ModifyPartController implements Initializable {

    /**
     * Radio button for In-House Part
     */
    public RadioButton inHouseRadio;
    /**
     * Toggle group for radio buttons
     */
    public ToggleGroup modifygroup;
    /**
     * Radio button for Outsourced Part
     */
    public RadioButton outsourcedBtn;
    /**
     * Text Field for ID (auto-generated)
     */
    public TextField idTextField;
    /**
     * Label for Machine ID or Company Name
     */
    public Label modifyChange;
    /**
     * Modify Part label
     */
    public Label addPartLBL;
    /**
     * Cancel button
     */
    public Button cancelPartAddBtn;
    /**
     * Save button
     */
    public Button savePartBtn;
    /**
     * Text field for minimum level
     */
    public TextField minTextField;
    /**
     * Text field for Machine ID or Company Name
     */
    public TextField machineOrCompany;
    /**
     * Text field for maximum level
     */
    public TextField maxTextField;
    /**
     * Text field for cost
     */
    public TextField costPartText;
    /**
     * Text field for stock
     */
    public TextField inventoryTextField;
    /**
     * Text field for name
     */
    public TextField nameTextField;

    /**
     * selectedPart object
     */
    private Part selectedPart;
    /**
     * Index of parts
     */
    private int index;

    /**
     * This method changes text to Machine ID when In-House radio button is selected.
     *
     * @param actionEvent In-House radio button is selected
     */
    public void onInHouseModify(ActionEvent actionEvent) {
        modifyChange.setText("Machine ID");
    }

    /**
     * This method changes text to Company Name when Outsourced radio button is selected.
     *
     * @param actionEvent Outsourced radio button is selected
     */
    public void onOutsourcedModify(ActionEvent actionEvent) {
        modifyChange.setText("Company Name");
    }

    /**
     * This method returns user to main window when they click on cancel.
     *
     * @param actionEvent On Cancel button click
     * @throws IOException From FXMLLoader
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();
    }

    /**
     * This method returns user to main window
     *
     * @param actionEvent Event type
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
     * This method gets a part and returns it
     *
     * @return Modified part
     */

    private Part getModPart() {
        if (modifygroup.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ("In-House or Outsourced need to be checked."));
            alert.showAndWait();
        }
        try {
            String name = nameTextField.getText();
            Double price = Double.parseDouble(costPartText.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int stock = Integer.parseInt(inventoryTextField.getText());
            int machineID;
            String companyName;


            if (inventoryValid(min, max, stock) && minMaxValid(min, max)) {
                int id = selectedPart.getId();

                if (inHouseRadio.isSelected()) {
                    machineID = Integer.parseInt(machineOrCompany.getText());
                    InHouse newPartInHouse = new InHouse(id, name, price, stock, min, max, machineID);
                    Inventory.updatePart(index, newPartInHouse);
                    return newPartInHouse;

                } else if (outsourcedBtn.isSelected()) {
                    companyName = machineOrCompany.getText();
                    Outsourced newOutsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.updatePart(index,newOutsourced);
                    return newOutsourced;
                }

            }
        } catch (Exception displayE) {
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
         * This method saves modified part.
         * @param actionEvent On Save button click
         * @throws IOException From
         * FXMLLoader
         */

        public void onSaveModify (ActionEvent actionEvent) throws IOException {
            Part part = getModPart();
            if (part != null) {
                returnMainScreen(actionEvent);
            }
        }
        /**
         * This method initializes the Modify Part Controller.
         * @param url Used to resolve relative paths for the root object, or null if the location is not known.
         * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
         */
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

            selectedPart = MainController.getModPart();
            index = MainController.getIndexPart();


            if (selectedPart instanceof Outsourced) {
                outsourcedBtn.setSelected(true);
                machineOrCompany.setText(((Outsourced) selectedPart).getCompanyName());
                modifyChange.setText("Company Name");
            }

            String nameTest = selectedPart.getName();
            nameTextField.setText(nameTest);

            Double priceTest = selectedPart.getPrice();
            costPartText.setText(String.valueOf(priceTest));

            Integer stockTest = selectedPart.getStock();
            inventoryTextField.setText(String.valueOf(stockTest));

            Integer minTest = selectedPart.getMin();
            minTextField.setText(String.valueOf(minTest));

            Integer maxTest = selectedPart.getMax();
            maxTextField.setText(String.valueOf(maxTest));


            if (selectedPart instanceof Outsourced) {
                outsourcedBtn.setSelected(true);
                machineOrCompany.setText(((Outsourced) selectedPart).getCompanyName());
                modifyChange.setText("Company Name");
            }
            if (selectedPart instanceof InHouse) {
                inHouseRadio.setSelected(true);
                machineOrCompany.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
                modifyChange.setText("Machine ID");
            }


        }
    }

