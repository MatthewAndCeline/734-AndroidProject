package com.essai.celine.essai15;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private final CCarte donnees = new CCarte(0,"root","root");
    private CCarte curent = donnees;
    private final ArrayList<ICarte> chemin = new ArrayList<>();
    private static final String NOM_DB = "essai15bis.db";
    private SQLiteDatabase db;
    private DBGestionnaire db_gest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(NOM_DB,MODE_APPEND,null);
        db_gest = new DBGestionnaire(db);

        db_gest.peupler(donnees);
        maj();
    }


    private class ClicCourtPourOuvrirCarte implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            chemin.add(curent.getEnfants().get(id));
            curent = curent.getEnfants().get(id).choisir(donnees);
            maj();
        }

    }

    private class ClicLongPourAfficherAide implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            int id = v.getId();
            showDialog(id);
            return true;
        }

    }

    public class AjoutListener implements View.OnClickListener {

        private final EditText edtAjout;
        private final EditText edtImage;
        private final EditText edtFeuille;

        public AjoutListener(EditText pedtAjout, EditText pedtImage, EditText pedtFeuille) {

            super();
            edtAjout = pedtAjout;
            edtImage = pedtImage;
            edtFeuille = pedtFeuille;

        }

        @Override
        public void onClick(View v) {

            if (String.valueOf(edtFeuille.getText()).equals("F")) {
                curent.ajouter(new FCarte(db_gest.getMaxId(),String.valueOf(edtAjout.getText()),String.valueOf(edtImage.getText())),db);
            }
            else {
                curent.ajouter(new CCarte(db_gest.getMaxId(), String.valueOf(edtAjout.getText()), String.valueOf(edtImage.getText())),db);
            }
            maj();

        }

    }

    @Override
    public Dialog onCreateDialog(int id) {

        Dialog box = new Dialog(this);
        box.setTitle(curent.getEnfants().get(id).getTitre());

        return box;
    }

    @Override
    public void onPrepareDialog(int id, @NonNull Dialog box) {

        box.setTitle(curent.getEnfants().get(id).getTitre());

    }

    void maj() { //Mise A Jour = update

        TableLayout lnPage = (TableLayout) findViewById(R.id.lnPage);
        lnPage.removeAllViewsInLayout();

        TableRow ThirdLine = new TableRow(this);
        ArrayList<TableRow> lignes = new ArrayList<>();

        int nb_par_ligne = CreerLignes(lnPage, lignes);

        afficheCartes(lignes, nb_par_ligne);

        afficheThirdLine(ThirdLine);
        lnPage.addView(ThirdLine);

        afficheChemin();

    }

    private int CreerLignes(TableLayout lnPage, ArrayList<TableRow> lignes) {

        int nb_par_ligne = curent.getEnfants().size() / 2 + 1;
        for (int i = 0; i < 2; i++) {
            lignes.add(new TableRow(this));
        }
        for (TableRow ligne : lignes) {
            lnPage.addView(ligne);
        }
        return nb_par_ligne;

    }

    private void afficheCartes(ArrayList<TableRow> lignes, int nb_par_ligne) {

        ArrayList<ImageButton> mes_boutons = new ArrayList<>();

        for(ICarte carte : curent.getEnfants()) {
            mes_boutons.add(new ImageButton(this));
        }

        int i = 0;
        for(ICarte carte : curent.getEnfants()) {
            int pict_id = getResources().getIdentifier(carte.getImage(), "drawable", getPackageName());
            if (pict_id == 0) { pict_id = getResources().getIdentifier("ic_launcher", "drawable", getPackageName()); }
            mes_boutons.get(i).setImageDrawable(getResources().getDrawable(pict_id));
            mes_boutons.get(i).setId(i);
            mes_boutons.get(i).setOnClickListener(new ClicCourtPourOuvrirCarte());
            mes_boutons.get(i).setOnLongClickListener(new ClicLongPourAfficherAide());
            mes_boutons.get(i).setBackgroundColor(Color.TRANSPARENT);
            lignes.get(i / nb_par_ligne).addView(mes_boutons.get(i));
            i++;
        }

    }

    private void afficheChemin() {

        int i; LinearLayout lnChemin = (LinearLayout) findViewById(R.id.lnChemin);
        lnChemin.removeAllViewsInLayout();
        ArrayList<Button> lst_btChemin = new ArrayList<>();
        for (ICarte carte : chemin) {
            lst_btChemin.add(new Button(this));
        }

        i = 0;
        for (ICarte carte : chemin) {
            lst_btChemin.get(i).setText(carte.getTitre());
            lnChemin.addView(lst_btChemin.get(i));
            i++;
        }

    }

    private void afficheThirdLine(TableRow ligneF) {

        EditText edtAjout = new EditText(this);
        edtAjout.setText("carte");
        edtAjout.setSelection(0,edtAjout.getText().length());

        EditText edtImage = new EditText(this);
        edtImage.setText("image");
        edtImage.setSelection(0,edtImage.getText().length());

        EditText edtFeuille = new EditText(this);
        edtFeuille.setText("F");
        edtFeuille.setSelection(0,edtFeuille.getText().length());

        Button btAjout = new Button(this);
        btAjout.setText("ajouter");
        btAjout.setOnClickListener(new AjoutListener(edtAjout, edtImage, edtFeuille));


        ligneF.addView(edtAjout);
        ligneF.addView(edtImage);
        ligneF.addView(edtFeuille);
        ligneF.addView(btAjout);

    }


}
