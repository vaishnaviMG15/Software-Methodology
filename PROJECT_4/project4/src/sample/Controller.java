package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This is a class that controls the user interaction with the first GUI.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class Controller implements Initializable {

    private Order order = new Order();

    Stage secondStage;

    @FXML
    private ComboBox<String> Type;

    @FXML
    private ListView<Extra> SelectionsList;

    @FXML
    private TextArea reqIngredients;

    @FXML
    private Button Add;

    @FXML
    private Button Remove;

    @FXML
    private Button Clear;

    @FXML
    private ImageView Picture;

    @FXML
    private TextField Price;

    @FXML
    private Button addToOrder;

    @FXML
    private Button showOrder;

    @FXML
    private ListView<Extra> SelectedList;

    /**
     * The initialize method sets the initial scene on the GUI. It sets the GUI in a state of ordering a
     * chicken burger with no extras.
     *
     * @param location, where the resource/scene is
     * @param resource, resource which are needed for the implementation of initializing the UI
     */
    @Override
    public void initialize (URL location, ResourceBundle resource){

        //initialize and set initial value of combo box
        ObservableList<String> items =
                FXCollections.observableArrayList("chicken", "beef", "fish");
        Type.setItems(items);
        Type.setValue("chicken");

        //Set the image, basic ingredients, and price fields.
        reqIngredients.setPromptText(Chicken.basicIngredient1 + ", " + Chicken.basicIngredient2+ ", " + Chicken.basicIngredient3 +"");

        Image chickenChoice = new Image("file:chickenChoice.jpeg");
        try{
            Picture.setImage(chickenChoice);
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("Image not found.");
            alert.setContentText("Please make sure image path is right.");
            alert.showAndWait();
        }


        Price.setText(String.format("%.02f", Chicken.basicPrice));
        initializeLists();

    }

    /**
     * This method initializes the state of the list views. The Selections List would initially be full
     * with all possible extras. the Selected list is initially set to empty.
     *
     */
    private void initializeLists(){
        //Initialize SelectionList's List view as with all extras
        ObservableList<Extra> extras = FXCollections.observableArrayList(Extra.eggs, Extra.coleslaw,
                Extra.onion, Extra.mayonnaise, Extra.lettuce, Extra.hummus, Extra.guacamole, Extra.tomatoes,
                Extra.ham, Extra.bacon);
        SelectionsList.setItems(extras);

        //Initialize SelectedList's List view as empty
        ObservableList<Extra> extrasSelected = FXCollections.observableArrayList();
        SelectedList.setItems(extrasSelected);
    }



    /**
     * Based on the kind of the sandwich selected, the following changes are made:
     *
     * 1) The picture is modified
     * 2) The corresponding basic ingredients are displayed
     * 3) The corresponding basic price is set in Price field
     * 4) The list views go back to initial state.
     *
     * @param event representing the selection of an item in the sandwich Type combo box.
     */

    @FXML
    void updateGUI(ActionEvent event) {

        //get the item that is selected
        String selected = Type.getSelectionModel().getSelectedItem();

        //based on that item, change the picture, basic ingredients, and price
        if(selected.equals("chicken")) {

            reqIngredients.setPromptText(Chicken.basicIngredient1 + ", " + Chicken.basicIngredient2+ ", " + Chicken.basicIngredient3 +"");
            Image chickenChoice = new Image("file:chickenChoice.jpeg");
            Picture.setImage(chickenChoice);
            Price.setText(String.format("%.02f", Chicken.basicPrice));

        }
        else if(selected.equals("beef")) {

            reqIngredients.setPromptText(Beef.basicIngredient1 + ", " + Beef.basicIngredient2 + ", " + Beef.basicIngredient3 +"");
            Image beefChoice = new Image("file:beefChoice.jpeg");//fill in url
            Picture.setImage(beefChoice);
            Price.setText(String.format("%.02f", Beef.basicPrice));

        }
        else if(selected.equals("fish")) {

            reqIngredients.setPromptText(Fish.basicIngredient1+", "+ Fish.basicIngredient2+ ", " + Fish.basicIngredient3 +"");
            Image fishChoice = new Image("file:fishChoice.jpeg");//fill in url
            Picture.setImage(fishChoice);
            Price.setText(String.format("%.02f", Fish.basicPrice));
        }


        initializeLists();

    }

    /**
     *
     * An item is added to the extras of the sandwich.
     * The following actions are performed, unless the total extras cross 6 items:
     *
     * 1) The extra is removed from Selections List
     * 2) The extra is added to Selected List
     * 3) price is updated
     *
     * @param event representing the click of the Add button (selection of an extra)
     */
    @FXML
    void addSelection(ActionEvent event) {

        //get items selected
        Extra myItem = SelectionsList.getSelectionModel().getSelectedItem();

        if (myItem == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("No extras selected.");
            alert.setContentText("Please select an extra to add to your sandwich.");
            alert.showAndWait();
            return;
        }

        //Check number of extras and add to SelectedList accordingly. Also, update price.
        int total = 1 + (SelectedList.getItems()).size();
        if(total > Sandwich.MAX_EXTRAS){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("Exceeded maximum number of extras");
            alert.setContentText("You can only select up to 6 extra ingredients.");
            alert.showAndWait();
            return;
        }else{
            SelectionsList.getItems().remove(myItem);
            SelectedList.getItems().add(myItem);

            Price.setText(String.format("%.02f",(Double.parseDouble(Price.getText()) + Sandwich.PER_EXTRA)));
        }

    }

    /**
     *
     * An item is removed from the extras of the sandwich.
     * The following actions are performed:
     *
     * 1) The extra is removed from Selected List
     * 2) The extra is added back to Selections List
     * 3) price is updated
     *
     * @param event representing the click of the remove button (removal of an extra)
     */
    @FXML
    void removeSelection(ActionEvent event) {

        //get items selected
        Extra myItem = SelectedList.getSelectionModel().getSelectedItem();
        if (myItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("No extra item selected.");
            alert.setContentText("Please select an extra item to remove.");
            alert.showAndWait();
            return;
        }
        //remove items from 2nd list view and add them to first list view. Also, update price.
        SelectionsList.getItems().add(myItem);
        SelectedList.getItems().remove(myItem);

        Price.setText(String.format("%.02f",(Double.parseDouble(Price.getText()) - Sandwich.PER_EXTRA)));
    }

    /**
     *
     * All selected extras are removed. The price is updated
     * accordingly and the list views go back to original state.
     *
     * @param event representing the click of the clear button
     */
    @FXML
    void clearSelection(ActionEvent event) {


        String selected = Type.getSelectionModel().getSelectedItem();

        if(selected.equals("chicken")){
            Price.setText(Chicken.basicPrice + "");
        }else if(selected.equals("beef")){
            Price.setText(Beef.basicPrice + "");
        }else{
            Price.setText(Fish.basicPrice + "");
        }

        initializeLists();

    }

    /**
     *
     * An order line representing the current sandwich selection is created an added to the order
     * instance variable.
     *
     * @param event representing the click of the addToOrder button
     */
    @FXML
    void addingToOrder(ActionEvent event) {

        Sandwich cur = null;
        String selected = Type.getSelectionModel().getSelectedItem();

        if(selected.equals("chicken")){
            cur = new Chicken();
        }else if(selected.equals("beef")){
            cur = new Beef();
        }else{
            cur = new Fish();
        }



        for(Extra element: SelectedList.getItems()){
            cur.add(element);
        }


        OrderLine line = new OrderLine(cur);

        //add line to the order.
        order.add(line);

    }

    /**
     *
     * The order summary is loaded and opened. A reference of this controller instance
     * is sent to the controlled of order summary page to enable data communication
     * between the two interfaces.
     *
     * @param event representing the click of the showOrder button
     */
    @FXML
    void displayOrder(ActionEvent event) {

        //connecting to other controller

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));

        try {
            loader.load();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("Failed to load.");
            alert.setContentText("The next page failed to load.");
            alert.showAndWait();
            return;
        }

        Controller2 controller2 = loader.getController();

        controller2.setView1Controller(this);

        Parent root = loader.getRoot();
        secondStage = new Stage();
        secondStage.setTitle("Order Summary");
        secondStage.setScene(new Scene(root, 750, 500));
        secondStage.show();


    }

    /**
     *
     * The instance of the second stage is closed. This occurs when the user
     * wants to come back to the initial gui.
     *
     */
    public void closeSecondStage(){

        secondStage.close();

    }

    /**
     *
     * Getter method for the instance of the current order.
     *
     * @return order, an instance of Order class.
     */
    public Order getOrder(){

        return order;

    }



}

