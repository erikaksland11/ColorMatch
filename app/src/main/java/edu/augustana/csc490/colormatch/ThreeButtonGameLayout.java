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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class ThreeButtonGameLayout extends ActionBarActivity {
    // the two list of numbers associated with the colors
    private ArrayList<Integer> playerColorList = new ArrayList<Integer>();
    private ArrayList<Integer> compColorList = new ArrayList<Integer>();
    private Random rand;
    private int lastColor = -1;
    //private int timerStartTIme = compColorList.size();
    private TextView scoreView;
    private int currentScore = 0;
    //private TextView highScoreView;

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
        Button temp = (Button) findViewById(R.id.startGameButton);
        temp.setEnabled(false);     // So the user cannot hit this button during game play

        for (int i = 0; i < 4; i++) {
            addNewRandColor();
        }
        gamePlay();
    }

    public void addNewRandColor() {
        rand = new Random();     // to create the random numbers to match to colors
        compColorList.add(rand.nextInt(3));
        checkForNewColor(rand, compColorList.size() - 1, lastColor);
        lastColor = compColorList.get(compColorList.size() - 1);
    }

    public void checkForNewColor(Random rand, int newColor, int lastColor) {
        if (compColorList.get(newColor) == lastColor) {
            compColorList.set(newColor, rand.nextInt(3));
            checkForNewColor(rand, newColor, lastColor);
        }
    }

    // lets the user start the game
    public void gamePlay() throws InterruptedException {
        CountDownTimer timer = new CountDownTimer(compColorList.size() * 750 + 700, 750) {
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
        scoreView = (TextView) findViewById(R.id.scoreValueTextView);
        currentScore = playerColorList.size();
        scoreView.setText(Integer.toString(currentScore));

        playerColorList.clear();
        addNewRandColor();
        gamePlay();
    }

    public void endGame() {
        //checkForHighScore();
        AlertDialog.Builder builder = new AlertDialog.Builder(ThreeButtonGameLayout.this);
        builder.setTitle(getString(R.string.giveUpDialog));     // set the AlertDialog's title
        // set list of items to display in dialog
        String temp = getString(R.string.scoreText) + getString(R.string.scoreValueText);
        builder.setMessage(temp);     // set the AlertDialog's message
        builder.setPositiveButton(getString(R.string.playAgainText),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       Intent intent = new Intent(ThreeButtonGameLayout.this,
                               SelectDifficultyMenu.class);
                        startActivity(intent);
                    }
                }
        );  // endSetPositive
        builder.setNegativeButton(getString(R.string.homeText),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ThreeButtonGameLayout.this,
                                MainActivity.class);
                        startActivity(intent);
                    }
                }
        );  //endSetNegative
        builder.create().show(); // display the AlertDialog
    }

//    public void checkForHighScore() {
//        highScoreView = (TextView) findViewById(R.id.easyHighScoreTextView);
//        if (currentScore > Integer.parseInt((String) highScoreView.getText())) {
//            highScoreView.setText(Integer.toString(currentScore));
//        }
//    }

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
