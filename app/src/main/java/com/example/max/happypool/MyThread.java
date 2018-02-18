package com.example.max.happypool;// 3 стажа, сохранение, возврат после победы и поражения, звук.

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class MyThread extends Thread implements Constants {

    String TAG="Target";
    boolean running = false;
    private SurfaceHolder surfaceHolder;
    private Context context;
    protected int curentState;
    private ArrayList<Kuvshinka> arrayKuvshinka;
    private ArrayList<Heart> arrayHeart;
    private ArrayList<Hippo> arrayHippo;
    Player player;
    Kuvshinka kuvshinka, lastKuvshinka;
    Hippo hippo;
    Splash splash;
    Heart heart;
    Kamish kamish;
    GameOver mGameOver;
    Target target;
    Handler handler;
    TextView tv;
    InputOutput inputOutput;
    MyView myView;
    MainActivity m;
    private int countLive = 3;
    private Win win;
    private boolean canDrawGameOver = false;
    private boolean canDrawNextStage = false;
    long now;
    MakeDrawable makeDrawable;
    MakeStage1 makeStage1;
    MakeStage2 makeStage2;
    MakeStage3 makeStage3;
    Rect rectFrog;
    private Map<String, Drawable> hashMapImg;
    private Map<String, Integer> hashMapSize;
    Bitmap backgroundImg;
//    Background background;
    int timeUnderWater;
    int lengthJump;
    int stage;
    NextStage nextStage;
    MySound mySound;
    String KEY_Stage = "Stage";
    SharedPreferences sPref;

    MyThread(int startFrom, MyView myView, SurfaceHolder surfaceHolder, Context context, Handler handler) {
        this.myView = myView;
        this.surfaceHolder = surfaceHolder;
        this.handler = handler;
        this.context = context;
        makeDrawable = new MakeDrawable(context, this);
        inputOutput = new InputOutput(myView, this);
        lengthJump = hashMapSize.get("lengthJump");
        startGame(startFrom);
        setState(STATE_RUNING);
    }
    public void ObjFromHash(Map<String, Object> gameObject) {
        player = (Player) gameObject.get("player");
        splash = (Splash) gameObject.get("splash");
        kamish = (Kamish) gameObject.get("kamish");
        mGameOver = (GameOver) gameObject.get("mGameOver");
        target = (Target) gameObject.get("target");
        win = (Win) gameObject.get("win");
        nextStage = (NextStage) gameObject.get("nextStage");
        mySound = (MySound) gameObject.get("mySound");

    }
    public void setArray(ArrayList<Kuvshinka> arrayKuvshinka, ArrayList<Heart> arrayHeart, ArrayList<Hippo>arrayHippo){
        this.arrayKuvshinka = arrayKuvshinka;
        lastKuvshinka = arrayKuvshinka.get(0);
        this.arrayHeart = arrayHeart;
        this.arrayHippo = arrayHippo;
    }
    public void setHash(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, Bitmap backgroundImg){
        this.hashMapImg = hashMapImg;
        this.hashMapSize = hashMapSize;
        this.backgroundImg = backgroundImg;
    }
    public void setRunning(boolean b) {
        running = b;
    }
    public void setTimeUnderWater(int timeUnderWater) {
        this.timeUnderWater = timeUnderWater;
    }

    public void setState(int state) {/////////////****************************////////////////////////////
//        CharSequence str;
        curentState = state;

        if(curentState == STATE_RUNING) {
//

        }else if (curentState == STATE_MOVE) {

        }else if (curentState == STATE_PAUSE) {
//            str = context.getResources().getText(R.string.mode_pause);
            running = false;

        }else if (curentState == STATE_BULK) {
            mySound.goSoundBulk();
            now = System.currentTimeMillis();
            splash.setLocatoin();
            countLive--;
            if (countLive>0) {
                arrayHeart.remove(countLive);
                player.setState(STATE_BULK);
            } else if (countLive == 0){
                arrayHeart.remove(countLive);
                setState(STATE_LOSE);
            }

        }else if (curentState == STATE_LOSE) {
//
            player.setState(STATE_LOSE);
            for(Hippo h : arrayHippo) {
                h.setState(STATE_PAUSE);
            }
            mTimer(2000, STATE_LOSE);

        }else if (curentState == STATE_WIN) {
            mySound.goSoundWin();
            player.setState(STATE_WIN);
            for(Hippo h : arrayHippo) {
                h.setState(STATE_PAUSE);
            }
//            hippo.setState(STATE_PAUSE);
            win();
        }
    }
    private void startGame(int startFrom) {
        int saveStage;

//        Log.d("abc", "startFrom = "+startFrom);
        if (startFrom == START) {
            saveStage=STAGE_1;
            stage = saveStage;
        } else {
            sPref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
            saveStage = sPref.getInt(KEY_Stage, STAGE_1);
            stage = saveStage;
//            Toast.makeText(context, "Stage loaded", Toast.LENGTH_SHORT).show();

        }
        switch (saveStage){
            case STAGE_1:{
                makeStage1 = new MakeStage1(hashMapImg, hashMapSize, this, handler);
                player.setSound(mySound);
                setRunning(true);
                mySound.goSoundMusic();
                nextStage.setStage(STAGE_1);
                canDrawNextStage = true;
                mTimer(3000, STAGE_1);
                player.setCurentKufsh(arrayKuvshinka.get(0));
                player.setState(Constants.STATE_ONKUVSHINKA);
                break;
            }
            case STAGE_2:{
                makeStage1 = new MakeStage1(hashMapImg, hashMapSize, this, handler);
                player.setSound(mySound);
                nextStage.setStage(STAGE_2);
                canDrawNextStage = true;
                mTimer(2000, STAGE_1);

                makeStage2 = new MakeStage2(hashMapImg, hashMapSize, this, handler);
                lastKuvshinka = arrayKuvshinka.get(0);
                player.setCurentKufsh(lastKuvshinka);
                player.setState(Constants.STATE_ONKUVSHINKA);
                break;
            }
            case STAGE_3:{
                makeStage1 = new MakeStage1(hashMapImg, hashMapSize, this, handler);
                player.setSound(mySound);
                nextStage.setStage(STAGE_3);
                canDrawNextStage = true;
                mTimer(2000, STAGE_1);

                makeStage2 = new MakeStage2(hashMapImg, hashMapSize, this, handler);
                makeStage3 = new MakeStage3(hashMapImg, hashMapSize, this, handler);
                lastKuvshinka = arrayKuvshinka.get(0);
                player.setCurentKufsh(lastKuvshinka);
                player.setState(Constants.STATE_ONKUVSHINKA);
                break;
            }
        }
    }
    protected void setStage(int s){
        stage = s;

        if (stage == STAGE_1) {
            makeStage1 = new MakeStage1(hashMapImg, hashMapSize, this, handler);
            player.setSound(mySound);
            setRunning(true);
            mySound.goSoundMusic();
//            goNextStage();
            nextStage.setStage(STAGE_1);
            canDrawNextStage = true;
            mTimer(3000, STAGE_1);

            player.setCurentKufsh(arrayKuvshinka.get(0));
            player.setState(Constants.STATE_ONKUVSHINKA);

        }else if (stage == STAGE_2) {

            nextStage.setStage(STAGE_2);
            canDrawNextStage = true;
            mTimer(2000, STAGE_1);
            makeStage2 = new MakeStage2(hashMapImg, hashMapSize, this, handler);
//            makeStage2.setArray(arrayKuvshinka, arrayHeart, arrayHippo);
            lastKuvshinka = arrayKuvshinka.get(0);
            player.setCurentKufsh(lastKuvshinka);
            player.setState(Constants.STATE_ONKUVSHINKA);

        }else if (stage == STAGE_3) {
//            goNextStage();
            nextStage.setStage(STAGE_3);
            canDrawNextStage = true;
            mTimer(2000, STAGE_1);
            makeStage3 = new MakeStage3(hashMapImg, hashMapSize, this, handler);
//            makeStage3.setArray(arrayKuvshinka, arrayHeart, arrayHippo);
            lastKuvshinka = arrayKuvshinka.get(0);
            player.setCurentKufsh(lastKuvshinka);
            player.setState(Constants.STATE_ONKUVSHINKA);
        }
    }
    protected void checkLocation(Rect rectFrog, int x, int y) {
        boolean contains = false;
        this.rectFrog = rectFrog;////////////////////////
        int lengthFly = (int) Math.sqrt((rectFrog.centerX()-x)*(rectFrog.centerX()-x)+(rectFrog.centerY()-y)
                *(rectFrog.centerY()-y));
        boolean mustFly = lengthFly < lengthJump/4 ;
        if (mustFly) return;
//        boolean contains = myThread.checkLocation(rect);
        boolean jump = lengthFly < lengthJump;
//        if ((contains) setState(STATE_ONKUVSHINKA);

        for(Kuvshinka k : arrayKuvshinka) {
            if (k.getRect().contains(rectFrog.centerX(), rectFrog.centerY())) {
                mySound.goSoundShlep();
                lastKuvshinka = k;
//                player.setCurentOdj("kuvshinka", k);
//                rectFrog.offset(k.getRect().centerX()-rectFrog.centerX(), k.getRect().centerY()-rectFrog.centerY());
//                player.setPositionFrog(rectFrog);
                player.setCurentKufsh(lastKuvshinka);
                player.setState(STATE_ONKUVSHINKA);
                contains = true;
            }
        }
        if (!contains) {
            for (Hippo h : arrayHippo) {
                if (h.getRect().contains(rectFrog.centerX(), rectFrog.centerY())) {
                    mySound.goSoundShlep();
//            rectFrog.offset(hippo.getRect().centerX()-rectFrog.centerX(), hippo.getRect().centerY()-rectFrog.centerY());
//            player.setPositionFrog(rectFrog);
                    player.setState(STATE_ONHIPPO);
                    player.setCurentHippo(h);
                    contains = true;
                }
            }
        }
        if (target.getRect().contains(rectFrog.centerX(), rectFrog.centerY()) && !contains){
            switch (stage){
                case STAGE_1:{
                    setStage(STAGE_2);
                    break;
                }
                case STAGE_2:{
                    setStage(STAGE_3);
                    break;
                }
                case STAGE_3:{
                    setState(STATE_WIN);
                    break;
                }
            }
            contains = true;
        }
        if (!mustFly && !jump && !contains) setState(STATE_BULK);;
    }

    private void gameOver() {
//        now = System.currentTimeMillis();
        mySound.goSoundFall();
        mGameOver.setCanUpdate(true);
        canDrawGameOver = true;
    }
//    private void goNextStage() {
////        nextStage.setCanUpdate(true);
//
//    }
//    void setCanDrawNextStage(boolean b){canDrawNextStage = b;}

    private void win() {
//        now = System.currentTimeMillis();
        win.setCanUpdate(true);
//        canDrawGameOver = true;
    }
    public void getArray(ArrayList<Kuvshinka> arrayKuvshink, ArrayList<Heart> arrayHear, ArrayList<Hippo>arrayHipp){
        arrayKuvshink = this.arrayKuvshinka;
        arrayHear = this.arrayHeart;
        arrayHipp = this.arrayHippo;
    }
    public void setSurfaceSize(int width, int height) {
        synchronized (surfaceHolder) {

//            kamishImg = Bitmap.createScaledBitmap(kamishImg, width, height, true);
        }
    }


    public void mTimer(final long delay, final int st){
        mySound.stopSoundMusic();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (st) {
                    case STATE_LOSE: {
                        gameOver();
                        break;
                    }
                    case STAGE_1: {
                        mySound.goSoundMusic();
                        canDrawNextStage = false;
                        break;
                    }
//                    case STAGE_2: {
////                        nextStage.setStage(STAGE_2);
//                        canDrawNextStage = false;
//                        break;
//                    }
//                    case STAGE_3: {
////                        nextStage.setStage(STAGE_3);
//                        canDrawNextStage = false;
//                        break;
//                    }
                }
            }
        }).start();
    }

    public void pause() {
        synchronized (surfaceHolder) {
            if (curentState == STATE_RUNING) setState(STATE_PAUSE);
        }
    }

    public void run(){
        Canvas canvas;
        while (running){
            canvas=null;
            try{
                canvas=surfaceHolder.lockCanvas();
                if (canvas!=null){
                    try{
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    updatePhysics();
                    doDraw(canvas);
                }
            }finally {
                if(canvas!=null) surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
    private void updatePhysics() {
        player.updatePhysics();
//        hippo.updatePhysics();
        for(Hippo h : arrayHippo) {
            h.updatePhysics();
        }
        mGameOver.updatePhysics();
        win.updatePhysics();

        if (curentState == STATE_BULK) {
//            Rect rect = new Rect(player.getRect());
            if(System.currentTimeMillis()>now + timeUnderWater) {
//                rect.offset(lastKuvshinka.getRect().centerX() - rect.centerX(), lastKuvshinka.getRect().centerY() - rect.centerY());
                player.setCurentKufsh(lastKuvshinka);
                player.setState(STATE_ONKUVSHINKA);
                setState(STATE_RUNING);
            }
        } else if (stage == STAGE_1 && canDrawNextStage){
//            nextStage.updatePhysics();
        }
    }

    private void doDraw(Canvas canvas) {

        canvas.drawBitmap(backgroundImg, 0, 0, null);

        if (curentState == STATE_BULK || curentState == STATE_LOSE) {
            splash.getCurentImg().setBounds(splash.getRect());
            splash.getCurentImg().draw(canvas);
        }
        for(Kuvshinka k : arrayKuvshinka){
            k.getCurentImg().setBounds(k.getRect());
            k.getCurentImg().draw(canvas);
        }

        target.getCurentImg().setBounds(target.getRect());
        target.getCurentImg().draw(canvas);

        for(Heart h : arrayHeart){
            h.getCurentImg().setBounds(h.getRect());
            h.getCurentImg().draw(canvas);
        }
        for(Hippo h : arrayHippo) {
            h.getCurentImg().setBounds(h.getRect());
            h.getCurentImg().draw(canvas);
        }

        if (curentState != STATE_BULK && curentState != STATE_LOSE && curentState != STATE_WIN) {
            canvas.save();
            Rect rect = new Rect(player.getRect());
            canvas.rotate((float) player.getHeading(), rect.centerX(), rect.centerY());
            player.getCurentImg().setBounds(rect);
            player.getCurentImg().draw(canvas);
//            canvas.drawRect(rect, paint);
            canvas.restore();
        }
        kamish.getCurentImg().setBounds(kamish.getRect());
        kamish.getCurentImg().draw(canvas);

        if (curentState == STATE_WIN){
            win.getCurentImg().setBounds(win.getRect());
            win.getCurentImg().draw(canvas);
        }

        if (canDrawNextStage){
            nextStage.getCurentImg().setBounds(nextStage.getRect());
            nextStage.getCurentImg().draw(canvas);
        }

        if (curentState == STATE_LOSE && canDrawGameOver){
            mGameOver.getCurentImg().setBounds(mGameOver.getRect());
            mGameOver.getCurentImg().draw(canvas);
        }
    }

    public void setTouchDown(float x, float y) {
        if (curentState != STATE_LOSE && curentState != STATE_WIN) {
            player.setTouchDown(x, y);
        } else {
//                Intent intent = new Intent(context, MenuActivity.class);
//                startActivity(intent);
            m.finish();

        }
    }

    public void setHeading(float x, float y) {
        player.setHeading(x, y);
    }

    public void setTouchUp(float x, float y) {
        player.setTouchUp(x, y);
    }

    public ArrayList<Kuvshinka> getArrayKuvshinka() {
        return arrayKuvshinka;
    }

    public ArrayList<Heart> getArrayHeart() {
        return arrayHeart;
    }

    public ArrayList<Hippo> getArrayHippo() {
        return arrayHippo;
    }

    public Target getTarget() {
        return target;
    }

    public void setMainAktivity(MainActivity mainAktivity) {
        m = mainAktivity;
    }
    public Context getContext(){return context;}

    void saveData(){
        sPref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(KEY_Stage, stage);
        ed.commit();
//        Toast.makeText(context, "Stage saved", Toast.LENGTH_SHORT).show();
        mySound.finish();
    }
//    public Bundle saveState(Bundle map) {
//
//        synchronized (surfaceHolder) {
//            if (map != null) {
//                map.putInt(KEY_Stage, Integer.valueOf(stage));
//                map.putDouble(KEY_X, Double.valueOf(mX));
//                map.putDouble(KEY_Y, Double.valueOf(mY));
//                map.putDouble(KEY_DX, Double.valueOf(mDX));
//                map.putDouble(KEY_DY, Double.valueOf(mDY));
//                map.putDouble(KEY_HEADING, Double.valueOf(mHeading));
//                map.putInt(KEY_LANDER_WIDTH, Integer.valueOf(mLanderWidth));
//                map.putInt(KEY_LANDER_HEIGHT, Integer.valueOf(mLanderHeight));
//                map.putInt(KEY_GOAL_X, Integer.valueOf(mGoalX));
//                map.putInt(KEY_GOAL_SPEED, Integer.valueOf(mGoalSpeed));
//                map.putInt(KEY_GOAL_ANGLE, Integer.valueOf(mGoalAngle));
//                map.putInt(KEY_GOAL_WIDTH, Integer.valueOf(mGoalWidth));
//                map.putInt(KEY_WINS, Integer.valueOf(mWinsInARow));
//                map.putDouble(KEY_FUEL, Double.valueOf(mFuel));
//            }
//        }
//        return map;
//    }
//    public synchronized void restoreState(Bundle savedState) {
//        synchronized (surfaceHolder) {
////            setState(STATE_PAUSE);
//            int saveStage = savedState.getInt(KEY_Stage);
//
////            mRotating = 0;
////            mEngineFiring = false;
////
////            mDifficulty = savedState.getInt(KEY_DIFFICULTY);
////            mX = savedState.getDouble(KEY_X);
////            mY = savedState.getDouble(KEY_Y);
////            mDX = savedState.getDouble(KEY_DX);
////            mDY = savedState.getDouble(KEY_DY);
////            mHeading = savedState.getDouble(KEY_HEADING);
////
////            mLanderWidth = savedState.getInt(KEY_LANDER_WIDTH);
////            mLanderHeight = savedState.getInt(KEY_LANDER_HEIGHT);
////            mGoalX = savedState.getInt(KEY_GOAL_X);
////            mGoalSpeed = savedState.getInt(KEY_GOAL_SPEED);
////            mGoalAngle = savedState.getInt(KEY_GOAL_ANGLE);
////            mGoalWidth = savedState.getInt(KEY_GOAL_WIDTH);
////            mWinsInARow = savedState.getInt(KEY_WINS);
////            mFuel = savedState.getDouble(KEY_FUEL);
//        }
//    }

}

