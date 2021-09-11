package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class acts as a driver class and runs the program by loading the view and displaying the GUI to the user.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class Main extends Application {

    /**
     * This function loads the GUI information from the fxml file and displays the GUI.
     *
     * @param primaryStage to set up the scene for our GUI
     * @throws Exception if gui is not loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Transaction Manager");
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(root, 750, 500));
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
