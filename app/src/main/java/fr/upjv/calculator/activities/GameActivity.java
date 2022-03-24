package fr.upjv.calculator.activities;

import fr.upjv.calculator.R;
import fr.upjv.calculator.computation.*;
import fr.upjv.calculator.tools.Difficulty;
import fr.upjv.calculator.tools.GameMode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public abstract class GameActivity extends AppCompatActivity {
    private static final char HEART = '♥';
    private static final long LIMIT = 62500000000l;

    private Long answer;
    private boolean running;
    private AlertDialog popup;
    private Vibrator vibrator;
    private GameMode gameMode;
    private ConstraintLayout lock;
    private Difficulty difficulty;
    private TextInputEditText usernameInputText;

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
        GameActivity activity = this;

        int gameMode = getIntent().getIntExtra("gameMode", 0);
        int difficulty = getIntent().getIntExtra("difficulty", 0);

        this.gameMode = GameMode.values()[gameMode];
        this.difficulty = Difficulty.values()[difficulty];

        AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);

        popupBuilder.setView(findViewById(R.id.popup_nameInputText));

        popupBuilder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                saveResult(usernameInputText);
            }
        });

        popupBuilder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishActivity(0);
            }
        });

        score = 0;
        running = false;
        lock = findViewById(R.id.game_lock);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        failToast = Toast.makeText(this, R.string.fail, Toast.LENGTH_SHORT);
        emptyAnswerToast = Toast.makeText(this, R.string.answerMissing, Toast.LENGTH_LONG);

        Button submitButton = findViewById(R.id.game_keyboardSubmitButton);
        Button signumButton = findViewById(R.id.game_keyboardSignumButton);
        FloatingActionButton clearFloatingActionButton = findViewById(R.id.game_clearComputeFloatingButton);
        FloatingActionButton deleteFloatingActionButton = findViewById(R.id.game_deleteFloatingButton);

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
        deleteFloatingActionButton.setOnClickListener(view -> deleteAnswer());

        lifeTextView = findViewById(R.id.game_lifeTextView);
        clockTextView = findViewById(R.id.game_clockTextView);
        answerTextView = findViewById(R.id.game_answerTextView);
        gameModeTextView = findViewById(R.id.game_gameModeTextView);
        computationTextView = findViewById(R.id.game_computationTextView);

        lock.setOnClickListener(view -> unlock());
        lock.setBackgroundResource(R.color.transparent_black);

        gameModeTextView.setText(this.gameMode.getName());
        computation = Computation.generateComputation(this.difficulty);

        onInit();
    }

    /**
     * met pause au jeu
     */
    protected void pause() {
        running = false;
        onPauseGame();
    }

    /**
     * démarre le jeu
     */
    protected void start() {
        onStartGame();
    }

    /**
     * fin du jeu
     */
    protected void stop() {
        running = false;
        onStopGame();
        popup.show();
        //finishActivity(0);
    }

    /**
     * dévérouille le jeu
     */
    public void unlock() {
        if (!running) {
            lock.setVisibility(View.GONE);
            running = true;
            start();
        }
    }

    /**
     * vérouille le jeu
     */
    public void lock() {
        pause();
        lock.setVisibility(View.VISIBLE);
    }

    public abstract void onInit();
    public abstract void onSubmit(long answer);
    public abstract void onStartGame();
    public abstract void onPauseGame();
    public abstract void onStopGame();
    public abstract Intent onSaveResult();
    // BUTTON ACTIONS

    /**
     * enregistre les informations afin de l'envoyé dans la base de donnée
     */
    public void saveResult(TextInputEditText usernameInputText) {
        Intent intent = onSaveResult();
        startActivity(intent);
        finishActivity(0);
    }

    /**
     * valide la réponse si elle n'est pas null
     */
    private void submit() {
        if (answer != null)
            onSubmit(answer);
        else
            emptyAnswerToast.show();
    }

    /**
     * change le signe de la réponse
     */
    private void changeSignum() {
        if (answer != null) {
            vibrate(100);
            answer = -answer;
            updateAnswer();
        }
    }

    /**
     * ajoute un digit à la réponse
     * @param digit
     */
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

    /**
     * supprime entiérement la réponse
     */
    public void clearAnswer() {
        vibrate(150);
        answer = null;
        updateAnswer();
    }

    /**
     * retire retire le dernier digit de la réponse
     */
    protected void deleteAnswer() {
        if (answer == null) return;
        if (Math.abs(answer) < 10) answer = null;
        else answer = answer / 10;
        updateAnswer();
    }

    // TOOL

    /**
     * fait vibrer le téléphone pendant x millisecondes
     * @param millis
     */
    protected void vibrate(long millis) {
        vibrator.vibrate(VibrationEffect.createOneShot(millis, 1));
    }

    /**
     * affiche le calcul suivant
     */
    protected void nextComputation() {
        computation = Computation.generateComputation(difficulty);
        updateComputation();
    }

    /**
     * retire le calcul
     */
    protected void clearComputation() {
        computation = null;
        computationTextView.setText("");
    }

    // UPDATE

    /**
     * affiche au format mm:ss les millisecondes passée en paramètre
     * @param millis
     */
    public void updateClock(long millis) {
        millis /= 1000;
        int min = (int) (millis / 60);
        int sec = (int) (millis - min * 60);
        clockTextView.setText(String.format("%02d:%02d", min, sec));
    }

    /**
     * affiche la réponse entrée par l'utilisateur
     */
    public void updateAnswer() {
        if (answer == null)
            answerTextView.setText(R.string.answer);
        else
            answerTextView.setText(answer.toString());
    }

    /**
     * affiche la vie passée en paramètre
     * @param life
     */
    public void updateLife(int life) {
        String hearts = "";
        for (int i = 0; i < life; i++)
            hearts += HEART;
        lifeTextView.setText(hearts);
    }

    /**
     * affiche le calcul actuel
     */
    private void updateComputation() {
        computationTextView.setText(computation.toString());
    }

}