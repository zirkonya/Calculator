package fr.upjv.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.upjv.calculator.R;
import fr.upjv.calculator.tools.Difficulty;
import fr.upjv.calculator.tools.Gamemode;

public class MainActivity extends AppCompatActivity {
    private Difficulty difficulty;
    private Gamemode gamemode;

    private TextView difficultyTextView;
    private TextView gamemodeTextView;

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.main_startButton);
        Button rankingButton = findViewById(R.id.main_rankingButton);

        Button leftDifficultyButton = findViewById(R.id.main_leftDifficultyButton);
        Button leftGamemodeButton = findViewById(R.id.main_leftGamemodeButton);
        Button rightDifficultyButton = findViewById(R.id.main_rightDifficultyButton);
        Button rightGamemodeButton = findViewById(R.id.main_rightGamemodeButton);

        gamemodeTextView = findViewById(R.id.main_gamemodeSlideTextView);
        difficultyTextView = findViewById(R.id.main_difficultySlideTextView);

        startButton.setOnClickListener(view -> openGameActivity());
        rankingButton.setOnClickListener(view -> openRankingActivity());

        leftDifficultyButton.setOnClickListener(view -> changeDifficulty(true));
        leftGamemodeButton.setOnClickListener(view -> changeGamemode(true));
        rightDifficultyButton.setOnClickListener(view -> changeDifficulty(false));
        rightGamemodeButton.setOnClickListener(view -> changeGamemode(false));

        difficulty = Difficulty.SIMPLE;
        gamemode = Gamemode.CHRONO;

        title = findViewById(R.id.main_titleTextView);

        updateDifficulty();
        updateGamemode();
    }

    private void openRankingActivity() {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    private void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void changeDifficulty(boolean previous) {
        int total = Difficulty.values().length;
        int currentOrdinal = difficulty.ordinal();
        int nextOrdinal;
        if (previous)
            nextOrdinal = currentOrdinal == 0 ? total - 1 : currentOrdinal - 1;
        else
            nextOrdinal = currentOrdinal == total -1 ? 0 : currentOrdinal + 1;
        difficulty = Difficulty.values()[nextOrdinal];
        updateDifficulty();
    }

    private void changeGamemode(boolean previous) {
        int total = Gamemode.values().length;
        int currentOrdinal = gamemode.ordinal();
        int nextOrdinal;
        if (previous)
            nextOrdinal = currentOrdinal == 0 ? total - 1 : currentOrdinal - 1;
        else
            nextOrdinal = currentOrdinal == total -1 ? 0 : currentOrdinal + 1;
        gamemode = Gamemode.values()[nextOrdinal];
        updateGamemode();
    }

    private void updateDifficulty() {
        difficultyTextView.setText(difficulty.getName());
    }

    private void updateGamemode() {
        gamemodeTextView.setText(gamemode.getName());
    }

}