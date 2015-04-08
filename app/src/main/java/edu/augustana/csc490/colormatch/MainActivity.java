package edu.augustana.csc490.colormatch;

// Name: Erik Aksland
// Dev Name: AkslandApplications
// Type: Game
// Description: A game to test your memory.  Match the colors
//  displayed in the correct order! But do it fast, you're being
//  timed!
//  Play any of four difficulty levels: Easy, Medium, Hard, Expert

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called when the user clicks the playGameButton button
    public void showSelectDifficultyMenu(View view) {
        Intent intent = new Intent(this, SelectDifficultyMenu.class);
        startActivity(intent);
    }

    // Called when the user clicks the highScoresButton button
    public void showHighScoreMenu(View view) {
        Intent intent = new Intent(this, HighScoreMenu.class);
        startActivity(intent);
    }

}
