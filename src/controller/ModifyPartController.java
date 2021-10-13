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
    private int index;


    public void onInHouseModify(ActionEvent actionEvent) {
        modifyChange.setText("Machine ID");
    }

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

    public void returnMainScreen(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public void onSaveModify(ActionEvent actionEvent) throws IOException {

        try {
            int id = selectedPart.getId();
            String name = nameTextField.getText();
            Double price = Double.parseDouble(costPartText.getText());
            int stock = Integer.parseInt(inventoryTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int machineID;
            String companyName;

            if (inHouseRadio.isSelected()) {
                machineID = Integer.parseInt(machineIDText.getText());
                InHouse newPartInHouse = new InHouse(id, name, price, stock, min, max, machineID);
                Inventory.updatePart(index, newPartInHouse);
            }else if(outsourcedBtn.isSelected()) {
                companyName = machineIDText.getText();
                Outsourced newOutsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.updatePart(index, newOutsourced);



            }

        } catch (Exception e) {
            System.out.println("Modify part is not working like it should.");
        }

        System.out.println(index);
        returnMainScreen(actionEvent);
    }



@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
    //TODO Clean up & add IN/OUT choices & ID

    selectedPart = MainController.getModPart();
    index = MainController.getIndexPart();

    //System.out.println(index);
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

//            Integer.parseInt(machineText);
        modifyChange.setText("Machine ID");
    } else {
        outsourcedBtn.setSelected(true);
        machineIDText.setText(String.valueOf(((Outsourced) selectedPart).getCompanyName()));
        modifyChange.setText("Company Name");
    }

    System.out.println(selectedPart.toString());
    System.out.println("Now the index is: " + index);


    }
}

