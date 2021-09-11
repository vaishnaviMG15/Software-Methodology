package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This is a class that controls the user interaction with the second GUI.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */

public class Controller2 {

    Controller view1Controller;

    Order order = new Order();

    @FXML
    private ListView<OrderLine> orderDisplay;

    @FXML
    private Button clear;

    @FXML
    private Button removeLine;

    @FXML
    private Button back;

    @FXML
    private Button saveOrder;

    @FXML
    private Button addSameOrderLine;

    @FXML
    private TextField total;

    /**
     * This method is used to set up the orderDisplay list view and the total price in accordance with the
     * up to date state of the order. Basically, the GUI is updated according to the order.
     *
     */
    private void displayExactOrder (){

        ObservableList<OrderLine> items = FXCollections.observableArrayList();

        for (OrderLine element: order.getArray()){

            items.add(element);

        }

        orderDisplay.setItems(items);

        total.setText(String.format("%.02f",order.totalPrice()));

    }

    /**
     *
     * A new orderLine object is created with the cloning constructor in the order line class.
     * This is added to the order and the gui is updated.
     *
     * @param event representing the click of the add same order line button.
     */
    @FXML
    void addToOrder(ActionEvent event){

        OrderLine myItem = orderDisplay.getSelectionModel().getSelectedItem();

        OrderLine newItem = null;
        try{
            newItem = new OrderLine(myItem);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("No line selected.");
            alert.setContentText("Please select an order line to add again.");
            alert.showAndWait();
            return;
        }

        order.add(newItem);
        displayExactOrder();

    }

    /**
     *
     * Removes everything from the order and the gui is updated.
     *
     * @param event representing the click of the clear button.
     */

    @FXML
    void clearOrder(ActionEvent event){

        order.clear();
        displayExactOrder();

    }

    /**
     *
     * The order line corresponding to the one selected by the user is removed from the
     * order and the gui is updated.
     *
     * @param event representing the click of the removeLine button
     */
    @FXML
    void removeOrderLine(ActionEvent event){
        OrderLine myItem = orderDisplay.getSelectionModel().getSelectedItem();

        if (myItem == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("No line selected.");
            alert.setContentText("Please select an order line to delete.");
            alert.showAndWait();
            return;
        }
        order.remove(myItem);
        displayExactOrder();
    }

    /**
     * The order summary stage is closed by invoking the closeSecondStage method
     * of Controller instance.
     *
     * @param event representing the click of the back button
     */
    @FXML
    void goBack(ActionEvent event){

        view1Controller.closeSecondStage();

    }

    /**
     * Each order line of the order display list view is written to a file.
     *
     * @param event representing the click of the save order button
     */
    @FXML
    void saveOrderToFile(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        //write code to write to the file.
        PrintWriter pw;
        try {
            pw = new PrintWriter(targetFile);
        }catch(FileNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("File not found.");
            alert.setContentText("Please make sure that file path is mentioned correctly.");
            alert.showAndWait();
            return;
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("File not selected.");
            alert.setContentText("Please make sure that the file is selected.");
            alert.showAndWait();
            return;
        }


        for(OrderLine e: orderDisplay.getItems()){
            pw.println(e);
        }

        pw.println("Total Price: " + total.getText() + "");
        pw.close();

    }


    /**
     * This method starts the communication process. The instance of first view's controller is
     * received and the order information is retrieved from the first view. In accordance with this
     * initial information the initial state of the GUI is set up.
     *
     * @param controller instance required for communication
     */
    public void setView1Controller(Controller controller) {

        view1Controller = controller;
        order = view1Controller.getOrder();
        displayExactOrder();

    }
}
