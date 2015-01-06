package com.info.matthewceline.classeurcom;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    // test root card
    private final CategoryCard data = new CategoryCard(0,"root","root");
    public CategoryCard currentCard = data;

    public ArrayList<CategoryCard> ccards = new ArrayList<CategoryCard>(); // The cards to be displayed.
    private final ArrayList<AbstractCard> path = new ArrayList<>();

    public String databaseName = "CardDatabase";
    private SQLiteDatabase db;
    public DBManager manager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(databaseName, MODE_APPEND, null); // populate database.
        manager = new DBManager(db);

        manager.createCardsFromDatabase(data);
        updateUI();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                       Listeners                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private class ClicCard implements View.OnClickListener {

        @Override
        public void onClick(View v) {

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
            onCreateDialog(22000);
        }
    }

    private class ClicAdmin implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            onCreateDialog(20000);
        }
    }

    private class ClicHelp implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            onCreateDialog(21000);
        }
    }

    private class ClicBack implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    private class ClicUndo implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                       Dialogs                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Dialog onCreateDialog(int id) {

            //id available values :
            //0 -> 19999 = Card Id
            //20000 = admin
            //21000 = help
            //22000 = add a Card

        Dialog box = new Dialog(this);
        box.setTitle("Un dialogue");
        switch (id)
        {
            case 20000:
                Intent admin_intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivityForResult(admin_intent, 0);
                break;

            case 21000:

                Intent help_intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivityForResult(help_intent, 0);
                break;

            case 22000:
                Intent add_intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(add_intent, 0);
                break;

        }
        return box;
    }

    @Override
    protected void onPrepareDialog(int id, @NonNull Dialog box) {

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                           Manage User Interface                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void updateUI() {

        Button btHelp = (Button) findViewById(R.id.help);
        btHelp.setOnClickListener(new ClicHelp());

        Button btAdd = (Button) findViewById(R.id.add);
        btAdd.setOnClickListener(new ClicAddBt());

        Button btAdmin = (Button) findViewById(R.id.admin);
        btAdmin.setOnClickListener(new ClicAdmin());

        TableLayout pageLine = (TableLayout) findViewById(R.id.lnPage);
        //pageLine.removeAllViewsInLayout();


        TableRow thirdRow = new TableRow(getApplicationContext());
        ArrayList<TableRow> rows = new ArrayList<>();

        int numOfLines = createRow(pageLine, rows);

    }
    /*
    private class clickToOpenCard implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            int id = v.getId();
            path.add(data.getChilds().get(id));
            data = data.getChilds().get(id).(this);

        }
    }*/

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

    private void displayPage() {

    }

    private void displayAdminDialog() {

    }

    private void displayAddDialog() {

    }

    private void displayCards(ArrayList<TableRow> lignes, int nb_par_ligne) {

        ArrayList<ImageButton> cardButtons = new ArrayList<>();

        for(AbstractCard card : data.getChilds()) {
            cardButtons.add(new ImageButton(this));
        }

        int i = 0;
        for(AbstractCard card : data.getChilds()) {
            int pict_id = getResources().getIdentifier(card.getPicture(), "drawable", getPackageName());
            if (pict_id == 0) { pict_id = getResources().getIdentifier("ic_launcher", "drawable", getPackageName()); }
            cardButtons.get(i).setImageDrawable(getResources().getDrawable(pict_id));
            cardButtons.get(i).setId(i);
            //cardButtons.get(i).setOnClickListener(new ClicCourtPourOuvrirCarte());
            //cardButtons.get(i).setOnLongClickListener(new ClicLongPourAfficherAide());
            cardButtons.get(i).setBackgroundColor(Color.TRANSPARENT);
            lignes.get(i / nb_par_ligne).addView(cardButtons.get(i));
            i++;
        }

    }
}
