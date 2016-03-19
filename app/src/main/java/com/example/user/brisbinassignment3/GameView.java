package com.example.user.brisbinassignment3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by User on 9/24/2015.
 */
public class GameView extends View{
    private GameLogic game;
    private Intent loseIntent;
    private Intent winIntent;
    private TextView textView;

    public GameView(Context context, TextView textView){
        super(context);
        super.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return game.onTouch(v, event);
            }
        });
        this.textView = textView;
        this.game = new GameLogic(context,this.getWidth(),getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.textView.setText("Score: " + game.getScore() +
                "\nTime Remaining: " + game.getRemainingTime());
        this.game.draw(canvas);
        invalidate();
    }
}
