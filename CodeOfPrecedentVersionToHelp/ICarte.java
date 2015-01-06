package com.essai.celine.essai15;

/**
 * Created by celine on 30/11/14.
 * Composant pour le pattern Composite
 */
abstract class ICarte {

    private int id;
    private String titre;
    private String image;

    abstract CCarte choisir(CCarte origine);

    abstract String getType();

    ICarte(int pid, String ptitre, String pimage) {
        id = pid;
        titre = ptitre;
        image = pimage;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getImage() {
        return image;
    }

}
