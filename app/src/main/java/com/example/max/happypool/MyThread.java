package com.example.max.happypool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.TextView;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Map;

public class MyThread extends Thread implements Constants {

    String TAG="Target";
    boolean running = false;
    private SurfaceHolder surfaceHolder;
    private Context context;
    protected int curentState;
    private ArrayList<Kuvshinka> arrayKuvshinka;
    private ArrayList<Heart> arrayHeart;
    Player player;
    Kuvshinka kuvshinka, lastKuvshinka;
    Hippo hippo;
    Splash splash;
    Heart heart;
    Kamish kamish;
    GameOver gameOver;
    Target target;
    Handler handler;
    TextView tv;
    InputOutput inputOutput;
    MyView myView;
    MainActivity m;
    private int countLive = 3;
//    private Kamish kamish;
//    private Target target;
//    private GameOver game_over;
    private Win win;
    private boolean canDrawGameOver = false;
    long now;
    MakeDrawable makeDrawable;
    MakeStage1 makeStage1;

    Rect rectFrog;
    private Map<String, Drawable> hashMapImg;
    private Map<String, Integer> hashMapSize;
    Bitmap backgroundImg;
    Background background;
    int timeUnderWater;

    public MyThread(MyView myView, SurfaceHolder surfaceHolder, Context context, Handler handler) {
//        Log.i(TAG, "Begin Constructor MyThread");
        this.myView = myView;
        this.surfaceHolder = surfaceHolder;
        this.handler = handler;
        this.context = context;

        makeDrawable = new MakeDrawable(context);

        hashMapImg = makeDrawable.getHashMapImg();
        hashMapSize = makeDrawable.getHashMapSize();
        makeStage1 = new MakeStage1(hashMapImg, hashMapSize, this);
        lastKuvshinka = arrayKuvshinka.get(0);

        inputOutput = new InputOutput(myView, player, this);
        background = new Background(hashMapSize, context);
        backgroundImg = background.getBgImg();
        setState(STATE_RUNING);
    }

    public void ObjFromHash(Map<String, Object> gameObject) {
        player = (Player) gameObject.get("player");
        hippo = (Hippo) gameObject.get("hippo");
        splash = (Splash) gameObject.get("splash");
        kamish = (Kamish) gameObject.get("kamish");
        gameOver = (GameOver) gameObject.get("gameOver");
        target = (Target) gameObject.get("target");
        win = (Win) gameObject.get("win");
    }

    public void setArray(ArrayList<Kuvshinka> arrayKuvshinka, ArrayList<Heart> arrayHeart){
        this.arrayKuvshinka = arrayKuvshinka;
        this.arrayHeart = arrayHeart;
    }
    public void setRunning(boolean b) {
        running = b;
    }


    public void setTimeUnderWater(int timeUnderWater) {
        this.timeUnderWater = timeUnderWater;
    }

    public void setState(int state) {/////////////****************************////////////////////////////
        CharSequence str;
        curentState = state;
//        Log.i(TAG, "Begin setState");

        if (curentState == STATE_RUNING) {
//            player.setState(STATE_ONKUVSHINKA);
//            hippo.setState(STATE_MOVE);
//            paint.setColor(Color.BLACK);
            running = true;

        } else if (curentState == STATE_PAUSE) {
            str = context.getResources().getText(R.string.mode_pause);
            running = false;

        } else if (curentState == STATE_BULK) {
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

        } else if (curentState == STATE_LOSE) {
            player.setState(STATE_LOSE);
            hippo.setState(STATE_PAUSE);
            gameOver();

        } else if (curentState == STATE_WIN) {
            player.setState(STATE_WIN);
            hippo.setState(STATE_PAUSE);
            win();
        }
    }
    protected void checkLocation(Rect rectFrog) {
        boolean contains = false;
        this.rectFrog = rectFrog;
        for(Kuvshinka k : arrayKuvshinka) {
            if (k.getRect().contains(rectFrog.centerX(), rectFrog.centerY())) {
                lastKuvshinka = k;
//                rectFrog.offset(k.getRect().centerX()-rectFrog.centerX(), k.getRect().centerY()-rectFrog.centerY());
//                player.setPositionFrog(rectFrog);
                player.setState(STATE_ONKUVSHINKA);
                contains = true;
            }
        }
        if (hippo.getRect().contains(rectFrog.centerX(), rectFrog.centerY())){
//            rectFrog.offset(hippo.getRect().centerX()-rectFrog.centerX(), hippo.getRect().centerY()-rectFrog.centerY());
//            player.setPositionFrog(rectFrog);
            player.setState(STATE_ONHIPPO);
            contains = true;
        }
        else if (target.getRect().contains(rectFrog.centerX(), rectFrog.centerY())){
            setState(STATE_WIN);
            contains = true;
        }
        if (!contains) setState(STATE_BULK);
    }

    private void gameOver() {
        now = System.currentTimeMillis();
        gameOver.setCanUpdate(true);
//        canDrawGameOver = true;
    }

    private void win() {
//        now = System.currentTimeMillis();
        win.setCanUpdate(true);
//        canDrawGameOver = true;
    }

    public void setSurfaceSize(int width, int height) {
        synchronized (surfaceHolder) {

//            kamishImg = Bitmap.createScaledBitmap(kamishImg, width, height, true);
        }
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
        hippo.updatePhysics();
        gameOver.updatePhysics();
        win.updatePhysics();

        if (curentState == STATE_BULK) {
            Rect rect = new Rect(player.getRect());
            if(System.currentTimeMillis()>now + timeUnderWater) {
                rect.offset(lastKuvshinka.getRect().centerX() - rect.centerX(), lastKuvshinka.getRect().centerY() - rect.centerY());
                player.setPositionFrog(rect);
                player.setState(STATE_ONKUVSHINKA);
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
        //TODO background into obj

        hippo.getCurentImg().setBounds(hippo.getRect());
        hippo.getCurentImg().draw(canvas);

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

        if (curentState == STATE_LOSE && (System.currentTimeMillis()>now + timeUnderWater)){
            gameOver.getCurentImg().setBounds(gameOver.getRect());
            gameOver.getCurentImg().draw(canvas);
        }
    }



//    public boolean doKeyUp(int keyCode, KeyEvent msg) {
//        return true;
//    }
//
//    public boolean doKeyDown(int keyCode, KeyEvent msg) {
//        return true;
//    }


}

