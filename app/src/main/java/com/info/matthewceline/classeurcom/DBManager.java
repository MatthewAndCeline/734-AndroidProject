package com.info.matthewceline.classeurcom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by celine on 04/12/14.
 */
public class DBManager {

    private final SQLiteDatabase db;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                       Constructor                                         //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    DBManager(SQLiteDatabase _db) {
        db = _db;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                Make objects from Database                                 //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public void fillObjects(CCard root) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Cards("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " //column 0
                + "title TEXT," //column 1
                + "picture TEXT," //column 2
                + "type TEXT," //column 3
                + "id_parent INTEGER)" //column 4
        );

        //Starting from the root, we recursively link each card to it's childs
        fill(root);

    }

    private void fill(CCard parent) {

        //Search all childs of the parent card
        Cursor resultSet = db.rawQuery("SELECT * FROM Cards WHERE id_parent = " + parent.getId(), null);

        while (resultSet.moveToNext()) {
            //Arrived in a Final Card, we add it to it's parent and stop recursivity
            if (resultSet.getString(3).equals("F")) {
                FCard child = new FCard(resultSet.getInt(0),resultSet.getString(1),resultSet.getString(2));
                parent.add(child);
            }
            //Arrived in a Category Card, we add it to it's parent and continue recursively with this card.
            else {
                CCard child = new CCard(resultSet.getInt(0),resultSet.getString(1),resultSet.getString(2));
                parent.add(child);
                fill(child);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                   Add/Remove elements                                     //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public Card add(Card _card, int id_parent) {
        _card.setId(getMaxId());
        db.execSQL("INSERT INTO Cards VALUES("
                        + _card.getId() + ",\""
                        + _card.getTitle() + "\",\""
                        + _card.getPicture() + "\",\""
                        + _card.getType() + "\","
                        + id_parent + ")"
        );

        return _card;
    }


    public void remove(Card _card) {
        if (_card.getType() == "C") {
            CCard ccard = (CCard) _card;
            for (Card child : ccard.getChilds()) {
                remove(child);
            }
        }
        db.execSQL("DELETE FROM Cards WHERE id=" + _card.getId());
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                          Tools                                            //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public int getMaxId() {

        Cursor resultSet = db.rawQuery("Select * from Cards ORDER BY id DESC LIMIT 1",null);
        if (!resultSet.moveToNext()) { return 1; }

        resultSet.moveToFirst();
        return resultSet.getInt(0) + 1;

    }
}
