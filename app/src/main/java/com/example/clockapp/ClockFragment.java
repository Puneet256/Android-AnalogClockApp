package com.example.clockapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import java.util.Calendar;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.Rectangle;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class ClockFragment extends Fragment {

    private static final String TAG = "Clock";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.clock_fragment, container, false);

        RelativeLayout relativeLayout = view.findViewById(R.id.clock_fragment);
        relativeLayout.addView(new ClockView(getActivity()));

        return view;
    }

    public class ClockView extends View {
        private int height, width = 0;
        private int padding = 0;
        private int numeralSpacing = 0;
        private int radius = 0;
        private Paint paint;
        private boolean isInit;
        private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        private int[] msNumbers = {12, 3, 6, 9};
        private Rect rect = new Rect();
        public int bg_colour;
        public int clock_colour;
        public int accent_colour;
        private static final String SHARED_PREFS = "sharePrefs";


        public ClockView(Context context) {
            super(context);
        }

        public ClockView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        private void initClock() {
            height = getHeight();
            width = getWidth();
            radius = width / 2 - 125;
            paint = new Paint();
            isInit = true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            if (!isInit) {
                initClock();
            }

            setColor();
            canvas.drawColor(bg_colour);
            drawCircle(canvas);
            drawMSCircle(canvas);
            drawCenter(canvas);
            drawNumeral(canvas);
            drawMSNums(canvas);
            drawHands(canvas);
            drawMSHand(canvas);

            invalidate();
        }

        private void drawHands(Canvas canvas) {
            Calendar c = Calendar.getInstance();
            float hour = c.get(Calendar.HOUR_OF_DAY);
            hour = hour > 12 ? hour - 12 : hour;
            drawHand(canvas, (hour * 5f) + (c.get(Calendar.MINUTE) * 5f / 60.0), true, false);
            drawHand(canvas, c.get(Calendar.MINUTE), false, true);
            drawHand(canvas, c.get(Calendar.SECOND), false, false);
        }

        private void drawHand(Canvas canvas, double loc, Boolean isHour, Boolean isMinute) {
            double angle = Math.PI * loc / 30 - Math.PI / 2;
            int handRadius = isHour ? radius - 210 : (isMinute ? radius - 100 : radius - 50);
            if (!isHour && !isMinute) {
                paint.setColor(accent_colour);
                paint.setStrokeWidth(2);
            }
            canvas.drawLine(width / 2, height / 2, (float) (width / 2 + Math.cos(angle) * handRadius), (float) (height / 2 + Math.sin(angle) * handRadius), paint);
        }

        private void drawMSHand(Canvas canvas) {
            Calendar c = Calendar.getInstance();
            float ms = (float) (c.get(Calendar.MILLISECOND) / 16.89);
            double angle = Math.PI * ms / 30 - Math.PI / 2;
            paint.setColor(accent_colour);
            paint.setStrokeWidth(2);
            canvas.drawLine((float) (width / 3.5), height / 2, (float) (width / 3.5 + Math.cos(angle) * (radius / 6 - 5)), (float) (height / 2 + Math.sin(angle) * (radius / 6)), paint);
        }


        private void drawNumeral(Canvas canvas) {
            paint.setTextSize(30);

            for (int number : numbers) {
                String tmp = String.valueOf(number);
                paint.getTextBounds(tmp, 0, tmp.length(), rect);
                double angle = Math.PI / 6 * (number - 3);
                int x = (int) (width / 2 + Math.cos(angle) * radius - rect.width() / 2);
                int y = (int) (height / 2 + Math.sin(angle) * radius + rect.height() / 2);
                canvas.drawText(tmp, x, y, paint);
            }
        }

        private void drawMSNums(Canvas canvas) {
            paint.setTextSize(30);

            for (int number : msNumbers) {
                String tmp = String.valueOf(number);
                paint.getTextBounds(tmp, 0, tmp.length(), rect);
                double angle = Math.PI / 6 * (number - 3);
                int x = (int) (width / 3.5 + Math.cos(angle) * (radius / 6 + 25) - rect.width() / 2);
                int y = (int) (height / 2 + Math.sin(angle) * (radius / 6 + 25) + rect.height() / 2);
                canvas.drawText(tmp, x, y, paint);
            }
        }

        private void drawCenter(Canvas canvas) {
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(width / 2, height / 2, 12, paint);
        }

        private void drawCircle(Canvas canvas) {
            paint.reset();
            paint.setColor(clock_colour);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            canvas.drawCircle(width / 2, height / 2, radius + 25, paint);
        }

        private void drawMSCircle(Canvas canvas) {
            paint.reset();
            paint.setColor(clock_colour);
            paint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            canvas.drawCircle((float) (width / 3.5), height / 2, radius / 6, paint);
        }

        private void setColor() {
            String[] colours = loadData();

            bg_colour = Color.parseColor(colours[0]);
            clock_colour = Color.parseColor(colours[1]);
            accent_colour = Color.parseColor(colours[2]);
        }


        public String[] loadData() {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            String bg = sharedPreferences.getString("bg_colour", "#000000");
            String clock = sharedPreferences.getString("clock_colour", "#ffffff");
            String accent = sharedPreferences.getString("accent_colour", "#ffa500");
            String[] colours = {bg, clock, accent};
            return (colours);

        }
    }
}
