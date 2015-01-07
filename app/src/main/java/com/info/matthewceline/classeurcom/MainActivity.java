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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends ActionBarActivity {

    // the root card
    private final CategoryCard data = new CategoryCard(0,"root","root");
    // the last CategoryCard selected by user
    public CategoryCard currentCard = data;
    // the parent of last CategoryCard
    public Stack<CategoryCard> currentPath = new Stack<>();
    // the finalCards selected by user to make a sentence
    private ArrayList<FinalCard> sentence = new ArrayList<>();

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
        currentPath.push(data);
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
                //Category : we enter into the category
                if (currentCard.getChilds().get(id).getType() == "C") {
                    currentPath.push(currentCard);
                    currentCard = (CategoryCard) currentCard.getChilds().get(id);
                }
                //Final Card : we add the word to the sentence
                else {
                    sentence.add((FinalCard) currentCard.getChilds().get(id));
                }
                updateUI();
            }


    }

    private class LongClicCard implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //TODO : Afficher le nom de la carte et proposer la possibilité de la supprimer
        }

    }

    private class ClicAddBt implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            //get the name of the card, picture and type of card filled by user
            final CheckBox responseFinal = (CheckBox) findViewById(R.id.CheckBoxFinal);
            boolean finalCheck = responseFinal.isChecked();

            final EditText name = (EditText) findViewById(R.id.EditTextCardName);
            String newCardName = name.getText().toString();

            final EditText picture = (EditText) findViewById(R.id.EditTextCardPicture);
            String newCardPicture = picture.getText().toString();

            //Create final Card
            if(!finalCheck) {
                CategoryCard newCatCard = new CategoryCard(0, newCardName, newCardPicture);
                newCatCard = (CategoryCard) manager.add(newCatCard,currentCard.getId());
                currentCard.add(newCatCard);
            }
            //Create category Card
            else {
                FinalCard newFinalCard = new FinalCard(0, newCardName, newCardPicture);
                newFinalCard = (FinalCard) manager.add(newFinalCard,currentCard.getId());
                currentCard.add(newFinalCard);
            }

            updateUI();
        }

    }

    private class ClicAdmin implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //TODO : will allow user to upload / download a json file representing the database
            Intent admin_intent = new Intent(getApplicationContext(), AdminActivity.class);
            startActivityForResult(admin_intent, 0);
        }
    }

    private class ClicHelp implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //TODO : will display a text explaining how to use the application
            Intent help_intent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivityForResult(help_intent, 0);
        }
    }

    private class ClicBack implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //Go back to superior category
            if (currentCard.getId() != 0) currentCard = currentPath.pop();
            updateUI();
        }
    }

    private class ClicUndo implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //Remove last word of the sentence
            if (sentence.size() > 0) {
                sentence.remove(sentence.size() - 1);
                updateUI();
            }
        }

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                           Manage User Interface                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void updateUI() {

        //Associate buttons with actions
        Button btBack = (Button) findViewById(R.id.goback);
        btBack.setOnClickListener(new ClicBack());

        Button btHelp = (Button) findViewById(R.id.help);
        btHelp.setOnClickListener(new ClicHelp());

        Button btAdd = (Button) findViewById(R.id.add);
        btAdd.setOnClickListener(new ClicAddBt());

        Button btAdmin = (Button) findViewById(R.id.admin);
        btAdmin.setOnClickListener(new ClicAdmin());

        //Erase the page of cards
        TableLayout pageLine = (TableLayout) findViewById(R.id.lnPage);
        pageLine.removeAllViewsInLayout();


        //Create the page of cards
        ArrayList<TableRow> rows = new ArrayList<>();
        int numberOfCardsInOneLine = createRow(pageLine, rows);
        displayCards(rows,numberOfCardsInOneLine);

        //Create the sentence
        displaySentence();

    }

    //Create the 2 rows where the cards will be displayed
    private int createRow(TableLayout lines, ArrayList<TableRow> rows){

        //Cards will fill 2 lines
        int numberOfCardsInOneLine = data.getChilds().size() / 2 + 1;
        for(int i = 0; i < 2; i++){
            rows.add(new TableRow(getApplicationContext()));
        }

        //Add the rows in the page
        for(TableRow line : rows){
            lines.addView(line);
        }

        return numberOfCardsInOneLine;
    }

    private void displaySentence() {

        Button btUndo = (Button) findViewById(R.id.undo);
        btUndo.setOnClickListener(new ClicUndo());

        LinearLayout lnSentence = (LinearLayout) findViewById(R.id.lnSentence);
        lnSentence.removeAllViewsInLayout();

        ArrayList<Button> wordsButtons = new ArrayList<>();

        for (FinalCard card : sentence) {
            wordsButtons.add(new Button(this));
        }

        int i = 0;
        for (FinalCard card : sentence) {
            wordsButtons.get(i).setText(card.getTitle());
            lnSentence.addView(wordsButtons.get(i));
            i++;
        }


        lnSentence.addView(btUndo);

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
            //TODO  cardButtons.get(i).setOnLongClickListener(new LongClicCard());
            cardButtons.get(i).setBackgroundColor(Color.TRANSPARENT);
            lignes.get(i / nb_par_ligne).addView(cardButtons.get(i));
            i++;
        }

    }
}
