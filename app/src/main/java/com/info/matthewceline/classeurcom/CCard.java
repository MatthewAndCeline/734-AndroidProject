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
    public void ajouter(Card newChild) {
        childs.add(newChild);
    }

    public void remove(Card childToRemove) { childs.remove(childToRemove); }

    public ArrayList<Card> getChilds() { return childs; }

}
