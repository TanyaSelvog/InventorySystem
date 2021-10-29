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
     * Text field for Machine ID
     */
    public TextField machineIDText;
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
     * @param actionEvent In-House radio button is selected
     */
    public void onInHouseModify(ActionEvent actionEvent) {
        modifyChange.setText("Machine ID");
    }

    /**
     * This method changes text to Company Name when Outsourced radio button is selected.
     * @param actionEvent Outsourced radio button is selected
     */
    public void onOutsourcedModify(ActionEvent actionEvent) {
        modifyChange.setText("Company Name");
    }

    /**
     * This method returns user to main window when they click on cancel.
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
     * This method returns a part
     * @return new part
     */

    private Part getModPart () {
        boolean isValid = true;
        List<String> messages = new ArrayList<>();
        Double price = null;
        Integer stock = null;
        Integer max = null;
        Integer min = null;
        Integer machineID = null;
        String companyName = null;


        String name = nameTextField.getText();
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


        String stockText = inventoryTextField.getText();
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

        if (modifygroup.getSelectedToggle() == null) {
            isValid = false;
            messages.add("Select either In-House or Outsourced");
        }

        if (inHouseRadio.isSelected()) {
            String machineText = machineIDText.getText();
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
            String company = machineIDText.getText();
            if (company.isEmpty()) {
                isValid = false;
                messages.add("No data in Company Name field");
            }

        }

        if (isValid) {

            Integer id = Inventory.addID();
            if (inHouseRadio.isSelected()) {
                machineID = Integer.parseInt(machineIDText.getText());
                InHouse newPartInHouse = new InHouse(id, name, price, stock, min, max, machineID);
                Inventory.updatePart(index, newPartInHouse);
                return newPartInHouse;
            } else if (outsourcedBtn.isSelected()) {
                companyName = machineIDText.getText();
                Outsourced newOutsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.updatePart(index, newOutsourced);
                return newOutsourced;
            }

        }
        Alert alert = new Alert(Alert.AlertType.ERROR, String.join("\n", messages));
        alert.showAndWait();
        return null;
    }
    /**
     * This method saves modified part.
     * @param actionEvent On Save button click
     * @throws IOException From
     * FXMLLoader
     */

    public void onSaveModify(ActionEvent actionEvent) throws IOException {
        Part part = getModPart();
        if (part != null){
            returnMainScreen(actionEvent);
        }
    }
    /**
    * This method initializes the Modify Part Controller.
    * @param url Used to resolve relative paths for the root object, or null if the location is not known.
    * @param resourceBundle Used to localize the root object, or null if the root object was not localized.
    */
@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedPart = MainController.getModPart();
        index = MainController.getIndexPart();

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

        if (selectedPart instanceof InHouse) {
            inHouseRadio.setSelected(true);
            machineIDText.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
            modifyChange.setText("Machine ID");
        } else {
            outsourcedBtn.setSelected(true);
            machineIDText.setText(String.valueOf(((Outsourced) selectedPart).getCompanyName()));
            modifyChange.setText("Company Name");
        }



    }
}

