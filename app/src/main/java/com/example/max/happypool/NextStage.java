package com.example.max.happypool;

import android.graphics.drawable.Drawable;

import java.util.Map;

public class NextStage extends PlayObject implements Constants {

    Boolean canUpdate = false;
    int centerX, centerY, ddx, ddy, bgWidth, bgHeight;
    int dx;
    int dy;
    int speed = 50;
    boolean canDownSize = false;
    MyThread myThread;
//    boolean canChangeSize = true;

    NextStage(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread, int x, int y) {
        super(hashMapImg, hashMapSize, x, y);
        this.myThread = myThread;
        curentImg = hashMapImg.get("stage1Img");
//        rect.set(x, y, x + 1, y + 1);
        bgWidth = hashMapSize.get("mCanvasWidth");
        bgHeight = hashMapSize.get("mCanvasHeight");
        rect.set(0, 0, bgWidth, bgHeight);
//        centerX = bgWidth/2;
//        centerY = bgHeight/2;
//        dx = bgWidth/5;
//        dy = bgHeight/5;
//        ddx = bgWidth/speed;
//        ddy = bgHeight/speed;
    }

//    public void setStage(int stage) {
//        this.stage = stage;
//    }

    public void setStage(int stage) {
        switch (stage){
//            case STAGE_1:{
//                curentImg = hashMapImg.get("stage1Img");
//                break;
//            }
            case STAGE_2:{
                curentImg = hashMapImg.get("stage2Img");
                break;
            }
            case STAGE_3:{
                curentImg = hashMapImg.get("stage3Img");
                break;
            }

        }
    }
//    public void setCanUpdate(Boolean b){
//        canUpdate = b;
//    }

//    public void updatePhysics() {
//        if (canUpdate){
//            if(canDownSize){
//                downSize();
//            } else upSize();
//        }
//    }
//    public void upSize() {
//        if(dx < bgWidth*0.6 && dy < bgHeight*0.6) {
//            rect.set(centerX - dx, centerY - dy, centerX + dx, centerY + dy);
//            dx += ddx;
//            dy += ddy;
//        } else canDownSize = true;
//    }
//    public void downSize() {
//        if(dx > bgWidth*0.5 && dy > bgHeight*0.5) {
//            rect.set(centerX - dx, centerY - dy, centerX + dx, centerY + dy);
//            dx -= ddx/4;
//            dy -= ddy/4;
//        } else{
//            canUpdate = false;
//            myThread.setCanDrawNextStage(false);
//        }
//    }
}

