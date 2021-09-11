package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is a class that runs the program.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */

public class Main extends Application {

    /**
     * This function loads the GUI information from the sample.fxml file and displays the GUI.
     *
     * @param primaryStage to set up the scene for our first GUI
     * @throws Exception if gui is not loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("My Sandwich Store");
        primaryStage.setScene(new Scene(root, 650, 500));
        primaryStage.show();
    }


    /**
     * driver of the main class.
     *
     * @param args input stream
     */
    public static void main(String[] args) {
        launch(args);
    }
}
