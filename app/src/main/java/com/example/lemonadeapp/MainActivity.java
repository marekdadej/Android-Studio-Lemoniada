package com.example.lemonadeapp;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView lemonTree, lemon, glass, emptyGlass;
    private TextView encourageText;
    private int lemonClicksRequired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lemonTree = findViewById(R.id.lemontree);
        encourageText = findViewById(R.id.encourageText);

        lemonTree.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                showLemonSqueezeScreen();
                return true;
            }
            return false;
        });
    }

    private void showLemonSqueezeScreen() {
        Random random = new Random();
        lemonClicksRequired = random.nextInt(3) + 2;

        setContentView(R.layout.activity_lemon_squeeze);

        lemon = findViewById(R.id.lemon);
        encourageText = findViewById(R.id.encourageText);
        encourageText.setText("Naciśnij cytrynę, aby ją wycisnąć!");

        lemon.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                lemonClicksRequired--;
                if (lemonClicksRequired <= 0) {
                    showLemonadeScreen();
                } else {
                    Toast.makeText(this, "Kliknięć pozostało: " + lemonClicksRequired, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });
    }

    private void showLemonadeScreen() {
        setContentView(R.layout.activity_lemonade);
        glass = findViewById(R.id.glass);
        encourageText = findViewById(R.id.encourageText);
        encourageText.setText("Kliknij w szklankę, aby wypić lemoniadę!");

        glass.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                showEmptyGlassScreen();
                return true;
            }
            return false;
        });
    }

    private void showEmptyGlassScreen() {
        setContentView(R.layout.activity_empty_glass);
        encourageText = findViewById(R.id.encourageText);
        encourageText.setText("Dotknij pustej szklanki, aby zacząć od nowa!");

        emptyGlass = findViewById(R.id.empty_glass);
        emptyGlass.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                setContentView(R.layout.activity_main);
                lemonTree = findViewById(R.id.lemontree);
                encourageText = findViewById(R.id.encourageText);
                encourageText.setText("Dotknij drzewa cytrynowego, aby zerwać cytrynę");

                lemonTree.setOnTouchListener((v1, event1) -> {
                    if (event1.getAction() == MotionEvent.ACTION_DOWN) {
                        showLemonSqueezeScreen();
                        return true;
                    }
                    return false;
                });
                return true;
            }
            return false;
        });
    }
}