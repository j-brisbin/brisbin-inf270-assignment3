package com.example.user.brisbinassignment3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private TextView scoreTextView;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Default initialization*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*Initialize needed variables*/
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        gameView = new GameView(this,scoreTextView);
        relativeLayout.addView(gameView);
    }
}
