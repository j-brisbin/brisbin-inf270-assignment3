package com.example.user.brisbinassignment3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.util.*;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Created by jbrisbin32 on 11/16/15.
 */
public class GameLogic{
    private int score;
    private int imageSelection;
    private boolean winGame;
    private boolean lostGame;
    private boolean gameStarted;
    private Context context;
    private Random randomImageSelection;
    private Random randomImageX;
    private Random randomImageY;
    private Random randomImageInterval;
    private Random pointValue;
    private ArrayList<Integer> imageIDs;
    private ArrayList<ImageSprite> imageSprites;
    private CountDownTimer imageChangeTimer;
    private int maxX;
    private int maxY;
    private long remainingTime;


    public GameLogic(Context context, int maxX, int maxY){
        this.context = context;
        this.winGame = false;
        this.lostGame = false;
        this.gameStarted = true;
        this.imageSprites = new ArrayList<>();
        this.imageIDs = new ArrayList<>();
        this.imageIDs.add(R.drawable.ic_android_black_18dp);
        this.imageIDs.add(R.drawable.ic_bookmark_black_18dp);
        this.imageIDs.add(R.drawable.ic_camera_enhance_black_18dp);
        this.imageIDs.add(R.drawable.ic_card_membership_black_18dp);
        this.imageIDs.add(R.drawable.ic_polymer_black_18dp);
        this.imageIDs.add(R.drawable.ic_thumb_up_black_18dp);
        this.imageIDs.add(R.drawable.ic_verified_user_black_18dp);
        this.randomImageSelection = new Random();
        this.randomImageX = new Random();
        this.randomImageY = new Random();
        this.randomImageInterval = new Random();
        this.pointValue = new Random();
        this.maxX = maxX;
        this.maxY = maxY;
        startGame();
    }

    public void draw(Canvas canvas){
        for(int i = 0;i<this.imageSprites.size();i++){
            if(!(this.imageSprites.get(i) == null)){
                this.imageSprites.get(i).setIsVisible(true);
                this.imageSprites.get(i).draw(canvas);
            }
            else{
                break;
            }
        }
    }

    public void startGame(){
        if(gameStarted){
            imageChangeTimer = new CountDownTimer(30000,1000){
                @Override
                public void onTick(long millisUntilFinished) {
                    if(millisUntilFinished>0){
                        for(int i = 0;i<randomImageInterval.nextInt(21) + 1;i++){
                            imageSprites.add(new ImageSprite(context.getResources(),
                                    imageIDs.get(randomImageSelection.nextInt(7)),
                                    randomImageX.nextInt(601) + 1,randomImageY.nextInt(601) + 1));
                        }
                        for(int i = 0;i<imageSprites.size();i++){
                            imageSprites.remove(i);
                        }
                    }
                    remainingTime = millisUntilFinished/1000;
                }

                @Override
                public void onFinish() {
                    Activity a = (Activity)GameLogic.this.context;
                    Intent intent = new Intent(a,HighscoreActivity.class);
                    intent.putExtra("score",score);
                    //below code line is courtesy of tips.androidhive.info
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    a.startActivity(intent);
                }
            }.start();
        }
    }


    public boolean onTouch(View v, MotionEvent event){
        for(int i = 0;i<imageSprites.size();i++) {
            if (imageSprites.get(i).getBounds().contains((int) event.getX(), (int) event.getY())){
                imageSprites.remove(i);
                score += (pointValue.nextInt(4) + 1);
                return true;
            }
        }
        return false;
    }

    public ImageSprite getImageSpriteByID(int i){
        return this.imageSprites.get(i);
    }

    public int getImageSpritesSize(){
        return this.imageSprites.size();
    }

    public void resetGame(){
        this.score = 0;
        startGame();
    }

    public long getRemainingTime(){
        return remainingTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getWinGame() {
        return winGame;
    }

    public void setWinGame(boolean winGame) {
        this.winGame = winGame;
    }

    public boolean getLostGame() {
        return lostGame;
    }

    public void setLostGame(boolean lostGame) {
        this.lostGame = lostGame;
    }

    public boolean getGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
}
