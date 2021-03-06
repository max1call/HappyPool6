package com.example.max.happypool;

        import android.graphics.drawable.Drawable;
        import java.util.Map;

public class Hippo extends PlayObject implements Constants {

    private final int speedHippo;
    private int speed, maxCanvasHeight, minCanvasHeight ;

    Hippo(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, int speedHippo, int x, int y){
        super(hashMapImg, hashMapSize, x, y);
        curentImg = hashMapImg.get("hippoImg");
        this.speedHippo = speedHippo;
        rect.set(x, y, x + hashMapSize.get("hippoWidth"), y + hashMapSize.get("hippoHeight"));
        maxCanvasHeight = (int) (hashMapSize.get("mCanvasHeight")*0.9);
        minCanvasHeight = (int) (hashMapSize.get("mCanvasHeight")*0.07);
        setState(STATE_MOVE);
    }
    public void updatePhysics() {
        rect.offset(0, speed);
        if (rect.bottom > maxCanvasHeight || rect.top < minCanvasHeight) speed=(-1)*speed;
    }
    public void setState(int curentState) {
        if (curentState == STATE_MOVE) {
            speed = speedHippo;
        } else if (curentState == STATE_PAUSE) {
            speed = 0;
        }
    }
}

