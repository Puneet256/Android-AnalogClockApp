package com.example.clockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColourPicker extends AppCompatActivity {

    Button blackButton,whiteButton,orangeButton,redButton,blueButton,skyButton,turquoiseButton,greyButton,pinkButton,purpleButton,greenButton,emeraldButton;
    String key;
    public static final String SHARED_PREFS = "sharedPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_picker);


        blackButton = findViewById(R.id.blackButton);
        whiteButton = findViewById(R.id.whiteButton);
        orangeButton = findViewById(R.id.orangeButton);
        redButton = findViewById(R.id.redButton);
        blueButton = findViewById(R.id.blueButton);
        skyButton = findViewById(R.id.skyButton);
        turquoiseButton = findViewById(R.id.turquoiseButton);
        greyButton = findViewById(R.id.greyButton);
        pinkButton = findViewById(R.id.pinkButton);
        purpleButton = findViewById(R.id.purpleButton);
        greenButton = findViewById(R.id.greenButton);
        emeraldButton = findViewById(R.id.emeraldButton);
        key = getIntent().getStringExtra("key");
        System.out.println(key);

        blackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#000000");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        whiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#ffffff");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#ffa500");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#ff0000");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#0000FF");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        skyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#87ceeb");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        turquoiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#40e0d0");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        greyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#5f5f5f");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        pinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#ff6ec7");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        purpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#bc13fe");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#90ee90");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        emeraldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","#046307");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
