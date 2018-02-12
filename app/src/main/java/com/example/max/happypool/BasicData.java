package com.example.max.happypool;

import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.Map;

public class BasicData {

    private Kamish kamish;
    private Win win;
    private GameOver gameOver;
    private int xPlayer, yPlayer;

    BasicData(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread) {
        int lengthJump = hashMapSize.get("lengthJump");
        int speedFly= lengthJump/10;
        int speedHippo = lengthJump/70;
        int timeUnderWater=2000;
        int k = lengthJump/20;
        newHeart(k, k);
        newHeart(2*k+hashMapSize.get("heartWidth"), k);
        newHeart(3*k+2*hashMapSize.get("heartWidth"), k);
        player = new Player(hashMapImg, hashMapSize, speedFly, myThread, hippo);
        splash = new Splash(hashMapImg, hashMapSize , 0, 0, player);
        kamish = new Kamish(hashMapImg, hashMapSize , 0, hashMapSize.get("mCanvasHeight")-hashMapSize.get("kamishHeight"));
        gameOver = new GameOver(hashMapImg, hashMapSize, handler, hashMapSize.get("mCanvasWidth")/2, hashMapSize.get("mCanvasHeight")/2);
        win = new Win(hashMapImg, hashMapSize , hashMapSize.get("mCanvasWidth")/2, hashMapSize.get("mCanvasHeight")/2);
        putToHash();
        myThread.ObjFromHash(gameObject);
        myThread.setTimeUnderWater(timeUnderWater);


    }
    private void putToHash() {
        gameObject = new HashMap<>();
        gameObject.put("hippo", hippo);
        gameObject.put("player", player);
        gameObject.put("splash", splash);
        gameObject.put("kamish", kamish);
        gameObject.put("mGameOver", gameOver);
        gameObject.put("win", win);
        gameObject.put("target", target);
    }
    private void newHeart(int x, int y) {
        Heart heart = new Heart(hashMapImg, hashMapSize , x, y);
        arrayHeart.add(heart);
    }
}
