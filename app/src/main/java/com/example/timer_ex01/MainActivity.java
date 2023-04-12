package com.example.timer_ex01;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private TextView Cnt_View;
    private Button startStopButton;
    private Button resetButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 60000; // 60 seconds
    private int cnt = 0;
    private boolean timerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timer_text_view);
        Cnt_View = findViewById(R.id.cnt_view);

        startStopButton = findViewById(R.id.start_stop_button);
        resetButton = findViewById(R.id.reset_button);

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                // Handle timer finish here
                timerRunning = false;
                startStopButton.setText("start");
            }
        }.start();

        timerRunning = true;
        startStopButton.setText("stop");
        resetButton.setVisibility(View.INVISIBLE);
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timerRunning = false;
        startStopButton.setText("start");
        resetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        timeLeftInMillis = 60000;
        updateTimer();
        resetButton.setVisibility(View.INVISIBLE);
    }

    private void updateTimer() {
        int seconds = (int) (timeLeftInMillis / 1000);
        String timeLeftFormatted = String.format("%02d", seconds);
        timerTextView.setText("time left: " + timeLeftFormatted);
        Cnt_View.setText("cnt: " + cnt + " set: " + cnt / 5);
        cnt++;
    }
}


