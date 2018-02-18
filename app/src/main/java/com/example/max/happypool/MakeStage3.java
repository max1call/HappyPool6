
package com.example.max.happypool;

import android.graphics.drawable.Drawable;
import java.util.Map;

class MakeStage3 extends MakeStage {

    MakeStage3(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread) {
        super(hashMapImg, hashMapSize, myThread);
        getArray();
        makeKuvshinks();
    }
    private void makeKuvshinks(){
        int kHeading;
        double radians;
        int lengthJump = hashMapSize.get("lengthJump");
        int xKuvshinka = (int) (0.3*hashMapSize.get("kuvshinkaWidth"));//1
        int yKuvshinka =  hashMapSize.get("mCanvasHeight")/2;
        setPositiovKuvsh(0, xKuvshinka, yKuvshinka);
        kHeading = 120;//2
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        xHippo = xKuvshinka;
        yHippo = yKuvshinka;
        setPositiovHippo(0, xHippo, yHippo);
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
        setPositiovHippo(1, xHippo, yHippo);
        kHeading = 140;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        target.setPoisition(xKuvshinka, yKuvshinka);
    }
}