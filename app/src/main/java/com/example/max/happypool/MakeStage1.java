package com.example.max.happypool;

import android.graphics.drawable.Drawable;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class MakeStage1 {

    private Map<String, Drawable> hashMapImg;
    private Map<String, Integer> hashMapSize;
    private Map<String, Object> gameObject;
    private ArrayList<Kuvshinka> arrayKuvshinka;
    private ArrayList<Heart> arrayHeart;
    private Hippo hippo;
    private Player player;
    private Splash splash;
    private Target target;

    private int xHippo, yHippo;

    MakeStage1(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread, Handler handler) {
        this.hashMapImg = hashMapImg;
        this.hashMapSize = hashMapSize;
        arrayKuvshinka = new ArrayList<>();
        arrayHeart = new ArrayList<>();
        makeKuvshinks();
        myThread.setArray(arrayKuvshinka, arrayHeart);
        hippo = new Hippo(hashMapImg, hashMapSize, speedHippo, xHippo, yHippo);
        putToHash();
        myThread.ObjFromHash(gameObject);
    }
    private void makeKuvshinks(){
        int kHeading;
        double radians;
        int lengthJump = hashMapSize.get("lengthJump");
        int xKuvshinka = (int) (0.3*hashMapSize.get("kuvshinkaWidth"));
        int yKuvshinka = (hashMapSize.get("mCanvasHeight") - 3*hashMapSize.get("kuvshinkaHeight"));
        newKuvshinka(xKuvshinka, yKuvshinka);
        xPlayer = xKuvshinka+(hashMapSize.get("kuvshinkaWidth")-hashMapSize.get("idleFrogWidth"))/2;
        yPlayer = yKuvshinka+(hashMapSize.get("kuvshinkaHeight")-hashMapSize.get("idleFrogHeight"))/2;

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
        gameObject = new HashMap<>();
        gameObject.put("hippo", hippo);
        gameObject.put("player", player);
        gameObject.put("splash", splash);
        gameObject.put("kamish", kamish);
        gameObject.put("mGameOver", gameOver);
        gameObject.put("win", win);
        gameObject.put("target", target);
    }
    private void newKuvshinka(int x, int y) {
        Kuvshinka kuvshinka = new Kuvshinka(hashMapImg, hashMapSize , x, y);
        arrayKuvshinka.add(kuvshinka);
    }
    public void newStage(){

    }

}
