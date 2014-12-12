package com.essai.celine.essai15;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by celine on 30/11/14.
 */
class DBGestionnaire {

    private final SQLiteDatabase db;

    public DBGestionnaire(SQLiteDatabase pdb) {
        db = pdb;
    }

    public void peupler(CCarte donnees) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Cartes("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " //0
                + "titre TEXT," //1
                + "image TEXT," //2
                + "type TEXT," //3
                + "id_parent INTEGER)" //4
        );

        ajouter(donnees,db);

    }

    void ajouter(CCarte mere, SQLiteDatabase db) {
        Cursor resultat = db.rawQuery("SELECT * FROM Cartes WHERE id_parent = " + mere.getId(), null);
        while (resultat.moveToNext()) {
            if (resultat.getString(3).equals("F")) {
                FCarte carte = new FCarte(resultat.getInt(0),resultat.getString(1),resultat.getString(2));
                mere.ajouter(carte);
            }
            else {
                CCarte carte = new CCarte(resultat.getInt(0),resultat.getString(1),resultat.getString(2));
                mere.ajouter(carte);
                ajouter(carte, db);
            }
        }
    }

    public int getMaxId() {

        Cursor resultSet = db.rawQuery("Select * from Cartes ORDER BY id DESC LIMIT 1",null);
        if (!resultSet.moveToNext()) { db.close(); return 1; }
        resultSet.moveToFirst();
        db.close();

        return resultSet.getInt(0) + 1;

    }

}
