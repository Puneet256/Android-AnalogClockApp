package com.example.clockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;


public class NewTimer extends AppCompatActivity {

    private long timer = 0;
    TextView display;
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0,backButton,startButton;
    long minutes;
    int seconds;
    int numcount = 0;
    int num;
    private static final String SHARED_PREFS = "sharePrefs";
    LinearLayout timer_layout;
    TextView title;
    com.google.android.material.appbar.AppBarLayout bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timer);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);
        backButton = findViewById(R.id.backButton);
        startButton = findViewById(R.id.startButton);
        display = findViewById(R.id.display);
        title = findViewById(R.id.title);
        bar = findViewById(R.id.bar);
        timer_layout = findViewById(R.id.timer_layout);

        minutes = (timer / 1000)  / 60;
        seconds = (int)((timer / 1000) % 60);

        display.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));


        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(0);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(4);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(5);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(6);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(7);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(8);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(9);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minutes = 0;
                seconds = 0;
                num = 0;
                numcount = 0;
                display.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer = minutes*60000 + seconds*1000;
                setResult(RESULT_OK);
                saveData();
                finish();
            }
        });

        String[] colors = loadColours();

        title.setTextColor(Color.parseColor(colors[2]));
        bar.setBackgroundColor(Color.parseColor(colors[0]));
        timer_layout.setBackgroundColor(Color.parseColor(colors[0]));
        display.setTextColor(Color.parseColor(colors[2]));
        button0.setBackgroundColor(Color.parseColor(colors[2]));
        button1.setBackgroundColor(Color.parseColor(colors[2]));
        button2.setBackgroundColor(Color.parseColor(colors[2]));
        button3.setBackgroundColor(Color.parseColor(colors[2]));
        button4.setBackgroundColor(Color.parseColor(colors[2]));
        button5.setBackgroundColor(Color.parseColor(colors[2]));
        button6.setBackgroundColor(Color.parseColor(colors[2]));
        button7.setBackgroundColor(Color.parseColor(colors[2]));
        button8.setBackgroundColor(Color.parseColor(colors[2]));
        button9.setBackgroundColor(Color.parseColor(colors[2]));
        backButton.setTextColor(Color.parseColor(colors[2]));
        startButton.setTextColor(Color.parseColor(colors[2]));


    }

    public void updateDisplay(int i){
        switch (numcount){
            case 0:
                num = i;
                numcount++;
                break;
            case 1:
                num = num*10 + i;
                numcount++;
                break;
            case 2:
                num = num*10 + i;
                numcount++;
                break;
            case 3:
                num = num*10 + i;
                numcount++;
                break;
            case 4:
                num = (num%1000)*10 + i;
                numcount = 4;
                break;
        }
        minutes = num/100;
        seconds = num%100;
        display.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));

    }
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong("timer", timer);

        editor.apply();

    }
    public String[] loadColours() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String bg = sharedPreferences.getString("bg_colour", "#000000");
        String clock = sharedPreferences.getString("clock_colour", "#ffffff");
        String accent = sharedPreferences.getString("accent_colour", "#ffffff");

        String[] colours = {bg, clock, accent};
        return (colours);

    }
}
