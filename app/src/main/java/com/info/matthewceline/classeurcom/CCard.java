package com.info.matthewceline.classeurcom;

<<<<<<< HEAD
import java.util.ArrayList;

=======
>>>>>>> b24ecc2ebaa25320b7c39fe1e3307fa449a72268
/**
 * Created by celine on 04/12/14.
 * Composite (pattern Composite)
 */
public class CCard extends Card {

<<<<<<< HEAD
    private ArrayList<Card> childs = new ArrayList<>();

=======
>>>>>>> b24ecc2ebaa25320b7c39fe1e3307fa449a72268
    CCard(int _id, String _title, String _picture) {
        super(_id, _title, _picture);
    }

    @Override
    String getType() {
        return "C";
    }

<<<<<<< HEAD
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                Specific to Composite                                      //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public void ajouter(Card newChild) {
        childs.add(newChild);
    }

    public void remove(Card childToRemove) { childs.remove(childToRemove); }

    public ArrayList<Card> getChilds() { return childs; }

=======
>>>>>>> b24ecc2ebaa25320b7c39fe1e3307fa449a72268
}
