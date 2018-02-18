
package com.example.max.happypool;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Map;

class MakeStage {

    Map<String, Drawable> hashMapImg;
    Map<String, Integer> hashMapSize;
    Map<String, Object> gameObject;
    ArrayList<Kuvshinka> arrayKuvshinka;
    ArrayList<Heart> arrayHeart;
    ArrayList<Hippo> arrayHippo;
    Player player;
    Splash splash;
    Target target;
    Kamish kamish;
    Win win;
    GameOver gameOver;
    int xHippo, yHippo;
    private int speedHippo;
    private MyThread myThread;
    int speedFly;
    int timeUnderWater;
    int lengthJump;
    NextStage nextStage;
    MySound mySound;

    MakeStage(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread) {
        this.myThread = myThread;
        this.hashMapImg = hashMapImg;
        this.hashMapSize = hashMapSize;
        lengthJump = hashMapSize.get("lengthJump");
        speedFly= lengthJump/10;
        timeUnderWater=2500;
        speedHippo = lengthJump/70;
    }
    void setPositiovKuvsh(int numberOfKuvsh, int x, int y){
        arrayKuvshinka.get(numberOfKuvsh).setPoisition(x, y);
    }
    void setPositiovHippo(int numberOfHippo, int x, int y){
        arrayHippo.get(numberOfHippo).setPoisition(x, y);
    }
    void newKuvshinka(int x, int y) {
        Kuvshinka kuvshinka = new Kuvshinka(hashMapImg, hashMapSize , x, y);
        arrayKuvshinka.add(kuvshinka);
    }
    void newHeart(int x, int y) {
        Heart heart = new Heart(hashMapImg, hashMapSize , x, y);
        arrayHeart.add(heart);
    }
    void newHippo(int x, int y) {
        Hippo hippo = new Hippo(hashMapImg, hashMapSize, speedHippo, x, y);
        arrayHippo.add(hippo);
    }
    void getArray() {
        arrayKuvshinka = myThread.getArrayKuvshinka();
        arrayHeart = myThread.getArrayHeart();
        arrayHippo = myThread.getArrayHippo();
        target = myThread.getTarget();
    }
}