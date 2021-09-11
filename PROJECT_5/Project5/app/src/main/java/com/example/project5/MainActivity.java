package com.example.project5;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This is a class that acts as a controller for the first screen in the app.
 * It allows the user to choose from a list of museums and then invokes the next screen.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView museumTickets;
    public Intent intent;
    public static final String EXTRA_MESSAGE = "com.example.project5.extra.MESSAGE";

    /**
     * The on create method initializes the content of the activity.
     * It sets the items (museums) of the listview and displays the list view to the user.
     * Additionally it makes this class the listener of the list view.
     *
     * @param savedInstanceState is a Bundle object used to create the initial state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        museumTickets = findViewById(R.id.museumTickets);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.museums, android.R.layout.simple_gallery_item);
        museumTickets.setAdapter(adapter);

        museumTickets.setOnItemClickListener(this);
    }

    /**
     * onItemClick() invokes Activity 2 from MainActivity by creating an intent object and
     * providing it with the correct screen to go to and information to carry
     * by fetching the position of the message.
     * Note that the EXTRA_MESSAGE is a way to mention that we are sending extra information.
     * @param parent, AdapterView parent, the parent class of ListView
     * @param view, node (list view in this case)
     * @param position, location was selected in the list view
     * @param id, float representing id of item at param position
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        intent = new Intent(this, Activity2.class);
        int message = position;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    /**
     * getIntent() is a getter method that fetches the intent of an Activity class.
     * In our case, our intent is a simple message object that communicates between our two
     * Android activities.
     *
     * @return intent, the intent we want to fetch
     */
   public Intent getIntent() {
        return intent;
   }
}