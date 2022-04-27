package com.example.chessclockfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button timeV1;
    private Button timeV2;
    private Button timeV3;
    private Button timeV5;
    private Button timeV10;
    private Button timeV30;
    private Button addTime;

    private EditText customT;
    private EditText customIncrement;

    public TextView currentT;

    private long timePH;
    private String inc;
    private long incCalc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeV1 = findViewById(R.id.time1);
        timeV2 = findViewById(R.id.time2);
        timeV3 = findViewById(R.id.time3);
        timeV5 = findViewById(R.id.time5);
        timeV10 = findViewById(R.id.time10);
        timeV30 = findViewById(R.id.time30);
        addTime = findViewById(R.id.addTime);

        currentT = findViewById(R.id.currentTime);
        customIncrement = findViewById(R.id.customInc);
        customT = findViewById(R.id.customTime);

        timeV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime(1);
            }
        });

        timeV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime(2);
            }
        });

        timeV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime(3);
            }
        });

        timeV5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime(5);
            }
        });

        timeV10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime(10);

            }
        });

        timeV30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime(30);
            }
        });

        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph = customT.getText().toString();
                changeTime(Integer.parseInt(ph));
            }
        });

        customIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc = customIncrement.getText().toString();
                changeTimeSeconds(Integer.parseInt(inc));
            }
        });


    }



    public void changeTime(int a) {
        timePH = a * 60000;
        currentT.setText(a + ":00");
    }

    public void changeTimeSeconds(int a) {
        incCalc = a * 6000;
    }

    public void play(View view) {
        String timePHSend = timePH + "";
        String incPHSend = incCalc + "";
        long millisInput = Long.parseLong(timePHSend) * 60000;
        if (timePHSend.length() == 0) {
            Toast.makeText(MainActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
            return;
        } else if (millisInput == 0) {
            Toast.makeText(MainActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Intent intent=new Intent(MainActivity.this, playGame.class);
            intent.putExtra("ct",timePHSend);
            intent.putExtra("inc",incPHSend);
            startActivity(intent);
        }
    }
}