package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class ModifyPartController implements Initializable {


    public RadioButton inHouseRadio;
    public ToggleGroup modifygroup;
    public RadioButton outsourcedBtn;
    public TextField idTextField;
    public Label modifyChange;
    public Label addPartLBL;
    public Button cancelPartAddBtn;
    public Button savePartBtn;
    public TextField minTextField;
    public TextField machineIDText;
    public TextField maxTextField;
    public TextField costPartText;
    public TextField inventoryTextField;
    public TextField nameTextField;

    private Part selectedPart;
    private InHouse testIH;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO Clean up & add IN/OUT choices & ID

        selectedPart = MainController.getModPart();

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


    public void onInHouseModify(ActionEvent actionEvent) {
        modifyChange.setText("Machine ID");
    }

    //DONT BELIEVE THIS IS NEEDED ANYMORE (AS OF AFTERNOON 10.7.2021)
    public void onOutsourcedModify(ActionEvent actionEvent) {
        modifyChange.setText("Company Name");
    }


    public void onCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();
    }


    public void onSaveModify(ActionEvent actionEvent) {

Part updatePart = getPart();
    }

    private Part getPart() {
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


        if (inHouseRadio.isSelected()) {
            String machineText = modifyChange.getText();
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
            String company = modifyChange.getText();
            if (company.isEmpty()) {
                isValid = false;
                messages.add("No data in Company Name field");
            }

        }

/**        if (isValid) {

            if (inHouseRadio.isSelected()) {
                InHouse newPartInHouse = new InHouse(id, name, price, stock, min, max, machineID);
                Inventory.addPart(newPartInHouse);
                return newPartInHouse;

            } else if (outsourcedBtn.isSelected()) {
                Outsourced newOutsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.addPart(newOutsourced);
                return newOutsourced;
            }
 */
        Alert alert = new Alert(Alert.AlertType.ERROR, String.join("\n", messages));
        alert.showAndWait();

        return null;
        }
    }





