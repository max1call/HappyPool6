
package com.example.max.happypool;

import android.graphics.drawable.Drawable;
import java.util.Map;

class MakeStage2 extends MakeStage {

    MakeStage2(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread) {
        super(hashMapImg, hashMapSize, myThread);
        getArray();
        makeKuvshinks();
    }
    private void makeKuvshinks(){
        int kHeading;
        double radians;
        int lengthJump = hashMapSize.get("lengthJump");
        int xKuvshinka = (int) (0.3*hashMapSize.get("kuvshinkaWidth"));
        int yKuvshinka =  3*hashMapSize.get("kuvshinkaHeight");
        setPositiovKuvsh(0, xKuvshinka, yKuvshinka);
        kHeading = 165;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        setPositiovKuvsh(1, xKuvshinka, yKuvshinka);
        arrayKuvshinka.remove(2);
        kHeading = 60;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        xHippo = xKuvshinka;
        yHippo = yKuvshinka;
        setPositiovHippo(0, xHippo, yHippo);
        kHeading = 110;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        xHippo = xKuvshinka;
        yHippo = yKuvshinka;
        newHippo(xHippo, yHippo);
        kHeading = 60;
        radians = 2 * Math.PI * kHeading / 360;
        xKuvshinka += (int) (lengthJump*Math.sin(radians));
        yKuvshinka -= (int) (lengthJump*Math.cos(radians));
        target.setPoisition(xKuvshinka, yKuvshinka);
    }
}