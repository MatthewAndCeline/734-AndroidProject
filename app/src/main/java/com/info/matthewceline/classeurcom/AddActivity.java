package com.info.matthewceline.classeurcom;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;


public class AddActivity extends ActionBarActivity {

    public CategoryCard newCatCard;
    public FinalCard newFinalCard;
    ArrayList<AbstractCard> cardsThatExist = new ArrayList<>();
    public CategoryCard currentCard;
    public String databaseName = "CardDatabase";
    private SQLiteDatabase db;
    public DBManager manager;
    int curId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Manage checkboxes
        final CheckBox categoryCheck = (CheckBox) findViewById(R.id.CheckBoxCategory);
        final CheckBox finalCheck = (CheckBox) findViewById(R.id.CheckBoxFinal);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            curId = extras.getInt("curent");
        }

        db = openOrCreateDatabase(databaseName, MODE_APPEND, null); // populate database.
        manager = new DBManager(db);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendNewCard(View button) {

        int defaultStartingID = 10;
        String cardType; // not necessary

        final CheckBox responseCategory = (CheckBox) findViewById(R.id.CheckBoxCategory);
        boolean categoryCheck = responseCategory.isChecked();

        final CheckBox responseFinal = (CheckBox) findViewById(R.id.CheckBoxFinal);
        boolean finalCheck = responseFinal.isChecked();

        final EditText name = (EditText) findViewById(R.id.EditTextCardName);
        String newCardName = name.getText().toString();

        final EditText picture = (EditText) findViewById(R.id.EditTextCardPicture);
        String newCardPicture = picture.getText().toString();






        if(newCardName.isEmpty() || newCardPicture.isEmpty()){ // && category ou final){

            // Display an alert box.
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("S'il vous plaît, regardez bien");
            builder1.setCancelable(true);
            builder1.setPositiveButton("JE COMPRENDS",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

        if(!finalCheck){
            newCatCard = new CategoryCard(defaultStartingID, newCardName, newCardPicture);
            cardType = newCatCard.getType(); // not needed

            // Add this new card to array list.
            manager.add(newCatCard,curId);
            cardsThatExist.add(newCatCard);
            defaultStartingID++;

        }
        else{
            newFinalCard = new FinalCard(defaultStartingID, newCardName, newCardPicture);
            cardType = newFinalCard.getType(); // not needed
            defaultStartingID++;

            // Add this new card to array list.
            cardsThatExist.add(newFinalCard);
            defaultStartingID++;
        }


    }
}
