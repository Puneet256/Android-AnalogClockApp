package com.example.clockapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class TimerFragment extends Fragment {

    LinearLayout timer_layout;
    private TextView time;
    Button startButton, stopButton;
    private boolean isRunning = false;
    private long timer;
    private long remaining;
    private CountDownTimer c;
    private static final String SHARED_PREFS = "sharePrefs";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timer_fragment, container, false);

        timer_layout = view.findViewById(R.id.timer_fragment);
        startButton = view.findViewById(R.id.startButton);
        stopButton = view.findViewById(R.id.stopButton);
        time = view.findViewById(R.id.display);
        timer = loadData();
        remaining = timer;
        stopButton.setVisibility(View.GONE);
        updateCountDownText();

        String[] colors = loadColours();

        timer_layout.setBackgroundColor(Color.parseColor(colors[0]));
        startButton.setBackgroundColor(Color.parseColor(colors[0]));
        stopButton.setBackgroundColor(Color.parseColor(colors[0]));
        startButton.setTextColor(Color.parseColor(colors[2]));
        stopButton.setTextColor(Color.parseColor(colors[2]));
        time.setTextColor(Color.parseColor(colors[2]));

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopButton.setVisibility(View.VISIBLE);
                if (!isRunning) {
                    stopButton.setText("Pause");
                    isRunning = true;
                    startTimer();
                }

                else if (isRunning) {

                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stopButton.getText().toString() == "Pause"  && isRunning) {
                    pauseTimer();
                }
                else if (stopButton.getText().toString() == "Stop")
                {
                    stopButton.setVisibility(View.GONE);
                    stopTimer();
                }
                stopButton.setText("Stop");
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        remaining = loadData();
        String[] colors = loadColours();

        timer_layout.setBackgroundColor(Color.parseColor(colors[0]));
        startButton.setBackgroundColor(Color.parseColor(colors[0]));
        stopButton.setBackgroundColor(Color.parseColor(colors[0]));
        startButton.setTextColor(Color.parseColor(colors[2]));
        stopButton.setTextColor(Color.parseColor(colors[2]));
        time.setTextColor(Color.parseColor(colors[2]));
        updateCountDownText();
    }


    public void startTimer(){
        c = new CountDownTimer(remaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remaining = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isRunning = false;
            }
        }.start();
    }

    public void pauseTimer(){
        c.cancel();
        isRunning = false;
    }

    public void stopTimer(){
        remaining = timer;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (remaining / 1000) / 60;
        int seconds = (int) (remaining / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        time.setText(timeLeftFormatted);
    }


    public long loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        timer = sharedPreferences.getLong("timer", (long) 60000);

        return timer;
    }

    public String[] loadColours() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String bg = sharedPreferences.getString("bg_colour", "#000000");
        String clock = sharedPreferences.getString("clock_colour", "#ffffff");
        String accent = sharedPreferences.getString("accent_colour", "#ffffff");

        System.out.println("timerfragment: BG=" + bg + " Clock= " + clock + " Accent= " +  accent);

        String[] colours = {bg, clock, accent};
        return (colours);

    }
}