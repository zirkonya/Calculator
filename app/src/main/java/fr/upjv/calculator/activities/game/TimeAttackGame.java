package fr.upjv.calculator.activities.game;

import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

import fr.upjv.calculator.activities.GameActivity;

public class TimeAttackGame extends GameActivity {
    private Timer timer;
    private TimerTask timerTask;
    private long time;

    @Override
    public void onInit() {
        timer = new Timer();
        time = 300000;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                time -= 1000;
                updateClock(time);
                if (time <= 0)
                    stop();
            }
        };
    }

    @Override
    public void onSubmit(long answer) {
        if (answer == computation.result()) {
            time += 1000;
            updateClock(time);
            score++;
        } else {
            vibrate(300);
            failToast.show();
        }
        clearAnswer();
        nextComputation();
    }

    @Override
    public void onStartGame() {
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        nextComputation();
    }

    @Override
    public void onPauseGame() {

    }

    @Override
    public void onStopGame() {

    }

    @Override
    public Intent onSaveResult() {
        return null;
    }
}
