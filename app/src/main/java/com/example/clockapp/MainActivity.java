package com.example.clockapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String SHARED_PREFS = "sharePrefs";

    private SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;
    private com.google.android.material.appbar.AppBarLayout bar;
    TabLayout tabLayout;
    int fabState = 0;
    TextView title;


    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        fab = findViewById(R.id.fab);
        bar = findViewById(R.id.bar);
        title = findViewById(R.id.title);
        fab.setImageResource(R.drawable.ic_settings_01);

        title.setText("Clock");

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {

                //Called when the scroll state changes.
            }

            @Override
            public void onPageScrolled(int position,
                                       float positionOffset, int positionOffsetPixels) {
                fabState = position;
                if (fabState == 0){
                    fab.setImageResource(R.drawable.ic_settings_01);
                }
                if (fabState == 1){
                    fab.setImageResource(R.drawable.ic_add_icon);
                }
            }

            @Override
            public void onPageSelected(int position) {
                fabState = position;
                if (fabState == 0){
                    fab.setImageResource(R.drawable.ic_settings_01);
                }
                if (fabState == 1){
                    fab.setImageResource(R.drawable.ic_add_icon);
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fabState == 0) {
                    Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(i);

                } else if (fabState == 1) {
                    Intent i = new Intent(MainActivity.this, NewTimer.class);
                    startActivity(i);

                } else {
                    Snackbar.make(view, "That shouldn't have happened", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

    }


        private void setupViewPager (ViewPager viewPager){
            SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
            adapter.addFragment(new ClockFragment(), "Clock");
            adapter.addFragment(new TimerFragment(), "Timer");
            viewPager.setAdapter(adapter);
        }

    @Override
    protected void onResume() {
        super.onResume();

        String colors[] = loadData();

        title.setTextColor(Color.parseColor(colors[2]));
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colors[2])));
        bar.setBackgroundColor(Color.parseColor(colors[0]));
        tabLayout.setBackgroundColor(Color.parseColor(colors[0]));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor(colors[2]));
        tabLayout.setTabTextColors(Color.parseColor(colors[1]), Color.parseColor(colors[2]));
    }

    public String[] loadData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String bg = sharedPreferences.getString("bg_colour", "#000000");
        String clock = sharedPreferences.getString("clock_colour", "#ffffff");
        String accent = sharedPreferences.getString("accent_colour", "#ffffff");

        String[] colours = {bg, clock, accent};
        return (colours);

    }

}