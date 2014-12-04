package com.info.matthewceline.classeurcom;

/**
 * Created by celine on 04/12/14.
 * Composite (pattern Composite)
 */
public class CCard extends Card {

    CCard(int _id, String _title, String _picture) {
        super(_id, _title, _picture);
    }

    @Override
    String getType() {
        return "C";
    }

}
