package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.io.*;

/**
 * This class allows provides the methods to respond to the user interaction with the transaction manager GUI.
 * The class uses an instance of the AccountDatabase class to connect the model with the view.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class Controller {

    AccountDatabase bank = new AccountDatabase();

    @FXML
    private TextField firstName, lastName, month, day, year, balanceAmount ;

    @FXML
    private ToggleGroup accounts, accounts2, accounts3;

    @FXML
    private RadioButton checking, savings, moneyMarket;

    @FXML
    private Button openAccount, closeAccount, clear, clear2;

    @FXML
    private CheckBox directDeposit, loyalCustomer;

    @FXML
    private TextField firstName2, lastName2;

    @FXML
    private RadioButton checking2, savings2, moneyMarket2;

    @FXML
    private TextField fName, lName, amount;

    @FXML
    private Button depositAmount, withdrawAmount, clear3;

    @FXML
    private RadioButton checkingAccount, savingsAccount, moneyMarketAccount;

    @FXML
    private MenuItem importFile, exportFile, printAccounts, printAccountsByDateOpen, printAccountsByLastName;

    @FXML
    private TextArea console;


    /**
     * Clears all text fields and unselects all the buttons and checkboxes to bring the open tab's GUI back
     * to initial state.
     *
     * @param event representing the click of the clear button in open tab
     */
    @FXML
    void clearText(ActionEvent event) {
        firstName.setText(null);
        lastName.setText(null);
        day.setText(null);
        month.setText(null);
        year.setText(null);
        balanceAmount.setText(null);
        checking.setSelected(true);
        savings.setSelected(false);
        moneyMarket.setSelected(false);
        directDeposit.setSelected(false);
        loyalCustomer.setSelected(false);
        directDeposit.setDisable(false);
        loyalCustomer.setDisable(true);
    }

    /**
     * Clears all text fields and unselects all the buttons to bring the close tab's GUI back
     * to initial state.
     *
     * @param event representing the click of the clear button in close tab
     */
    @FXML
    void clearText2(ActionEvent event) {
        firstName2.setText(null);
        lastName2.setText(null);
        checking2.setSelected(false);
        savings2.setSelected(false);
        moneyMarket2.setSelected(false);
    }

    /**
     * Clears all text fields and unselects all the buttons to bring the deposit/withdraw tab's GUI back
     * to initial state.
     *
     * @param event representing the click of the clear button in deposit/withdraw tab
     */
    @FXML
    void clearText3(ActionEvent event) {
        fName.setText(null);
        lName.setText(null);
        amount.setText(null);
        checkingAccount.setSelected(false);
        savingsAccount.setSelected(false);
        moneyMarketAccount.setSelected(false);
    }

    /**
     * Helper method which retrieves first and last names from appropriate controls in the GUI
     * (depending on the caller method) and returns the corresponding Profile. However, if there is any
     * error in the input, null is returned.
     *
     * @param method which is an integer that represents the type of method which called this method.
     * @return holder of type profile corresponding to the account; null if holder cannot be found.
     */
    private Profile scanName(int method){

        String first_Name = "", last_Name = "";
        try {
            if (method == Constants.closeAccountMethod){//close
                first_Name = (firstName2.getText()).trim();
                last_Name = (lastName2.getText()).trim();
            }else if (method == Constants.openAccountMethod){ //open
                first_Name = (firstName.getText()).trim();
                last_Name = (lastName.getText()).trim();
            }else if (method == Constants.depositOrWithdrawAmountMethod){ //Deposit or withdraw
                first_Name = (fName.getText()).trim();
                last_Name = (lName.getText()).trim();
            }
        }catch(NullPointerException e){
            console.appendText("Please make sure you enter both a proper first name and last name.\n");
            return null;
        }

        if(first_Name.length() == 0 || last_Name.length() == 0){
            console.appendText("Make sure you entered a value for both first name and last name.\n");
            return null;
        }

        Profile customer = new Profile(first_Name, last_Name);
        return customer;
    }

    /**
     * Retrieves account information from GUI and closes the particular account.
     * If there is any error in account information input, a reason for the first error is displayed and
     * this method terminates without closing the account.
     *
     * @param event representing the click of the close button.
     */
    @FXML
    void close(ActionEvent event) {

        Profile customer = scanName(Constants.closeAccountMethod);
        if(customer == null){
            return;
        }

        Date defaultDate = new Date(Constants.randomYear, Constants.randomMonth,Constants.randomDay);
        Account account;

        if(checking2.isSelected()){
            account = new Checking(customer, Constants.randomBalance, defaultDate, Constants.randomBoolean);
        }
        else if (savings2.isSelected()) {
            account = new Savings(customer, Constants.randomBalance, defaultDate, Constants.randomBoolean);
        }
        else if (moneyMarket2.isSelected()) {
            account = new MoneyMarket(customer, Constants.randomBalance, defaultDate, Constants.initialWithdrawals);
        }
        else {
            console.appendText("Please select an account type.\n");
            return;
        }

        if(bank.remove(account)){
            console.appendText("Account closed and removed from the database.\n");
        }else{
            console.appendText("Account does not exist.\n");
        }

    }

    /**
     * Unselects the loyal customer and direct deposit checkboxes to bring them back to initial state.
     * Enables the direct deposit option and disables the loyal customer option.
     *
     * @param event representing the selection of checking radio button in open tab.
     */
    @FXML
    void disableOnChecking(ActionEvent event) {

        loyalCustomer.setDisable(true);
        directDeposit.setDisable(false);
        loyalCustomer.setSelected(false);
        directDeposit.setSelected(false);

    }

    /**
     * Unselects the loyal customer and direct deposit checkboxes to bring them back to initial state.
     * Disables both the direct deposit and loyal customer option.
     *
     * @param event representing the selection of money market radio button in open tab.
     */
    @FXML
    void disableOnMoneyMarket(ActionEvent event) {

        loyalCustomer.setDisable(true);
        directDeposit.setDisable(true);
        loyalCustomer.setSelected(false);
        directDeposit.setSelected(false);

    }

    /**
     * Unselects the loyal customer and direct deposit checkboxes to bring them back to initial state.
     * Enables the loyal customer option and disables the direct deposit option.
     *
     * @param event representing the selection of savings radio button in open tab.
     */
    @FXML
    void disableOnSavings(ActionEvent event) {

        directDeposit.setDisable(true);
        loyalCustomer.setDisable(false);
        loyalCustomer.setSelected(false);
        directDeposit.setSelected(false);

    }

    /**
     * Retrieves account information from GUI and opens the particular account.
     * If there is any error in account information input, a reason for the first error is displayed and
     * this method terminates without opening the account.
     *
     * @param event representing the click of the open button.
     */
    @FXML
    void open(ActionEvent event) {

        Profile customer = scanName(Constants.openAccountMethod);
        if(customer == null){
            return;
        }
        Account account;
        double balanceValue;
        Date date;

        try {
            int monthValue = Integer.parseInt(month.getText());
            int dayValue = Integer.parseInt(day.getText());
            int yearValue = Integer.parseInt(year.getText());
            date = new Date (yearValue, monthValue, dayValue);
        }catch(NumberFormatException e){
            console.appendText("Non-numeric data has been entered for date. Please enter integers.\n");
            return;
        }

        if(!date.isValid()){
            console.appendText(date.toString() + " is not a valid date.\n");
            return;
        }

        try {
            balanceValue = Double.parseDouble(balanceAmount.getText());
        }catch(NumberFormatException e){
            console.appendText("Non-numeric data has been entered: please enter a decimal for balance.\n");
            return;
        }

        if(balanceValue <= 0){
            console.appendText("You cannot open an account with a balance less than or equal to $0.\n");
            return;
        }

        if(checking.isSelected()){
            account = new Checking(customer, balanceValue, date, directDeposit.isSelected());
        }
        else if (savings.isSelected()) {
            account = new Savings(customer, balanceValue, date, loyalCustomer.isSelected());
        }
        else if (moneyMarket.isSelected()) {
            account = new MoneyMarket(customer, balanceValue, date, Constants.initialWithdrawals);
        }
        else {
            console.appendText("Please select an account type.\n");
            return;
        }

        if(bank.add(account)){
            console.appendText("Account opened and added to database.\n");
        }else{
            console.appendText("Account is already in the database.\n");
        }
    }

    /**
     * Retrieves account and amount information from GUI and attempts to withdraw the amount from the particular account and displays appropriate output.
     * If there is any error in account or amount information input, a reason for the first error is displayed and
     * this method terminates without performing any withdrawal.
     *
     * @param event representing the click of the withdraw button.
     */
    @FXML
    void withdraw(ActionEvent event){

        Profile customer = scanName(Constants.depositOrWithdrawAmountMethod);
        if(customer == null){
            return;
        }
        Account account;
        Date defaultDate = new Date(Constants.randomYear, Constants.randomMonth,Constants.randomDay);
        double amountValue;

        try {
            amountValue = Double.parseDouble(amount.getText());
        }
        catch(NumberFormatException e) {
            console.appendText("Invalid type for amount; please enter a decimal value.\n");
            return;
        }

        if(amountValue <= 0){
            console.appendText("You cannot withdraw an amount less than or equal to $0.\n");
            return;
        }

        if(checkingAccount.isSelected()){
            account = new Checking(customer, Constants.randomBalance, defaultDate, Constants.randomBoolean);
        }
        else if (savingsAccount.isSelected()) {
            account = new Savings(customer, Constants.randomBalance, defaultDate, Constants.randomBoolean);
        }
        else if (moneyMarketAccount.isSelected()) {
            account = new MoneyMarket(customer, Constants.randomBalance, defaultDate, Constants.initialWithdrawals);
        }
        else {
            console.appendText("Please select an account type.\n");
            return;
        }

        int value = bank.withdrawal(account,amountValue);
        if(value == 1){
            console.appendText("Insufficient funds.\n");
        }else if (value == Constants.indexNotFound){
            console.appendText("Account does not exist.\n");
        }else if (value == 0){
            console.appendText(String.format("%.02f withdrawn from account\n", amountValue));
        }

    }

    /**
     * Retrieves account and amount information from GUI and attempts to deposit the amount to the particular account and displays appropriate output.
     * If there is any error in account or amount information input, a reason for the first error is displayed and
     * this method terminates without performing any withdrawal.
     *
     * @param event representing the click of the deposit button.
     */
    @FXML
    void deposit(ActionEvent event){

        Profile customer = scanName(Constants.depositOrWithdrawAmountMethod);
        if(customer == null){
            return;
        }
        Account account;
        Date defaultDate = new Date(Constants.randomYear, Constants.randomMonth,Constants.randomDay);
        double amountValue;

        try {
            amountValue = Double.parseDouble(amount.getText());
        }
        catch(NumberFormatException e) {
            console.appendText("Invalid type for amount; please enter a decimal value.\n");
            return;
        }

        if(amountValue <= 0){
            console.appendText("You cannot deposit an amount less than or equal to $0.\n");
            return;
        }

        if(checkingAccount.isSelected()){
            account = new Checking(customer, Constants.randomBalance, defaultDate, Constants.randomBoolean);
        }
        else if (savingsAccount.isSelected()) {
            account = new Savings(customer, Constants.randomBalance, defaultDate, Constants.randomBoolean);
        }
        else if (moneyMarketAccount.isSelected()) {
            account = new MoneyMarket(customer, Constants.randomBalance, defaultDate, Constants.initialWithdrawals);
        }
        else {
            console.appendText("Please select an account type from checking, savings or Money Market.\n");
            return;
        }

        if(bank.deposit(account, amountValue)){
            console.appendText(String.format("%.02f deposited to account\n", amountValue));
        }else{
            console.appendText("Account does not exist.\n");
        }

    }

    /**
     *
     * Retrieves a pointer to the source file based on the users selection. Then imports the information from the file to the
     * account database bank by parsing the information in the source file and adding all the accounts in the source file to bank.
     * If the source file is not found, the exception is handled by outputting "file not found."
     *
     * @param event representing the click of the import menu option.
     */
    @FXML
    void importFile(ActionEvent event) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
        Scanner sc;
        try{
            sc = new Scanner(sourceFile);
        }catch(FileNotFoundException e){
            console.appendText("File was not found.\n");
            return;
        }catch(NullPointerException e){
            console.appendText("Import did not occur because a proper file was not chosen.\n");
            return;
        }
        //write code to read from the file.
        sc.useDelimiter(",|\\n");
        while (sc.hasNext()){

            Account account = null;

            String accountTypeString = sc.next();
            char accountType = accountTypeString.charAt(0);
            String first_name = (sc.next()).trim();
            String last_name = (sc.next()).trim();
            Profile customer = new Profile(first_name, last_name);

            double balanceInAccount = sc.nextDouble();

            String date = sc.next();
            StringTokenizer tokenizedDate = new StringTokenizer(date, "/");
            int monthOfDate = Integer.parseInt(tokenizedDate.nextToken());
            int dayOfDate = Integer.parseInt(tokenizedDate.nextToken());
            int yearOfDate = Integer.parseInt(tokenizedDate.nextToken());
            Date open_date = new Date(yearOfDate, monthOfDate, dayOfDate);

            if (accountType == Constants.moneyMarketDecider) {
                int numberOfWithdrawals = sc.nextInt();
                account = new MoneyMarket(customer, balanceInAccount, open_date, numberOfWithdrawals);
            }
            else if (accountType == Constants.checkingDecider) {
                boolean directDeposit = sc.nextBoolean();
                account = new Checking(customer, balanceInAccount, open_date, directDeposit);
            }
            else if (accountType == Constants.savingsDecider) {
                boolean isLoyalCustomer = sc.nextBoolean();
                account = new Savings(customer, balanceInAccount, open_date, isLoyalCustomer);
            }
            bank.add(account);
        }
        sc.close();
        console.appendText("Imported successfully.\n");

    }

    /**
     * Retrieves a pointer to the target file based on the users selection. Then exports the information from the
     * account database bank to the target file by calling the export method of Accounts DataBase class.
     * If the target file is not found, the exception is handled by outputting "file not found."
     *
     * @param event representing the click of the export menu option.
     */
    @FXML
    void exportFile(ActionEvent event) {
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
            console.appendText("File was not found.\n");
            return;
        }catch(NullPointerException e){
            console.appendText("Export did not occur because a proper file was not chosen.\n");
            return;
        }
        String output = bank.export();
        pw.println(output);
        pw.close();
        console.appendText("Exported Successfully.\n");
    }

    /**
     * Prints out the information of all the accounts to the text area by calling the printAccounts method of account database.
     *
     * @param event representing the click of the Accounts menu option in Print menu.
     */
    @FXML
    void print(ActionEvent event){

        String output = bank.printAccounts();
        console.appendText(output);

    }

    /**
     * Prints out the updated information of all the accounts in ascending order of the holder's last name to the text area by calling the
     * printByLastName method of account database.
     *
     * @param event representing the click of the Statements by Last Name menu option in Print menu.
     */
    @FXML
    void printByLastName(ActionEvent event){
        String output = bank.printByLastName();
        console.appendText(output);
    }

    /**
     * Prints out the updated information of all the accounts in ascending order of the open date to the text area by calling the
     * printByDateOpen method of account database.
     *
     * @param event representing the click of the Statements by Last Name menu option in Print menu.
     */
    @FXML
    void printByDateOpen(ActionEvent event){
        String output = bank.printByDateOpen();
        console.appendText(output);
    }

}

