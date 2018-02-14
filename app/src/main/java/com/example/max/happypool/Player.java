package com.example.max.happypool;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

public class Player extends PlayObject implements Constants {

    private int heading, x1, y1, dx, dy, lengthJump;
    private boolean readXY = false;
    private int curentState;
    private int speedFly;
    private double radians;
    MyThread myThread;
    ArrayList<Hippo> arrayHippo;
    Hippo curentHippo;
    Kuvshinka curentKuvsh;
//    Object object;

    Player(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, int x, int y, int speedFly, MyThread myThread, ArrayList<Hippo> hippo) {
        super(hashMapImg, hashMapSize, x, y);
        this.speedFly=speedFly;
        this.myThread=myThread;
        this.arrayHippo=arrayHippo;
//        curentHippo = arrayHippo.get(0);
        lengthJump = hashMapSize.get("lengthJump");
        heading = 0;
//        rect.set(x, y, x + hashMapSize.get("idleFrogWidth"), y + hashMapSize.get("idleFrogHeight"));
//        curentImg = hashMapImg.get("idleFrogImg");
        curentState = STATE_ONKUVSHINKA;
//        setState(STATE_ONKUVSHINKA);
    }
    public void setState(int curentState) {/////////////////******************////////////////////////////
        this.curentState = curentState;
        if(curentState == STATE_ONKUVSHINKA){
//            myThread.setState(STATE_RUNING);
            curentImg = hashMapImg.get("idleFrogImg");
            rect.set(rect.left, rect.top, rect.left + hashMapSize.get("idleFrogWidth"), rect.top + hashMapSize.get("idleFrogHeight"));
            rect.offset(curentKuvsh.getRect().centerX() - rect.centerX(), curentKuvsh.getRect().centerY() - rect.centerY());
        }
        else if (curentState == STATE_MOVE) {
            curentImg = curentImg = hashMapImg.get("flyFrogImg");
            rect.set(rect.left, rect.top, rect.left + hashMapSize.get("flyFrogWidth"), rect.top + hashMapSize.get("flyFrogHeight"));
        }
        else if (curentState == STATE_ONHIPPO) {
            curentImg = hashMapImg.get("idleFrogImg");
            rect.set(rect.left, rect.top, rect.left + hashMapSize.get("idleFrogWidth"), rect.top + hashMapSize.get("idleFrogHeight"));
        }
        else if (curentState == STATE_BULK) {
//            myThread.setState(STATE_BULK);
        }
        else if (curentState == STATE_LOSE) {

        }else if (curentState == STATE_WIN){
            //win
        }
    }
    public int getHeading() {
        return heading;
    }

    public void setTouchDown(float x, float y){
        if (rect.contains((int)x, (int)y) && (curentState == STATE_ONKUVSHINKA || curentState == STATE_ONHIPPO)) {
            readXY = true;
            x1 = rect.centerX();
            y1 = rect.centerY();

            Log.i("fly", "xStartJump = "+x1+ "; yStartJump = "+y1);
        }
    }
    public void setHeading(float x, float y){
        if (readXY) {
            radians = Math.acos((y-y1)/Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1)));
            if (x>x1)radians=(-1)*radians;
            heading = (int) (radians*360/(2 * Math.PI));
            Log.i("fly", "heading = "+heading);
        }
    }
    public void setTouchUp(float x, float y){
        if (readXY) {
            x1 = rect.centerX();
            y1 = rect.centerY();
            dy = (int) (speedFly*Math.cos(radians));
            dx = (int) (speedFly*Math.sin(radians));
            setState(STATE_MOVE);
            readXY = false;
        }
    }
    public void updatePhysics() {
        if (curentState == STATE_MOVE) {
            rect.offset(dx, -dy);
            myThread.checkLocation(rect, x1, y1);
        }
        else if (curentState == STATE_ONHIPPO) {

                rect.offset(curentHippo.getRect().centerX() - rect.centerX(), curentHippo.getRect().centerY() - rect.centerY());

        }

        else if (curentState == STATE_LOSE) {

        }
    }
//    protected void setPositionFrog(Rect r){
//        rect.set(r);
//    }
    protected void setCurentHippo(Hippo h){
            curentHippo = h;
    }
    protected void setCurentKufsh(Kuvshinka k){
            curentKuvsh = k;//TODO use!
    }


}