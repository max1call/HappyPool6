package com.example.max.happypool;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


class MakeStage1 {


    private Map<String, Drawable> hashMapImg;
    private Map<String, Integer> hashMapSize;
    private Map<String, Object> gameObject;
//    MakeDrawable makeDrawable;
    private ArrayList<Kuvshinka> arrayKuvshinka;
    private ArrayList<Heart> arrayHeart;
    private Background background;
    Hippo hippo;
    Player player;
    Kuvshinka kuvshinka;
    Splash splash;
    Target target;
    Kamish kamish;
    InputOutput inputOutput;
    Win win;
    GameOver gameOver;
    Heart heart;
    MyThread myThread;
    int xPlayer, yPlayer, xHippo, yHippo;

    MakeStage1(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread) {
        this.hashMapImg = hashMapImg;
        this.hashMapSize = hashMapSize;
        this.myThread = myThread;
        arrayKuvshinka = new ArrayList<Kuvshinka>();
        arrayHeart = new ArrayList<Heart>();

        int lengthJump = hashMapSize.get("lengthJump");
        int speedFly= lengthJump/10;
        int xHippo = 300;
        int yHippo = 200;
        int speedHippo = lengthJump/70;
        int timeUnderWater=2000;

        int k = lengthJump/20;
        newHeart(k, k);
        newHeart(2*k+hashMapSize.get("heartWidth"), k);
        newHeart(3*k+2*hashMapSize.get("heartWidth"), k);

        makeKuvshinks();
        myThread.setArray(arrayKuvshinka, arrayHeart);
        hippo = new Hippo(hashMapImg, hashMapSize, speedHippo, xHippo, yHippo);
        player = new Player(hashMapImg, hashMapSize , xPlayer, yPlayer, speedFly, myThread, hippo);

        splash = new Splash(hashMapImg, hashMapSize , 0, 0, player);
        kamish = new Kamish(hashMapImg, hashMapSize , 0, hashMapSize.get("mCanvasHeight")-hashMapSize.get("kamishHeight"));

        gameOver = new GameOver(hashMapImg, hashMapSize , hashMapSize.get("mCanvasWidth")/2, hashMapSize.get("mCanvasHeight")/2);
        win = new Win(hashMapImg, hashMapSize , hashMapSize.get("mCanvasWidth")/2, hashMapSize.get("mCanvasHeight")/2);
        putToHash();
        myThread.ObjFromHash(gameObject);
        myThread.setTimeUnderWater(timeUnderWater);
    }
    private void setBeginValue(){

    }
    private void makeKuvshinks(){
        int kHeading;
        double radians;
        int lengthJump = hashMapSize.get("lengthJump");
        int xKuvshinka = (int) (0.3*hashMapSize.get("kuvshinkaWidth"));
        int yKuvshinka = (int) (hashMapSize.get("mCanvasHeight") - 3*hashMapSize.get("kuvshinkaHeight"));
        newKuvshinka(xKuvshinka, yKuvshinka);
        int xPlayer = xKuvshinka+(hashMapSize.get("kuvshinkaWidth")-hashMapSize.get("idleFrogWidth"))/2;
        int yPlayer = yKuvshinka+(hashMapSize.get("kuvshinkaHeight")-hashMapSize.get("idleFrogHeight"))/2;

        kHeading = 35;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        newKuvshinka(xKuvshinka, yKuvshinka);

        kHeading = 115;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        newKuvshinka(xKuvshinka, yKuvshinka);

        kHeading = 90;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        xHippo = xKuvshinka;
        yHippo = yKuvshinka;

        kHeading = 35;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));

        target = new Target(hashMapImg, hashMapSize , xKuvshinka, yKuvshinka);
    }

    private void putToHash() {
        gameObject = new HashMap<String, Object>();
        gameObject.put("hippo", hippo);
        gameObject.put("player", player);
        gameObject.put("splash", splash);
        gameObject.put("kamish", kamish);
        gameObject.put("gameOver", gameOver);
        gameObject.put("win", win);
        gameObject.put("target", target);
    }

    private void newKuvshinka(int x, int y) {
        kuvshinka = new Kuvshinka(hashMapImg, hashMapSize , x, y);
        arrayKuvshinka.add(kuvshinka);
    }

    private void newHeart(int x, int y) {
        heart = new Heart(hashMapImg, hashMapSize , x, y);
        arrayHeart.add(heart);
    }

    public Map<String, Object> ObjFromHash() {
        return gameObject;
    }
}
