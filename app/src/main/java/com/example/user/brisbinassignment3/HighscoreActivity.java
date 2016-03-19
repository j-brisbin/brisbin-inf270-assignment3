package com.example.user.brisbinassignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class HighscoreActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private EditText usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        usernameText = (EditText)findViewById(R.id.usernameText);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        scoreTextView.setText(b.getInt("score") + "");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent highScoreIntent = new Intent(HighscoreActivity.this,TopScoresActivity.class);
                HighscoreDBManager dbManager = new HighscoreDBManager(HighscoreActivity.this);
                User user = new User(usernameText.getText().toString());
                user = dbManager.addUser(user);
                HighScore highScore = new HighScore(user.getId(),
                        Long.parseLong(scoreTextView.getText().toString()),
                        (new Date().getTime()));
                highScore = dbManager.addHighScore(highScore);
                highScoreIntent.putExtra("username",user.getUsername());
                highScoreIntent.putExtra("userid",user.getId());
                startActivity(highScoreIntent);
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v, "Tap on this button to submit your highscore", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return false;
            }
        });
    }

}
