package com.example.chessclockfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class helpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page2);

    }

    public void returnToMain(View view) {
        Intent intent2=new Intent(helpPage.this, MainActivity.class);
        startActivity(intent2);
    }
}