package edu.augustana.csc490.colormatch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;


public class ThreeButtonGameLayout extends ActionBarActivity {
    // the two list of numbers associated with the colors
    private ArrayList<Integer> playerColorList = new ArrayList<Integer>();
    private ArrayList<Integer> compColorList = new ArrayList<Integer>();
    //private int timerStartTIme = compColorList.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_button_game_layout);
    }

    // create a new AlertDialog Builder
    public void gameOverDialog(View view) {
        endGame();
    }

    // gets the game started
    // NOTE: Blue = 0, Yellow = 1, Red = 2
    public void startGame(View view) throws InterruptedException {
        Random rand = new Random();     // to create the random numbers to match to colors
        Button temp = (Button) findViewById(R.id.startGameButton);
        temp.setEnabled(false);     // So the user cannot hit this button during game play

        int lastColor = -1;
        for (int i = 0; i < 4; i++) {
            compColorList.add(rand.nextInt(3));
            checkForNewColor(rand, i, lastColor);
            lastColor = compColorList.get(i);
        }
        gamePlay();
    }

    public void checkForNewColor(Random rand, int i, int lastColor) {
        if (compColorList.get(i) == lastColor) {
            compColorList.set(i, rand.nextInt(3));
            checkForNewColor(rand, i, lastColor);
        }
    }

    // lets the user start the game
    public void gamePlay() throws InterruptedException {
        CountDownTimer timer = new CountDownTimer(compColorList.size() * 750, 750) {
            int colorIndex = 0;
            ImageView display = (ImageView)findViewById(R.id.colorDisplayView);
            public void onTick(long millisUntilFinished) {
                if (compColorList.get(colorIndex) == 0) {
                    display.setBackgroundColor(Color.parseColor("#FF0096FF"));
                } else if (compColorList.get(colorIndex) == 1) {
                    display.setBackgroundColor(Color.parseColor("#FFFFE600"));
                } else if (compColorList.get(colorIndex) == 2) {
                    display.setBackgroundColor(Color.parseColor("#FFFF3200"));
                }
                colorIndex++;
            }

            public void onFinish() {
                display.setBackgroundColor(Color.WHITE);
                turnButtonsOn();        // So the user can match the buttons during game play
            }
        };
        timer.start();

    }

    public void colorButtonValue(View view) throws InterruptedException {
        if (view.getId() == R.id.blueButton) {
            playerColorList.add(0);    //adds user Pushed button
        } else if (view.getId() == R.id.yellowButton) {
            playerColorList.add(1);    //adds user Pushed button
        } else {
            playerColorList.add(2);    //adds user Pushed button
        }
        checkValidColor();
    }

    public void checkValidColor() throws InterruptedException {
        for (int i = 0; i < playerColorList.size(); i++) {
            if (playerColorList.get(i) != compColorList.get(i)) {
                endGame();
            }
        }
        if (playerColorList.size() == compColorList.size()) {
            passedCurrentRound();
        }
    }

    public void passedCurrentRound() throws InterruptedException {
        turnButtonsOff();
        //score = playerColorList.size();
        playerColorList.clear();

        Random rand = new Random();     // to create the random numbers to match to colors
        compColorList.add(compColorList.size() - 1, rand.nextInt(3));

        gamePlay();
    }


    public void endGame() {
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



    public void turnButtonsOn() {
        Button temp  = (Button) findViewById(R.id.blueButton);
        temp.setEnabled(true);
        temp = (Button) findViewById(R.id.yellowButton);
        temp.setEnabled(true);
        temp = (Button) findViewById(R.id.redButton);
        temp.setEnabled(true);
    }

    public void turnButtonsOff() {
        Button temp  = (Button) findViewById(R.id.blueButton);
        temp.setEnabled(false);
        temp = (Button) findViewById(R.id.yellowButton);
        temp.setEnabled(false);
        temp = (Button) findViewById(R.id.redButton);
        temp.setEnabled(false);
    }












}
