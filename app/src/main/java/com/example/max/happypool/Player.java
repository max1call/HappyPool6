package com.example.max.happypool;

import android.graphics.drawable.Drawable;
import java.util.Map;

class Player extends PlayObject implements Constants {

    private int heading, x1, y1, dx, dy;
    private boolean readXY = false;
    private int curentState;
    private int speedFly;
    private double radians;
    private MyThread myThread;
    private Hippo curentHippo;
    private Kuvshinka curentKuvsh;
    private MySound mySound;

    Player(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, int x, int y, int speedFly, MyThread myThread) {
        super(hashMapImg, hashMapSize, x, y);
        this.speedFly=speedFly;
        this.myThread=myThread;
        heading = 0;
        curentState = STATE_ONKUVSHINKA;
    }
    void setState(int curentState) {
        this.curentState = curentState;
        if(curentState == STATE_ONKUVSHINKA){
            myThread.setState(Constants.STATE_ONKUVSHINKA);
            curentImg = hashMapImg.get("idleFrogImg");
            rect.set(rect.left, rect.top, rect.left + hashMapSize.get("idleFrogWidth"), rect.top + hashMapSize.get("idleFrogHeight"));
            rect.offset(curentKuvsh.getRect().centerX() - rect.centerX(), curentKuvsh.getRect().centerY() - rect.centerY());
        }
        else if (curentState == STATE_MOVE) {
            mySound.goSoundJump();
            myThread.setState(Constants.STATE_MOVE);
            curentImg = hashMapImg.get("flyFrogImg");
            rect.set(rect.left, rect.top, rect.left + hashMapSize.get("flyFrogWidth"), rect.top + hashMapSize.get("flyFrogHeight"));
        }
        else if (curentState == STATE_ONHIPPO) {
            curentImg = hashMapImg.get("idleFrogImg");
            rect.set(rect.left, rect.top, rect.left + hashMapSize.get("idleFrogWidth"), rect.top + hashMapSize.get("idleFrogHeight"));
        }
    }
    int getHeading() {
        return heading;
    }
    void setTouchDown(float x, float y){
        if (rect.contains((int)x, (int)y) && (curentState == STATE_ONKUVSHINKA || curentState == STATE_ONHIPPO)) {
            readXY = true;
        }
    }
    void setHeading(float x, float y){
        if (readXY) {
            x1 = rect.centerX();
            y1 = rect.centerY();
            radians = Math.acos((y-y1)/Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1)));
            if (x>x1)radians=(-1)*radians;
            heading = (int) (radians*360/(2 * Math.PI));
        }
    }
    void setTouchUp(){
        if (readXY) {
            x1 = rect.centerX();
            y1 = rect.centerY();
            dy = (int) (speedFly*Math.cos(radians));
            dx = (int) (speedFly*Math.sin(radians));
            setState(STATE_MOVE);
            readXY = false;
        }
    }
    void updatePhysics() {
        if (curentState == STATE_MOVE) {
            rect.offset(dx, -dy);
            myThread.checkLocation(rect, x1, y1);
        }
        else if (curentState == STATE_ONHIPPO) {
            rect.offset(curentHippo.getRect().centerX() - rect.centerX(),
                        curentHippo.getRect().centerY() - rect.centerY());
        }
    }
    void setCurentHippo(Hippo h){
            curentHippo = h;
    }
    void setCurentKufsh(Kuvshinka k){
            curentKuvsh = k;
    }
    void setSound(MySound sound){
        mySound = sound;
        mySound.goSoundMusic();
    }
}