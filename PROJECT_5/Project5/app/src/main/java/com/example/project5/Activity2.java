package com.example.project5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

/**
 * This is a class that acts as a controller for the second screen in the app.
 * It displays required information of the museum selected in the parent screen.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class Activity2 extends AppCompatActivity {

    TextView museumName, adultPrice, seniorPrice, studentPrice;
    Spinner spinner1, spinner2, spinner3;
    ImageView museumImage;
    Button calculateTicketPrice;
    TextView ticketPrice, salesTax, ticketTotal;

    int message;

    /**
     *
     * This method initializes the setting of the second screen.
     * The method retrieves the museum information (museum selected by the user) from the first screen.
     * It retrieves the references of all the required controls.
     * According to the museum selected it displays the corresponding name of museum, clickable image of museum,
     * and the prices for adult, senior and student tickets for that museum.
     * Additionally it initializes 3 spinner objects for the user to choose the number of each type of tickets.
     * Also, a short toast message is displayed which specifies that the number of tickets of each types that
     * can be book is at the maximum 5.
     *
     * @param savedInstanceState, is a Bundle object used to create the initial state of the activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        message = (getIntent()).getIntExtra(MainActivity.EXTRA_MESSAGE, -1);

        museumName = findViewById(R.id.museumName);
        adultPrice = findViewById(R.id.adultPrice);
        studentPrice = findViewById(R.id.studentPrice);
        seniorPrice = findViewById(R.id.seniorPrice);
        museumImage = findViewById(R.id.museumImage);

        if(message == 0) {
            museumName.setText(getResources().getStringArray(R.array.museums)[0]);
            adultPrice.setText(getResources().getStringArray(R.array.Labels_for_museum1)[0]);
            seniorPrice.setText(getResources().getStringArray(R.array.Labels_for_museum1)[1]);
            studentPrice.setText(getResources().getStringArray(R.array.Labels_for_museum1)[2]);
            museumImage.setImageResource(getResources().getIdentifier("natural_history" , "drawable", "com.example.project5"));

        }else if (message == 1){
            museumName.setText(getResources().getStringArray(R.array.museums)[1]);
            adultPrice.setText(getResources().getStringArray(R.array.Labels_for_museum2)[0]);
            seniorPrice.setText(getResources().getStringArray(R.array.Labels_for_museum2)[1]);
            studentPrice.setText(getResources().getStringArray(R.array.Labels_for_museum2)[2]);
            museumImage.setImageResource(getResources().getIdentifier("moma" , "drawable", "com.example.project5"));

        }else if (message == 2){
            museumName.setText(getResources().getStringArray(R.array.museums)[2]);
            adultPrice.setText(getResources().getStringArray(R.array.Labels_for_museum3)[0]);
            seniorPrice.setText(getResources().getStringArray(R.array.Labels_for_museum3)[1]);
            studentPrice.setText(getResources().getStringArray(R.array.Labels_for_museum3)[2]);
            museumImage.setImageResource(getResources().getIdentifier("sea_air_space" , "drawable", "com.example.project5"));

        } else if (message == 3){
            museumName.setText(getResources().getStringArray(R.array.museums)[3]);
            adultPrice.setText(getResources().getStringArray(R.array.Labels_for_museum4)[0]);
            seniorPrice.setText(getResources().getStringArray(R.array.Labels_for_museum4)[1]);
            studentPrice.setText(getResources().getStringArray(R.array.Labels_for_museum4)[2]);
            museumImage.setImageResource(getResources().getIdentifier("ny_hall_of_science" , "drawable", "com.example.project5"));

        }else{
            return;
        }

        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tickets, android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);

        ticketPrice = findViewById(R.id.ticketPrice);
        salesTax = findViewById(R.id.salesTax);
        ticketTotal = findViewById(R.id.ticketTotal);
    }

    /**
     * calculate() finds the total price of a provided number of adult, senior and student tickets by
     * first fetching their adult price, senior price and student price from Constants,
     * depending on which index they are in (the index translates to the museum names, in the order
     * they appear on MainActivity, with 0 being AMNH, 1 being MOMA, 2 being Intrepid Sea and Space, and
     * 3 being NY Hall of Science). We then format the price, salesTaxAmount and totalTicketPrice using the
     * wrapper String class’s format method, and return this value to its respective labels on our Activity2 GUI.
     * @param view, the ListView we are currently on
     */
    public void calculate(View view){

        int numOfAdults = spinner1.getSelectedItemPosition();
        int numOfSeniors = spinner2.getSelectedItemPosition();
        int numOfStudents = spinner3.getSelectedItemPosition();

        double price = 0.0;
        double salesTaxAmount = 0.0;
        double totalTicketPrice = 0.0;

        if(message == 0){

            price = (Constants.apAMNH * numOfAdults) + (Constants.sepAMNH * numOfSeniors) + (Constants.stpAMNH * numOfStudents);

        }else if (message == 1){

            price = (Constants.apMOMA * numOfAdults) + (Constants.sepMOMA * numOfSeniors) + (Constants.stpMOMA * numOfStudents);

        }else if (message == 2){

            price = (Constants.apSAS * numOfAdults) + (Constants.sepSAS * numOfSeniors) + (Constants.stpSAS * numOfStudents);

        }else if(message == 3){

            price = (Constants.apNYHS * numOfAdults) + (Constants.sepNYHS * numOfSeniors) + (Constants.stpNYHS * numOfStudents);

        }else{
            return;
        }

        salesTaxAmount = price*Constants.tax;
        totalTicketPrice = price+salesTaxAmount;

        String priceString = String.format("%.02f", price);
        ticketPrice.setText("$" + priceString);

        String salesTaxAmountString = String.format("%.02f", salesTaxAmount);
        salesTax.setText("$" + salesTaxAmountString);

        String totalTicketPriceString = String.format("%.02f", totalTicketPrice);
        ticketTotal.setText("$" + totalTicketPriceString);

    }

    /**
     * goToWebsite() redirects the user to the museum website of the particular museum we are looking at
     * once they click the museum’s image.
     * We do so by linking the URL of the museum to the museum’s image.
     * @param view, the ListView we are currently on
     */
    public void goToWebsite(View view){

        if(message == 0){

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amnh.org/")));

        }else if (message == 1){

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.moma.org/")));

        }else if (message == 2){

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.intrepidmuseum.org/")));

        }else if(message == 3){

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://nysci.org/")));

        }else{
            return;
        }

    }
}
