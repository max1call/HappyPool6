package com.example.max.happypool;

import android.graphics.drawable.Drawable;
import android.os.Handler;

import java.util.Map;

class GameOver extends PlayObject implements Constants {

    private Boolean canUpdate = false;
    private boolean canDownSize = false;
    private int centerX, centerY, dx, dy, ddx, ddy, bgWidth, bgHeight;
    private int speed = 30;
    private Handler handler;

    GameOver(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, int x, int y) {
        super(hashMapImg, hashMapSize, x, y);
        curentImg = hashMapImg.get("game_overImg");
        this.handler = handler;
        rect.set(x, y, x + 1, y + 1);
        bgWidth = hashMapSize.get("mCanvasWidth");
        bgHeight = hashMapSize.get("mCanvasHeight");
        centerX = bgWidth/2;
        centerY = bgHeight/2;
        dx = bgWidth/5;
        dy = bgHeight/5;
        ddx = bgWidth/speed;
        ddy = bgHeight/speed;
    }
    void setCanUpdate(Boolean b){
        canUpdate = b;
    }
    void updatePhysics() {
        if (canUpdate){
            if(canDownSize){
                downSize();
            } else upSize();
        }
    }
    private void upSize() {
        if(dx < bgWidth*0.6 && dy < bgHeight*0.6) {
            rect.set(centerX - dx, centerY - dy, centerX + dx, centerY + dy);
            dx += ddx;
            dy += ddy;
        } else canDownSize = true;
    }
    private void downSize() {
        if(dx > bgWidth*0.5 && dy > bgHeight*0.5) {
            rect.set(centerX - dx, centerY - dy, centerX + dx, centerY + dy);
            dx -= ddx/2;
            dy -= ddy/2;
        } else canUpdate = false;
    }
}
