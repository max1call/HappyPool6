package com.example.max.happypool;

import android.graphics.drawable.Drawable;
import java.util.Map;

public class NextStage extends PlayObject implements Constants {

    int bgWidth, bgHeight;
    MyThread myThread;

    NextStage(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, MyThread myThread, int x, int y) {
        super(hashMapImg, hashMapSize, x, y);
        this.myThread = myThread;
        curentImg = hashMapImg.get("stage1Img");
        bgWidth = hashMapSize.get("mCanvasWidth");
        bgHeight = hashMapSize.get("mCanvasHeight");
        rect.set(0, 0, bgWidth, bgHeight);
    }
    public void setStage(int stage) {
        switch (stage){
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
}

