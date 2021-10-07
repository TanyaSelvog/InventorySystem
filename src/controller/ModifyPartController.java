package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {


    public RadioButton inHouseRadio;
    public ToggleGroup modifygroup;
    public RadioButton outsourcedBtn;
    public TextField idTextField;
    public Label modifyChange;
    public Label addPartLBL;
    public Button cancelPartAddBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(getClass().getName() + "in initialize!");
//testing for TE this needs to be blank
    }

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

    public int testMe(){
        int x = 5;

        System.out.println(x);
        return x;
    }
    }



