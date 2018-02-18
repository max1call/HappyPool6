
package com.example.max.happypool;

        import android.graphics.drawable.Drawable;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;

class MakeStage1 extends MakeStage{

    MakeStage1(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread) {
        super(hashMapImg, hashMapSize, myThread);
        arrayKuvshinka = new ArrayList<>();
        arrayHeart = new ArrayList<>();
        arrayHippo = new ArrayList<>();
        int k = lengthJump/20;
        newHeart(k, k);
        newHeart(2*k+hashMapSize.get("heartWidth"), k);
        newHeart(3*k+2*hashMapSize.get("heartWidth"), k);
        makeKuvshinks();
        myThread.setArray(arrayKuvshinka, arrayHeart, arrayHippo);
        player = new Player(hashMapImg, hashMapSize , 0, 0, speedFly, myThread);
        splash = new Splash(hashMapImg, hashMapSize , 0, 0, player);
        kamish = new Kamish(hashMapImg, hashMapSize , 0, hashMapSize.get("mCanvasHeight")-hashMapSize.get("kamishHeight"));
        gameOver = new GameOver(hashMapImg, hashMapSize, hashMapSize.get("mCanvasWidth")/2, hashMapSize.get("mCanvasHeight")/2);
        win = new Win(hashMapImg, hashMapSize , hashMapSize.get("mCanvasWidth")/2, hashMapSize.get("mCanvasHeight")/2);
        nextStage = new NextStage(hashMapImg, hashMapSize, myThread, hashMapSize.get("mCanvasWidth")/2, hashMapSize.get("mCanvasHeight")/2);
        mySound = new MySound(myThread.getContext());
        putToHash();
        myThread.ObjFromHash(gameObject);
        myThread.setTimeUnderWater(timeUnderWater);
    }
    private void makeKuvshinks(){
        int kHeading;
        double radians;
        int lengthJump = hashMapSize.get("lengthJump");
        int xKuvshinka = (int) (0.3*hashMapSize.get("kuvshinkaWidth"));
        int yKuvshinka = (hashMapSize.get("mCanvasHeight") - 3*hashMapSize.get("kuvshinkaHeight"));
        newKuvshinka(xKuvshinka, yKuvshinka);
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
        newHippo(xHippo, yHippo);
        kHeading = 35;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        target = new Target(hashMapImg, hashMapSize , xKuvshinka, yKuvshinka);
    }
    private void putToHash() {
        gameObject = new HashMap<>();
        gameObject.put("player", player);
        gameObject.put("splash", splash);
        gameObject.put("kamish", kamish);
        gameObject.put("mGameOver", gameOver);
        gameObject.put("win", win);
        gameObject.put("target", target);
        gameObject.put("nextStage", nextStage);
        gameObject.put("mySound", mySound);
    }
}