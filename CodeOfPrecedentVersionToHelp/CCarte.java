package com.essai.celine.essai15;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by celine on 30/11/14.
 * Composite du pattern Composite
 */
class CCarte extends ICarte {

    private ArrayList<ICarte> enfants = new ArrayList<>();

    @Override
    CCarte choisir(CCarte origine) {
        return this;
    }

    @Override
    String getType() {
        return "C";
    }

    public CCarte(int pid, String ptitre, String pimage) {
        super(pid,ptitre,pimage);
    }

    public void ajouter(ICarte enfant) {
        enfants.add(enfant);
    }

    public void ajouter(ICarte enfant, SQLiteDatabase db) {
        db.execSQL("INSERT INTO Cartes VALUES("
            + enfant.getId() + ",\""
            + enfant.getTitre() + "\",\""
            + enfant.getImage() + "\",\""
            + enfant.getType() + "\","
            + this.getId() + ")"
        );
        enfants.add(enfant);
    }

    public void retirer(ICarte enfant) {
        enfants.remove(enfant);
    }

    public ArrayList<ICarte> getEnfants() {
        return enfants;
    }

}
