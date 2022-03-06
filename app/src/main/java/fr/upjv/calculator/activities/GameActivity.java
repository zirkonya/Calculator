package fr.upjv.calculator.activities;

import fr.upjv.calculator.R;
import fr.upjv.calculator.computation.Computation;
import fr.upjv.calculator.computation.Operator;
import fr.upjv.calculator.tools.Difficulty;
import fr.upjv.calculator.tools.GameMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public abstract class GameActivity extends AppCompatActivity {
    private static final char HEART = '♥';
    private static final long LIMIT = 62500000000l;

    private Long answer;
    private boolean running;
    private Vibrator vibrator;
    private GameMode gameMode;
    private ConstraintLayout lock;
    private Difficulty difficulty;

    protected Toast failToast;
    protected Toast emptyAnswerToast;

    protected int score;
    protected Computation computation;

    protected TextView lifeTextView;
    protected TextView clockTextView;
    protected TextView answerTextView;
    protected TextView gameModeTextView;
    protected TextView computationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int gameMode = getIntent().getIntExtra("gameMode", 0);
        int difficulty = getIntent().getIntExtra("difficulty", 0);

        this.gameMode = GameMode.values()[gameMode];
        this.difficulty = Difficulty.values()[difficulty];

        score = 0;
        running = false;
        lock = findViewById(R.id.game_lock);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        failToast = Toast.makeText(this, R.string.fail, Toast.LENGTH_SHORT);
        emptyAnswerToast = Toast.makeText(this, R.string.answerMissing, Toast.LENGTH_LONG);

        Button submitButton = findViewById(R.id.game_keyboardSubmitButton);
        Button signumButton = findViewById(R.id.game_keyboardSignumButton);
        FloatingActionButton clearFloatingActionButton = findViewById(R.id.game_clearComputeFloatingButton);

        Button keyboardButton0 = findViewById(R.id.game_keyboardButton0);
        Button keyboardButton1 = findViewById(R.id.game_keyboardButton1);
        Button keyboardButton2 = findViewById(R.id.game_keyboardButton2);
        Button keyboardButton3 = findViewById(R.id.game_keyboardButton3);
        Button keyboardButton4 = findViewById(R.id.game_keyboardButton4);
        Button keyboardButton5 = findViewById(R.id.game_keyboardButton5);
        Button keyboardButton6 = findViewById(R.id.game_keyboardButton6);
        Button keyboardButton7 = findViewById(R.id.game_keyboardButton7);
        Button keyboardButton8 = findViewById(R.id.game_keyboardButton8);
        Button keyboardButton9 = findViewById(R.id.game_keyboardButton9);

        keyboardButton0.setOnClickListener(view -> addDigit(0));
        keyboardButton1.setOnClickListener(view -> addDigit(1));
        keyboardButton2.setOnClickListener(view -> addDigit(2));
        keyboardButton3.setOnClickListener(view -> addDigit(3));
        keyboardButton4.setOnClickListener(view -> addDigit(4));
        keyboardButton5.setOnClickListener(view -> addDigit(5));
        keyboardButton6.setOnClickListener(view -> addDigit(6));
        keyboardButton7.setOnClickListener(view -> addDigit(7));
        keyboardButton8.setOnClickListener(view -> addDigit(8));
        keyboardButton9.setOnClickListener(view -> addDigit(9));
        signumButton.setOnClickListener(view -> changeSignum());
        submitButton.setOnClickListener(view -> submit());
        clearFloatingActionButton.setOnClickListener(view -> clearAnswer());

        lifeTextView = findViewById(R.id.game_lifeTextView);
        clockTextView = findViewById(R.id.game_clockTextView);
        answerTextView = findViewById(R.id.game_answerTextView);
        gameModeTextView = findViewById(R.id.game_gameModeTextView);
        computationTextView = findViewById(R.id.game_computationTextView);

        lock.setOnClickListener(view -> unlock());
        lock.setBackgroundResource(R.color.transparent_black);

        gameModeTextView.setText(this.gameMode.getName());
        computation = Computation.generateComputation(this.difficulty);
    }

    protected void pause() {
        running = false;
    }

    protected void start() {
        onStartGame();
    }

    protected void stop() {
        // TODO : action at end game
        running = false;
        onStopGame();
        lock();
    }

    public void unlock() {
        if (!running) {
            lock.setVisibility(View.GONE);
            running = true;
            start();
        }
    }

    public void lock() {
        pause();
        lock.setVisibility(View.VISIBLE);
    }

    // BUTTON ACTIONS

    public abstract void onSubmit(long answer);
    public abstract void onPauseGame();
    public abstract void onStartGame();
    public abstract void onStopGame();

    private void submit() {
        if (answer != null)
            onSubmit(answer);
        else
            emptyAnswerToast.show();
    }

    private void changeSignum() {
        if (answer != null) {
            vibrate(100);
            answer = -answer;
            updateAnswer();
        }
    }

    private void addDigit(long digit) {
        vibrate(100);
        if (answer == null)
            answer = digit;
        else {
            long newAnswer =  answer * 10 + (answer < 0 ? -digit : digit);
            if (newAnswer <= LIMIT) {
                answer = newAnswer;
            }
        }
        updateAnswer();
    }

    public void clearAnswer() {
        vibrate(150);
        answer = null;
        updateAnswer();
    }

    // TOOL

    protected void vibrate(long millis) {
        vibrator.vibrate(VibrationEffect.createOneShot(millis, 1));
    }

    protected void nextComputation() {
        computation = Computation.generateComputation(difficulty);
        updateComputation();
    }

    protected void clearComputation() {
        computation = null;
        computationTextView.setText("");
    }

    // UPDATE

    public void updateAnswer() {
        if (answer == null)
            answerTextView.setText(R.string.answer);
        else
            answerTextView.setText(answer.toString());
    }

    public void updateLife(int life) {
        String hearts = "";
        for (int i = 0; i < life; i++)
            hearts += HEART;
        lifeTextView.setText(hearts);
    }

    public void updateComputation() {
        computationTextView.setText(computation.toString());
    }
}