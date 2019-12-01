package com.example.clockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    TextView title;
    com.google.android.material.appbar.AppBarLayout tab;

    Button bgButton, clockButton, accentButton, applyButton;
    private static final String SHARED_PREFS = "sharePrefs";
    String bg_colour, clock_colour, accent_colour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bgButton = findViewById(R.id.bgButton);
        clockButton = findViewById(R.id.clockButton);
        accentButton = findViewById(R.id.accentButton);
        applyButton = findViewById(R.id.applyButton);
        title = findViewById(R.id.title);
        tab = findViewById(R.id.tab);

        title.setText("Settings");

        String[] current_colours = loadData();

        bg_colour = current_colours[0];
        clock_colour = current_colours[1];
        accent_colour = current_colours[2];

        System.out.println("onCreate: BG=" + current_colours[0] + " Clock= " + current_colours[1] + " Accent= " +  current_colours[2]);

        bgButton.setBackgroundColor(Color.parseColor(bg_colour));
        bgButton.setTextColor(Color.parseColor(clock_colour));
        clockButton.setBackgroundColor(Color.parseColor(clock_colour));
        clockButton.setTextColor(Color.parseColor(bg_colour));
        accentButton.setBackgroundColor(Color.parseColor(accent_colour));
        accentButton.setTextColor(Color.parseColor(bg_colour));


        bgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this, ColourPicker.class);
                i.putExtra("key","bg_colour");
                startActivityForResult(i, 1);
            }
        });

        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this, ColourPicker.class);
                i.putExtra("key","clock_colour");
                startActivityForResult(i, 2);
            }
        });

        accentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this, ColourPicker.class);
                i.putExtra("key","accent_colour");
                startActivityForResult(i, 3);
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                System.out.println("Result:" + result);
                bg_colour = result;
                saveData();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                System.out.println("Result:" + result);
                clock_colour = result;
                saveData();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        if (requestCode == 3) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                System.out.println("Result:" + result);
                accent_colour = result;
                saveData();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        String[] current_colours = loadData();

        bg_colour = current_colours[0];
        clock_colour = current_colours[1];
        accent_colour = current_colours[2];

        bgButton.setBackgroundColor(Color.parseColor(bg_colour));
        bgButton.setTextColor(Color.parseColor(clock_colour));
        clockButton.setBackgroundColor(Color.parseColor(clock_colour));
        clockButton.setTextColor(Color.parseColor(bg_colour));
        accentButton.setBackgroundColor(Color.parseColor(accent_colour));
        accentButton.setTextColor(Color.parseColor(bg_colour));
        title.setTextColor(Color.parseColor(accent_colour));
        tab.setBackgroundColor(Color.parseColor(bg_colour));
        applyButton.setTextColor(Color.parseColor(accent_colour));


    }

    public void saveData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("bg_colour", bg_colour);
        editor.putString("clock_colour", clock_colour);
        editor.putString("accent_colour", accent_colour);

        editor.apply();

    }

    public String[] loadData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String bg = sharedPreferences.getString("bg_colour", "#000000");
        String clock = sharedPreferences.getString("clock_colour", "#ffffff");
        String accent = sharedPreferences.getString("accent_colour", "#ffffff");

        System.out.println("onLoad: BG=" + bg + " Clock= " + clock + " Accent= " +  accent);

        String[] colours = {bg, clock, accent};
        return (colours);

    }
}
