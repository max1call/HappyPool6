package com.example.max.happypool;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import java.util.Map;
import static android.content.Context.MODE_PRIVATE;
public class MyThread extends Thread implements Constants {

    private boolean running = false;
    private SurfaceHolder surfaceHolder;
    private Context context;
    private  int curentState;
    private ArrayList<Kuvshinka> arrayKuvshinka;
    private ArrayList<Heart> arrayHeart;
    private ArrayList<Hippo> arrayHippo;
    private Player player;
    private Kuvshinka lastKuvshinka;
    private Splash splash;
    private Kamish kamish;
    private GameOver mGameOver;
    private Target target;
    private MainActivity m;
    private int countLive = 3;
    private Win win;
    private boolean canDrawGameOver = false;
    private boolean canDrawNextStage = false;
    private long now;
    private Map<String, Drawable> hashMapImg;
    private Map<String, Integer> hashMapSize;
    private Bitmap backgroundImg;
    private int timeUnderWater;
    private int lengthJump;
    private int stage;
    private NextStage nextStage;
    private MySound mySound;
    private String KEY_Stage = "Stage";
    private SharedPreferences sPref;

    MyThread(int startFrom, MyView myView, SurfaceHolder surfaceHolder, Context context) {
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        new MakeDrawable(context, this);
        new InputOutput(myView, this);
        lengthJump = hashMapSize.get("lengthJump");
        startGame(startFrom);
        setState(STATE_RUNING);
    }
    void ObjFromHash(Map<String, Object> gameObject) {
        player = (Player) gameObject.get("player");
        splash = (Splash) gameObject.get("splash");
        kamish = (Kamish) gameObject.get("kamish");
        mGameOver = (GameOver) gameObject.get("mGameOver");
        target = (Target) gameObject.get("target");
        win = (Win) gameObject.get("win");
        nextStage = (NextStage) gameObject.get("nextStage");
        mySound = (MySound) gameObject.get("mySound");
    }
    void setArray(ArrayList<Kuvshinka> arrayKuvshinka, ArrayList<Heart> arrayHeart, ArrayList<Hippo>arrayHippo){
        this.arrayKuvshinka = arrayKuvshinka;
        lastKuvshinka = arrayKuvshinka.get(0);
        this.arrayHeart = arrayHeart;
        this.arrayHippo = arrayHippo;
    }
    void setHash(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, Bitmap backgroundImg){
        this.hashMapImg = hashMapImg;
        this.hashMapSize = hashMapSize;
        this.backgroundImg = backgroundImg;
    }
    void setRunning(boolean b) {
        running = b;
    }
    void setTimeUnderWater(int timeUnderWater) {
        this.timeUnderWater = timeUnderWater;
    }
    void setState(int state) {
        curentState = state;
        if (curentState == STATE_PAUSE) {
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
            win();
        }
    }
    private void startGame(int startFrom) {
        int saveStage;
        if (startFrom == START) {
            saveStage=STAGE_1;
            stage = saveStage;
        } else {
            sPref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
            saveStage = sPref.getInt(KEY_Stage, STAGE_1);
            stage = saveStage;
        }
        switch (saveStage){
            case STAGE_1:{
                new MakeStage1(hashMapImg, hashMapSize, this);
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
                new MakeStage1(hashMapImg, hashMapSize, this);
                player.setSound(mySound);
                nextStage.setStage(STAGE_2);
                canDrawNextStage = true;
                mTimer(2000, STAGE_1);
                new MakeStage2(hashMapImg, hashMapSize, this);
                lastKuvshinka = arrayKuvshinka.get(0);
                player.setCurentKufsh(lastKuvshinka);
                player.setState(Constants.STATE_ONKUVSHINKA);
                break;
            }
            case STAGE_3:{
                new MakeStage1(hashMapImg, hashMapSize, this);
                player.setSound(mySound);
                nextStage.setStage(STAGE_3);
                canDrawNextStage = true;
                mTimer(2000, STAGE_1);
                new MakeStage2(hashMapImg, hashMapSize, this);
                new MakeStage3(hashMapImg, hashMapSize, this);
                lastKuvshinka = arrayKuvshinka.get(0);
                player.setCurentKufsh(lastKuvshinka);
                player.setState(Constants.STATE_ONKUVSHINKA);
                break;
            }
        }
    }
    private void setStage(int s){
        stage = s;
        if (stage == STAGE_1) {
            new MakeStage1(hashMapImg, hashMapSize, this);
            player.setSound(mySound);
            setRunning(true);
            mySound.goSoundMusic();
            nextStage.setStage(STAGE_1);
            canDrawNextStage = true;
            mTimer(3000, STAGE_1);
            player.setCurentKufsh(arrayKuvshinka.get(0));
            player.setState(Constants.STATE_ONKUVSHINKA);
        }else if (stage == STAGE_2) {
            nextStage.setStage(STAGE_2);
            canDrawNextStage = true;
            mTimer(2000, STAGE_1);
            new MakeStage2(hashMapImg, hashMapSize, this);
            lastKuvshinka = arrayKuvshinka.get(0);
            player.setCurentKufsh(lastKuvshinka);
            player.setState(Constants.STATE_ONKUVSHINKA);
        }else if (stage == STAGE_3) {
            nextStage.setStage(STAGE_3);
            canDrawNextStage = true;
            mTimer(2000, STAGE_1);
            new MakeStage3(hashMapImg, hashMapSize, this);
            lastKuvshinka = arrayKuvshinka.get(0);
            player.setCurentKufsh(lastKuvshinka);
            player.setState(Constants.STATE_ONKUVSHINKA);
        }
    }
    void checkLocation(Rect rectFrog, int x, int y) {
        boolean contains = false;
        int lengthFly = (int) Math.sqrt((rectFrog.centerX()-x)*(rectFrog.centerX()-x)+(rectFrog.centerY()-y)
                *(rectFrog.centerY()-y));
        boolean mustFly = lengthFly < lengthJump/4 ;
        if (mustFly) return;
        boolean jump = lengthFly < lengthJump;
        for(Kuvshinka k : arrayKuvshinka) {
            if (k.getRect().contains(rectFrog.centerX(), rectFrog.centerY())) {
                mySound.goSoundShlep();
                lastKuvshinka = k;
                player.setCurentKufsh(lastKuvshinka);
                player.setState(STATE_ONKUVSHINKA);
                contains = true;
            }
        }
        if (!contains) {
            for (Hippo h : arrayHippo) {
                if (h.getRect().contains(rectFrog.centerX(), rectFrog.centerY())) {
                    mySound.goSoundShlep();
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
        if (!mustFly && !jump && !contains) setState(STATE_BULK);
    }
    private void gameOver() {
        mySound.goSoundFall();
        mGameOver.setCanUpdate(true);
        canDrawGameOver = true;
    }
    private void win() {
        win.setCanUpdate(true);
    }
    void setSurfaceSize() {}
    private void mTimer(final long delay, final int st){
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
                }
            }
        }).start();
    }
    void pause() {
        if (curentState == STATE_RUNING) setState(STATE_PAUSE);
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
        for(Hippo h : arrayHippo) {
            h.updatePhysics();
        }
        mGameOver.updatePhysics();
        win.updatePhysics();

        if (curentState == STATE_BULK) {
            if(System.currentTimeMillis()>now + timeUnderWater) {
                player.setCurentKufsh(lastKuvshinka);
                player.setState(STATE_ONKUVSHINKA);
                setState(STATE_RUNING);
            }
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
    void setTouchDown(float x, float y) {
        if (curentState != STATE_LOSE && curentState != STATE_WIN) {
            player.setTouchDown(x, y);
        } else {
            m.finish();
        }
    }
    void setHeading(float x, float y) {
        player.setHeading(x, y);
    }
    void setTouchUp() {
        player.setTouchUp();
    }
    ArrayList<Kuvshinka> getArrayKuvshinka() {
        return arrayKuvshinka;
    }
    ArrayList<Heart> getArrayHeart() {
        return arrayHeart;
    }
    ArrayList<Hippo> getArrayHippo() {
        return arrayHippo;
    }
    Target getTarget() {
        return target;
    }
    void setMainAktivity(MainActivity mainAktivity) {
        m = mainAktivity;
    }
    public Context getContext(){return context;}
    void saveData(){
        sPref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(KEY_Stage, stage);
        ed.commit();
        mySound.finish();
    }
}

