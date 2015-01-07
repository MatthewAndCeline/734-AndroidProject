package com.info.matthewceline.classeurcom;

import java.util.ArrayList;

/**
 * Created by celine on 04/12/14.
 * Composite (pattern Composite)
 */
public class CategoryCard extends AbstractCard {

    private ArrayList<AbstractCard> childs = new ArrayList<>();

    CategoryCard(int _id, String _title, String _picture) {
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
    public void add(AbstractCard newChild) {
        childs.add(newChild);
    }

    // UnLink this card with one of it's child
    public void remove(AbstractCard childToRemove) {
        childs.remove(childToRemove);
    }

    public ArrayList<AbstractCard> getChilds() { return childs; }

}
