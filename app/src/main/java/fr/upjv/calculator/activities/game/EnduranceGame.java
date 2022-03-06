package fr.upjv.calculator.activities.game;

import fr.upjv.calculator.activities.GameActivity;

public class EnduranceGame extends GameActivity {
    private int life = 3;
    private int rightAnswer = 0;

    @Override
    public void onSubmit(long answer) {
        if (answer == computation.result()) {
            rightAnswer++;
            score++;
            if (rightAnswer == 10) {
                rightAnswer = 0;
                if (life < 3)
                    life++;
            }
        } else {
            life--;
            if (life == 0)
                stop();
            rightAnswer = 0;
            vibrate(300);
        }
        updateLife(life);
        clearAnswer();
        nextComputation();
    }

    @Override
    public void onPauseGame() {
        clearComputation();
    }

    @Override
    public void onStartGame() {
        life = 3;
        nextComputation();
    }

    @Override
    public void onStopGame() {
        clearComputation();
    }

}
