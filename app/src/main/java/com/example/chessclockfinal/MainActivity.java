package com.example.chessclockfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button timeV1;
    private Button timeV2;
    private Button timeV3;
    private Button timeV5;
    private Button timeV10;
    private Button timeV30;
    private Button addTime;

    private Spinner spinner;

    private EditText customT;
    private EditText customIncrement;

    public TextView currentT;

    private long timePH;
    private String inc;
    private long incCalc;

    dbHelper sql;


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
        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        sql = new dbHelper(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getId() == R.id.spinner) {
                    String sel = parent.getSelectedItem().toString();
                    Toast.makeText(MainActivity.this, "Selected: " + sel, Toast.LENGTH_SHORT).show();
                    changeTime(Integer.parseInt(sel));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                arrayList.add(ph);
                arrayAdapter.notifyDataSetChanged();

                Boolean insertTime = sql.insertTime(ph);
                if (insertTime == true)
                {
                    Toast.makeText(MainActivity.this,"New time Added", Toast.LENGTH_SHORT);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"New time Failed", Toast.LENGTH_SHORT);
                }
                changeTime(Integer.parseInt(ph));
                //getSpinnerTime();
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

    public void getSpinnerTime(){
        Cursor res = sql.getTime();
        if (res.getCount()==0)
        {
            Toast.makeText(MainActivity.this, "No time Available", Toast.LENGTH_SHORT);
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Time: "+ res.getString(0) + "\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Times");
        builder.setMessage(buffer.toString());
        builder.show();
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

    public void toHelp(View view) {
        Intent intent2=new Intent(MainActivity.this, helpPage.class);
        startActivity(intent2);
    }
}