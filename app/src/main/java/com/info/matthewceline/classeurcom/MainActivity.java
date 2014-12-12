package com.info.matthewceline.classeurcom;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                       Listeners                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private class ClicCard implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    private class LongClicCard implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    private class ClicAddBt implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    private class ClicAdmin implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    private class ClicHelp implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    private class ClicBack implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    private class ClicUndo implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                                       Dialogs                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected Dialog onCreateDialog(int id) {

        //id available values :
            //0 -> 19999 = Card Id
            //20000 = admin
            //21000 = help
            //22000 = add a Card

        Dialog box = new Dialog(this);
        box.setTitle("Un dialogue");

        return box;
    }

    @Override
    protected void onPrepareDialog(int id, @NonNull Dialog box) {

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                           Manage User Interface                                           //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void updateUI() {

    }

    private void displaySentence() {

    }

    private void displayPage() {

    }

    private void displayAdminDialog() {

    }

    private void displayAddDialog() {

    }
}
