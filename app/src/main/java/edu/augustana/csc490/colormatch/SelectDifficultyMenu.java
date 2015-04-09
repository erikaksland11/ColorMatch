package edu.augustana.csc490.colormatch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;


public class SelectDifficultyMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_difficulty_menu);
    }

    // Called when the user clicks the homeButton
    public void showMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // To check which radio button was clicked
        switch(view.getId()) {
            case R.id.easyDifficulty:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.mediumDifficulty:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.hardDifficulty:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.expertDifficulty:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }


    // Called when the user clicks the startGameButton
    public void showGameLayout(View view) {
        Intent intent = new Intent(this, ThreeButtonGameLayout.class);
        startActivity(intent);
    }
}
