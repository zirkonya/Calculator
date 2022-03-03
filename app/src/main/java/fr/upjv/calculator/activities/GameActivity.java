package fr.upjv.calculator.activities;

import fr.upjv.calculator.R;
import fr.upjv.calculator.calcul.Calcul;
import fr.upjv.calculator.calcul.Operator;
import fr.upjv.calculator.tools.Difficulty;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public abstract class GameActivity extends AppCompatActivity {
    protected int score;
    private Difficulty difficulty;
    protected TextView calculTextView;
    protected TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int difficulty = getIntent().getIntExtra("difficulty", 0);
        this.difficulty = Difficulty.values()[difficulty];

        calculTextView = findViewById(R.id.game_calculTextView);
        answerTextView = findViewById(R.id.game_answerTextView);

        init();
    }

    public abstract void init();
    public abstract void game();
    public abstract void end ();

    protected Calcul generateCalcul() {
        int[] values = new int[difficulty.getTerm()];
        Operator[] operator = new Operator[difficulty.getTerm()-1];

        values[0] = difficulty.getRandomTerm();
        for (int i = 0; i < operator.length; i++) {
            values[i+1] = difficulty.getRandomTerm();
            operator[i] = difficulty.getRandomOperator();
        }

        return new Calcul(values, operator);
    }

}