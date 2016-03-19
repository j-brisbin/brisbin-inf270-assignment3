package com.example.user.brisbinassignment3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TopScoresActivity extends AppCompatActivity {

    private ListView scoreListView;
    private ListAdapter adapter;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        scoreListView = (ListView)findViewById(R.id.scoreListView);
        b = getIntent().getExtras();
        final Long userID = b.getLong("userid");
        HighscoreDBManager dbManager = new HighscoreDBManager(this.getApplicationContext());
        Cursor cursorJoin = dbManager.getJoinCursor();
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                cursorJoin,
                new String[]{
                        HighscoreDBManager.USER_NAME,
                        HighscoreDBManager.HS_SCORE,
                },
                new int[] {
                        android.R.id.text1,
                        android.R.id.text2
                },
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        scoreListView.setAdapter(adapter);
        scoreListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        scoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopScoresActivity.this, MainActivity.class);
                //below code line is courtesy of tips.androidhive.info
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v, "Tap on this button to restart the game.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        final Long userID = b.getLong("userid");
        HighscoreDBManager dbManager = new HighscoreDBManager(this.getApplicationContext());
        //noinspection SimplifiableIfStatement
        if (id == R.id.top5_high_scores) {
            Cursor cursor = dbManager.getAllHighScoresByUser(userID,5);
            adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.two_line_list_item,
                    cursor,
                    new String[]{
                            HighscoreDBManager.HS_USERNAME_ID,
                            HighscoreDBManager.HS_SCORE,
                    },
                    new int[] {
                            android.R.id.text1,
                            android.R.id.text2
                    },
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        }
        else if (id == R.id.usertop5_high_scores) {
            Cursor cursorJoin = dbManager.getJoinCursor();
            adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.two_line_list_item,
                    cursorJoin,
                    new String[]{
                            HighscoreDBManager.USER_NAME,
                            HighscoreDBManager.HS_SCORE,
                    },
                    new int[] {
                            android.R.id.text1,
                            android.R.id.text2
                    },
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        }
        scoreListView.setAdapter(adapter);
        scoreListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        scoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        return super.onOptionsItemSelected(item);
    }

}
