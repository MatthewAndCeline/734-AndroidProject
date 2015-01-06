package com.info.matthewceline.classeurcom;

/**
 * Created by celine on 04/12/14.
 * Final Element (pattern Composite)
 */
public class FinalCard extends AbstractCard {

    FinalCard(int _id, String _title, String _picture) {
        super(_id, _title, _picture);
    }

    @Override
    String getType() {
        return "F";
    }

}
