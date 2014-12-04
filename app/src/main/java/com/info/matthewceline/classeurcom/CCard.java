package com.info.matthewceline.classeurcom;

import java.util.ArrayList;

/**
 * Created by celine on 04/12/14.
 * Composite (pattern Composite)
 */
public class CCard extends Card {

    private ArrayList<Card> childs = new ArrayList<>();

    CCard(int _id, String _title, String _picture) {
        super(_id, _title, _picture);
    }

    @Override
    String getType() {
        return "C";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                Specific to Composite                                      //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Link this card with a new child
    public void add(Card newChild) {
        childs.add(newChild);
    }

    // Link this card with a new child and save it in database
    public void add(Card newChild, DBManager manager) {
        newChild = manager.add(newChild,this.getId());
        childs.add(newChild);
    }

    // UnLink this card with one of it's child
    public void remove(Card childToRemove) {
        childs.remove(childToRemove);
    }

    // UnLink this card with one of it's child and remove this child of database
    public void remove(Card childToRemove, DBManager manager) {
        childs.remove(childToRemove);
        manager.remove(childToRemove);
    }

    public ArrayList<Card> getChilds() { return childs; }

}
