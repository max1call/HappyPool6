
package com.example.max.happypool;

import android.graphics.drawable.Drawable;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class MakeStage3 extends MakeStage {

//     Map<String, Drawable> hashMapImg;
//     Map<String, Integer> hashMapSize;
//     Map<String, Object> gameObject;
//     ArrayList<Kuvshinka> arrayKuvshinka;
//     ArrayList<Heart> arrayHeart;
//     Hippo hippo;
//     Player player;
//     Splash splash;
//     Target target;
//     Kamish kamish;
//     Win win;
//     GameOver gameOver;
//     int xPlayer, yPlayer;
//     int xHippo, yHippo;

    MakeStage3(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread, Handler handler) {
        super(hashMapImg, hashMapSize, myThread, handler);
        myThread.getArray(arrayKuvshinka, arrayHeart, arrayHippo);
        getArray();
        makeKuvshinks();
//        this.hashMapImg = hashMapImg;
//        this.hashMapSize = hashMapSize;
//        arrayKuvshinka = new ArrayList<>();
//        arrayHeart = new ArrayList<>();
//
//        int lengthJump = hashMapSize.get("lengthJump");
//        int speedFly= lengthJump/10;
//        int speedHippo = lengthJump/70;
//        int timeUnderWater=2000;
//        int k = lengthJump/20;
//        newHeart(k, k);
//        newHeart(2*k+hashMapSize.get("heartWidth"), k);
//        newHeart(3*k+2*hashMapSize.get("heartWidth"), k);
//
//        makeKuvshinks();
//        myThread.setArray(arrayKuvshinka, arrayHeart);
//        hippo = new Hippo(hashMapImg, hashMapSize, speedHippo, xHippo, yHippo);
//        player = new Player(hashMapImg, hashMapSize , xPlayer, yPlayer, speedFly, myThread, hippo);
//
//        splash = new Splash(hashMapImg, hashMapSize , 0, 0, player);
//        kamish = new Kamish(hashMapImg, hashMapSize , 0, hashMapSize.get("mCanvasHeight")-hashMapSize.get("kamishHeight"));
//
//        gameOver = new GameOver(hashMapImg, hashMapSize, handler, hashMapSize.get("mCanvasWidth")/2, hashMapSize.get("mCanvasHeight")/2);
//        win = new Win(hashMapImg, hashMapSize , hashMapSize.get("mCanvasWidth")/2, hashMapSize.get("mCanvasHeight")/2);
//        putToHash();
//        myThread.ObjFromHash(gameObject);
//        myThread.setTimeUnderWater(timeUnderWater);
    }
//    @Override
    void makeKuvshinks(){
        int kHeading;
        double radians;
        int lengthJump = hashMapSize.get("lengthJump");

        int xKuvshinka = (int) (0.3*hashMapSize.get("kuvshinkaWidth"));//1
        int yKuvshinka =  hashMapSize.get("mCanvasHeight")/2;
//        newKuvshinka(xKuvshinka, yKuvshinka);
        setPositiovKuvsh(0, xKuvshinka, yKuvshinka);

//        xPlayer = xKuvshinka+(hashMapSize.get("kuvshinkaWidth")-hashMapSize.get("idleFrogWidth"))/2;
//        yPlayer = yKuvshinka+(hashMapSize.get("kuvshinkaHeight")-hashMapSize.get("idleFrogHeight"))/2;

        kHeading = 120;//2
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        xHippo = xKuvshinka;
        yHippo = yKuvshinka;
//        newHippo(xHippo, yHippo);
        setPositiovHippo(0, xHippo, yHippo);
//
        kHeading = 45;//3
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        newKuvshinka(xKuvshinka, yKuvshinka);
        setPositiovKuvsh(1, xKuvshinka, yKuvshinka);



        kHeading = 45; //4
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        xHippo = xKuvshinka;
        yHippo = yKuvshinka;
//        newHippo(xHippo, yHippo);
        setPositiovHippo(1, xHippo, yHippo);

        kHeading = 120;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        target.setPoisition(xKuvshinka, yKuvshinka);
//        target = new Target(hashMapImg, hashMapSize , xKuvshinka, yKuvshinka);
    }


//    private void putToHash() {
//        gameObject = new HashMap<>();
//        gameObject.put("hippo", hippo);
//        gameObject.put("player", player);
//        gameObject.put("splash", splash);
//        gameObject.put("kamish", kamish);
//        gameObject.put("mGameOver", gameOver);
//        gameObject.put("win", win);
//        gameObject.put("target", target);
//    }
//
//    private void newKuvshinka(int x, int y) {
//        Kuvshinka kuvshinka = new Kuvshinka(hashMapImg, hashMapSize , x, y);
//        arrayKuvshinka.add(kuvshinka);
//    }
//
//    private void newHeart(int x, int y) {
//        Heart heart = new Heart(hashMapImg, hashMapSize , x, y);
//        arrayHeart.add(heart);
//    }

}