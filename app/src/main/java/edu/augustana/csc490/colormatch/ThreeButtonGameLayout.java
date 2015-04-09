package edu.augustana.csc490.colormatch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;


public class ThreeButtonGameLayout extends ActionBarActivity {
    private ArrayList<Integer> gameColorList = new ArrayList<>();    // list of numbers associated with the colors
    private Random rand;                         // to create the random numbers to match to colors

    public ThreeButtonGameLayout() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_button_game_layout);
    }

    // create a new AlertDialog Builder
    public void gameOverDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThreeButtonGameLayout.this);
        builder.setTitle(getString(R.string.giveUpDialog));     // set the AlertDialog's title
        // set list of items to display in dialog
        builder.setItems(R.array.giveUpDialogItems, new DialogInterface.OnClickListener() {
                    // responds to user touch by going to the home page
                    // or the select difficulty menu
                    Intent intent;
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // home
                                intent = new Intent(ThreeButtonGameLayout.this,
                                        MainActivity.class);
                                break;
                            case 1: // play again
                                intent = new Intent(ThreeButtonGameLayout.this,
                                        SelectDifficultyMenu.class);
                                break;
                        }
                        startActivity(intent);
                    }
                } // end DialogInterface.OnClickListener
        );// end call to builder.setItems
        //builder.setMessage(getString(R.string.scoresDialog));     // set the AlertDialog's message
        builder.create().show(); // display the AlertDialog
    }

    // lets the user start the game
    public void startGame(View view) {
        rand = new Random();
        for (int i = 0; i < 4; i++) {
            gameColorList.add(i, rand.nextInt(2));
        }
        showColors(gameColorList);
        //start timer when made


    }

    public void showColors(ArrayList<Integer> gameColorList) {
        ImageView display = (ImageView)findViewById(R.id.colorDisplayView);
        int currentColorNum;
        for (int i = 0; i < gameColorList.size(); i++) {
            currentColorNum = gameColorList.get(i);
            if (currentColorNum == 0) {
                display.setBackgroundColor(Color.parseColor("ff0096ff"));
            } else if (currentColorNum == 1) {
                display.setBackgroundColor(Color.parseColor("ffffe600"));
            } else if (currentColorNum == 2) {
                display.setBackgroundColor(Color.parseColor("ffff3200"));
            }
        }
    }

    public void checkColorMatch(View view) {

    }



}
