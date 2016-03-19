package com.example.user.brisbinassignment3;

/**
 * Created by brisbij on 10/26/2015.
 */
public class HighScore {
    private long id;
    private long userId;
    private long highScore;
    private long timeStamp;

    public HighScore(){

    }

    public HighScore(long userId, long highScore, long timeStamp){
        this.userId = userId;
        this.highScore = highScore;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getHighScore() {
        return highScore;
    }

    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
