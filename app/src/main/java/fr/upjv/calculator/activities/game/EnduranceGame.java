package fr.upjv.calculator.activities.game;

import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

import fr.upjv.calculator.activities.GameActivity;

public class EnduranceGame extends GameActivity {
    private Timer timer;
    private long time = 0;
    private int life;
    private int rightAnswer;

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
            failToast.show();
        }
        updateLife(life);
        clearAnswer();
        nextComputation();
    }

    @Override
    public void onInit() {
        life = 3;
        rightAnswer = 0;
        updateLife(life);
        timer = new Timer();
    }

    @Override
    public void onPauseGame() {
        clearComputation();
    }

    @Override
    public void onStartGame() {
        life = 3;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time += 1000;
                updateClock(time);
            }
        }, 0, 1000);
        nextComputation();
    }

    @Override
    public void onStopGame() {
        clearComputation();
    }

    @Override
    public Intent onSaveResult() {
        return null;
    }

}
