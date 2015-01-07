package com.info.matthewceline.classeurcom;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static android.os.Environment.getExternalStorageDirectory;


public class AdminActivity extends ActionBarActivity {

    //for database management
    public String databaseName = "CardDatabase";
    private SQLiteDatabase db;
    public DBManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //create database manager
        db = openOrCreateDatabase(databaseName, MODE_APPEND, null);
        manager = new DBManager(db);

        //Associate buttons with actions
        Button btImport = (Button) findViewById(R.id.importer);
        btImport.setOnClickListener(new ClicImport());

        Button btExport = (Button) findViewById(R.id.exporter);
        btExport.setOnClickListener(new ClicExport());
    }



    private class ClicExport implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //TODO : export database as text file
            String txt = manager.exporter();
            String nomFichier = "classeurComConfig.txt";
            ecrireFichier(nomFichier,txt);
        }

    }

    private class ClicImport implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //TODO : import database as text file
            String nomFichier = "classeurComConfig.txt";
            String resultat = lireFichier(nomFichier);
            manager.importer(resultat);
            TextView txtV = (TextView) findViewById(R.id.txtFile);
            txtV.setText(resultat);
        }

    }

    private void ecrireFichier(String nomFichier,String monText) {

        File sdLien = getExternalStorageDirectory();
        File monFichier = new File(sdLien , nomFichier);
        BufferedWriter writer = null;
        try {
            FileWriter out = new FileWriter(monFichier);
            writer = new BufferedWriter(out);
            writer.write(monText);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String lireFichier(String nomFichier) {
        String monText="";
        File sdLien = getExternalStorageDirectory();
        File monFichier = new File(sdLien + "/" +nomFichier);
        if (!monFichier.exists()) {
            throw new RuntimeException("Fichier inexistant sur la carte sd");
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(monFichier));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            monText = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return monText;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
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
}
