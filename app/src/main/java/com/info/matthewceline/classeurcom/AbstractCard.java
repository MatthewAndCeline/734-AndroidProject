package com.info.matthewceline.classeurcom;

/**
 * Created by celine on 04/12/14.
 * Composant (pattern Composite)
 */
public abstract class AbstractCard {

    private int id;
    private String title;
    private String picture;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                     Abstract methods                                      //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    abstract String getType();

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                     Constructor                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    AbstractCard(int _id, String _title, String _picture) {
        id = _id;
        title = _title;
        picture = _picture;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                       Accessors                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
