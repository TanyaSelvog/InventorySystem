package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/** This class creates an application for inventory management.
 * */
public class Main extends Application {
    /**This start method creates the stage for the FXML and displays main scene.
     *
     * @param primaryStage stage
     * @throws Exception FXMLLoader
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }

    /**This main method launches the application.
     * @param args args
     *<p>
     * <b>Javadoc folder is in top-level of project's assets New features and runtime error comments are in AddPartController class.</b>
     *</p>
     */
    public static void main(String[] args) {
        launch(args);
    }
}
