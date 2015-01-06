package com.essai.celine.essai15;

/**
 * Created by celine on 30/11/14.
 * Feuille du pattern Composite
 */
class FCarte extends ICarte {

    @Override
    CCarte choisir(CCarte origine) {
        return origine;
    }

    @Override
    String getType() {
        return "F";
    }

    public FCarte(int pid, String ptitre, String pimage) {
        super(pid,ptitre,pimage);
    }

}
