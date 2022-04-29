package com.example.chessclockfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playGame extends AppCompatActivity {
    private TextView countdownText;
    private TextView whiteScore;
    private TextView blackScore;

    private Button countdownButton;
    private Button countdownButton2;
    private Button whiteWin;
    private Button blackWin;
    private Button draw;



    private CountDownTimer countDownTimer;
    private CountDownTimer countDownTimer2;
    private long timeLeftInMillieseconds;
    private long timeLeftInMillieseconds2;
    private long timeHold;;
    private boolean timerRunning;
    private boolean timer2Running = false;
    private double whiteCounter;
    private double blackCounter;
    private int count = 0;
    private long newTime ;
    private Long inc;
    private String u_value;
    private String increment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        countdownText = findViewById(R.id.countdown_text);
        countdownButton =  findViewById(R.id.countdown_button);
        countdownButton2 = findViewById(R.id.countdown_button2);
        whiteWin = findViewById(R.id.whiteWin);
        blackWin = findViewById(R.id.blackWin);
        draw = findViewById(R.id.draw);
        whiteScore = findViewById(R.id.scoreWhite);
        blackScore = findViewById(R.id.scoreBlack);

        String u_value=getIntent().getStringExtra("ct");
        String increment=getIntent().getStringExtra("inc");
        inc = 100000L;//Long.parseLong(increment);
        timeLeftInMillieseconds = Long.parseLong(u_value);
        timeLeftInMillieseconds2 = Long.parseLong(u_value);
        timeHold = timeLeftInMillieseconds;

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });

        countdownButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerRunning) {

                } else {
                    startTimer();
                    stopTimer2();
                }
            }

        });

        whiteWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                whiteCounter++;
                whiteScore.setText(whiteCounter + "");
            }
        });

        blackWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blackCounter++;
                blackScore.setText(blackCounter +"");
            }
        });

        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whiteCounter += 0.5;
                blackCounter += 0.5;
                whiteScore.setText(whiteCounter + "");
                blackScore.setText(blackCounter +"");
            }
        });

        updateTimer1();
        updateTimer2();
    }


    public void startStop() {
        if (timer2Running) {

        } else {
            if (timerRunning) {
                stopTimer();
            } else {
                startTimer();
            }
        }
    }

    public void startStop2() {
        if(timerRunning) {
            stopTimer2();
        } else {
            startTimer2();
        }
    }






    public void startTimer() {
            countDownTimer = new CountDownTimer(timeLeftInMillieseconds, 1000) {
                @Override
                public void onTick(long l) {
                    timeLeftInMillieseconds = l;
                    updateTimer1();
                }

                @Override
                public void onFinish() {

                }
            }.start();


            //countdownButton.setText("Pause");
            timerRunning = true;


        //countdownButton.setText("Pause");


    }

    public void stopTimer() {
        countDownTimer.cancel();
        //countdownButton.setText("Start");
        timerRunning = false;
        //timeLeftInMillieseconds += inc;
        startTimer2();

    }

    public void startTimer2() {
            countDownTimer2 = new CountDownTimer(timeLeftInMillieseconds2, 1000) {
                @Override
                public void onTick(long l) {
                    timeLeftInMillieseconds2 = l;
                    updateTimer2();
                }

                @Override
                public void onFinish() {

                }
            }.start();



            //countdownButton.setText("Pause");
            timer2Running = true;



    }

    public void stopTimer2() {
        countDownTimer2.cancel();
        timer2Running = false;
        //timeLeftInMillieseconds2 += inc;
        //countdownButton.setText("Start");

    }

    public void updateTimer1() {
        int minutes = (int) timeLeftInMillieseconds / 60000;
        int seconds = (int) timeLeftInMillieseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10 ) timeLeftText += "0";
        timeLeftText += seconds;

        countdownButton.setText(timeLeftText);
    }

    public void updateTimer2() {
        int minutes = (int) timeLeftInMillieseconds2 / 60000;
        int seconds = (int) timeLeftInMillieseconds2 % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10 ) timeLeftText += "0";
        timeLeftText += seconds;

        countdownButton2.setText(timeLeftText);
    }




    public void reset(View view) {
        if(timerRunning) {
            countDownTimer.cancel();
        } else {
            countDownTimer2.cancel();
        }
        timeLeftInMillieseconds = timeHold;
        timeLeftInMillieseconds2 = timeHold;

        countdownButton.setText(timeHold / 60000 + ":00");
        countdownButton2.setText(timeHold / 60000 + ":00");
        timerRunning = false;
        timer2Running = false;


        /*blackCounter = 0;
        whiteCounter = 0;
        whiteScore.setText(0 + "");
        blackScore.setText(0 + "");*/
        //countdownButton;
    }


    public void returnToHome(View view) {
        Intent intent=new Intent(playGame.this, MainActivity.class);
        startActivity(intent);
    }

    public void swapScores(View view) {
        String valueHold = blackScore.getText().toString();
        blackScore.setText(whiteScore.getText().toString());
        whiteScore.setText(valueHold);
    }
}