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

    CategoryCard chosenCard(CategoryCard thisCard){
        return this;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                Specific to Composite                                      //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Link this card with a new child
    public void add(AbstractCard newChild) {
        childs.add(newChild);
    } //Done

    // Link this card with a new child and save it in database
    public void add(AbstractCard newChild, DBManager manager) { //Done
        newChild = manager.add(newChild,this.getId());
        childs.add(newChild);
    }

    // UnLink this card with one of it's child
    public void remove(AbstractCard childToRemove) {
        childs.remove(childToRemove);
    }

    // UnLink this card with one of it's child and remove this child of database
    public void remove(AbstractCard childToRemove, DBManager manager) {
        childs.remove(childToRemove);
        manager.remove(childToRemove);
    }

    public ArrayList<AbstractCard> getChilds() { return childs; }

}
