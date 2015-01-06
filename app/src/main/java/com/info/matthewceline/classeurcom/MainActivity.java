package com.info.matthewceline.classeurcom;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    // the root card
    private final CategoryCard data = new CategoryCard(0,"root","root");
    // the last CategoryCard selected by user
    public CategoryCard currentCard = data;
    // the finalCards selected by user to construct the sentence
    private final ArrayList<FinalCard> path = new ArrayList<>();

    //for database management
    public String databaseName = "CardDatabase";
    private SQLiteDatabase db;
    public DBManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //create database manager
        db = openOrCreateDatabase(databaseName, MODE_APPEND, null);
        manager = new DBManager(db);

        //create objects using datas in database
        manager.createCardsFromDatabase(data);

        //displaying the cards and associating buttons with actions
        updateUI();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                       Listeners                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private class ClicCard implements View.OnClickListener {


            @Override
            public void onClick(View v) {
                int id = v.getId();
                //path.add(data.getChilds().get(id));
                if (currentCard.getChilds().get(id).getType() == "C") {
                    currentCard = (CategoryCard) currentCard.getChilds().get(id);
                }
                else {
                    path.add((FinalCard) currentCard.getChilds().get(id));
                }
                updateUI();
            }


    }

    private class LongClicCard implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }

    }

    private class ClicAddBt implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int defaultStartingID = 10;

            final CheckBox responseFinal = (CheckBox) findViewById(R.id.CheckBoxFinal);
            boolean finalCheck = responseFinal.isChecked();

            final EditText name = (EditText) findViewById(R.id.EditTextCardName);
            String newCardName = name.getText().toString();

            final EditText picture = (EditText) findViewById(R.id.EditTextCardPicture);
            String newCardPicture = picture.getText().toString();

            if(!finalCheck) {
                CategoryCard newCatCard = new CategoryCard(defaultStartingID, newCardName, newCardPicture);
                newCatCard = (CategoryCard) manager.add(newCatCard,currentCard.getId());
                currentCard.add(newCatCard);
            }
            else {
                FinalCard newFinalCard = new FinalCard(defaultStartingID, newCardName, newCardPicture);
                newFinalCard = (FinalCard) manager.add(newFinalCard,currentCard.getId());
                currentCard.add(newFinalCard);
            }

            updateUI();
        }

    }

    private class ClicAdmin implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent admin_intent = new Intent(getApplicationContext(), AdminActivity.class);
            startActivityForResult(admin_intent, 0);
        }
    }

    private class ClicHelp implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent help_intent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivityForResult(help_intent, 0);
        }
    }

    private class ClicBack implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //TODO : back button makes us go to the top category

        }
    }

    private class ClicUndo implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //TODO : undo button remove last finalCard of the sentence
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                           Manage User Interface                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void updateUI() {

        //TODO : Remove this, it's only for debug
        for(AbstractCard card : data.getChilds()) {
            System.out.println(card.getTitle());
        }
        //TODO end

        Button btHelp = (Button) findViewById(R.id.help);
        btHelp.setOnClickListener(new ClicHelp());

        Button btAdd = (Button) findViewById(R.id.add);
        btAdd.setOnClickListener(new ClicAddBt());

        Button btAdmin = (Button) findViewById(R.id.admin);
        btAdmin.setOnClickListener(new ClicAdmin());

        TableLayout pageLine = (TableLayout) findViewById(R.id.lnPage);
        pageLine.removeAllViewsInLayout();


        TableRow thirdRow = new TableRow(getApplicationContext());
        ArrayList<TableRow> rows = new ArrayList<>();

        int numOfLines = createRow(pageLine, rows);
        displayCards(rows,numOfLines);


    }

    private int createRow(TableLayout lines, ArrayList<TableRow> rows){

        // Get size of cards stored.
        int numOfLine = data.getChilds().size() / 2 + 1;
        for(int i = 0; i < 2; i++){
            rows.add(new TableRow(getApplicationContext()));
        }

        for(TableRow line : rows){
            lines.addView(line);
        }
        return numOfLine;
    }

    private void displaySentence() {

    }

    private void displayCards(ArrayList<TableRow> lignes, int nb_par_ligne) {

        ArrayList<ImageButton> cardButtons = new ArrayList<>();

        for(AbstractCard card : currentCard.getChilds()) {
            cardButtons.add(new ImageButton(this));
        }

        int i = 0;
        for(AbstractCard card : currentCard.getChilds()) {
            int pict_id = getResources().getIdentifier(card.getPicture(), "drawable", getPackageName());
            if (pict_id == 0) { pict_id = getResources().getIdentifier("ic_launcher", "drawable", getPackageName()); }
            cardButtons.get(i).setImageDrawable(getResources().getDrawable(pict_id));
            cardButtons.get(i).setId(i);
            cardButtons.get(i).setOnClickListener(new ClicCard());
            cardButtons.get(i).setBackgroundColor(Color.TRANSPARENT);
            lignes.get(i / nb_par_ligne).addView(cardButtons.get(i));
            i++;
        }

    }
}
