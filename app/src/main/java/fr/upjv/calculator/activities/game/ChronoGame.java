package fr.upjv.calculator.activities.game;

import android.os.CountDownTimer;

import fr.upjv.calculator.activities.GameActivity;
import fr.upjv.calculator.calcul.Calcul;

public class ChronoGame extends GameActivity {
    private CountDownTimer timer;

    @Override
    public void init() {
        Calcul calcul = generateCalcul();
        calculTextView.setText(calcul.toString());
        answerTextView.setText(calcul.result() + "");
    }

    @Override
    public void game() {

    }

    @Override
    public void end() {

    }

}
