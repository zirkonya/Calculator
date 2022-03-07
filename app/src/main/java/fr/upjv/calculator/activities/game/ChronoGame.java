package fr.upjv.calculator.activities.game;

import android.os.CountDownTimer;

import fr.upjv.calculator.activities.GameActivity;
import fr.upjv.calculator.computation.Computation;

public class ChronoGame extends GameActivity {
    private CountDownTimer countDownTimer;

    @Override
    public void onSubmit(long answer) {
        if (answer == computation.result())
            score++;
        else {
            vibrate(300);
            failToast.show();
        }
        clearAnswer();
        nextComputation();
    }

    @Override
    public void onInit() {
        countDownTimer = new CountDownTimer(300000,1000) {
            @Override
            public void onTick(long l) {
                updateClock(l);
            }

            @Override
            public void onFinish() {
                stop();
            }
        };
    }

    @Override
    public void onPauseGame() {

    }

    @Override
    public void onStartGame() {
        countDownTimer.start();
        nextComputation();
    }

    @Override
    public void onStopGame() {

    }

}
