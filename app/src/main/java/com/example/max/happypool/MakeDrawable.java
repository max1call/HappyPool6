package com.example.max.happypool;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.Map;

class MakeDrawable {
    private Bitmap backgroundImg;
    private Drawable kuvshinkaImg, idleFrogImg, flyFrogImg, bulkImg, hippoImg, kamishImg, heartImg, game_overImg, winImg, targetImg;
    private int mCanvasWidth, mCanvasHeight, bgWidth, bgHeight, heartWidth, heartHeight;
    private int idleFrogWidth, idleFrogHeight, flyFrogWidth, flyFrogHeight, bulkWidth,
            bulkHeight, kuvshinkaWidth, kuvshinkaHeight, hippoWidth, hippoHeight, kamishWidth, kamishHeight, targetWidth, targetHeight;
    private float coefficientScale;
    private int lengthJump;
    private Context context;
    private boolean canResize;
    Map<String, Drawable> hashMapImg;
    Map<String, Integer> hashMapSize;

    MakeDrawable(Context context){
        this.context = context;
        canResize = true;
        initImg();
        defineSizeImg();
        calcullateScale();
        resizeImg();
        putToHash();
    }

    private void initImg() {
        kamishImg = context.getResources().getDrawable(R.drawable.kamish);
        game_overImg = context.getResources().getDrawable(R.drawable.game_over);
        winImg = context.getResources().getDrawable(R.drawable.win);
        kuvshinkaImg = context.getResources().getDrawable(R.drawable.kuvshinka);
        idleFrogImg = context.getResources().getDrawable(R.drawable.idle_frog);
        flyFrogImg = context.getResources().getDrawable(R.drawable.fly_frog);
        bulkImg = context.getResources().getDrawable(R.drawable.bulk);
        hippoImg = context.getResources().getDrawable(R.drawable.begemot);
        heartImg = context.getResources().getDrawable(R.drawable.heart);
        targetImg = context.getResources().getDrawable(R.drawable.target_frog);
    }
    private void defineSizeImg() {
        bgWidth=backgroundImg.getWidth();
        bgHeight= backgroundImg.getHeight();
        kamishWidth = kamishImg.getIntrinsicWidth();
        kamishHeight = kamishImg.getIntrinsicHeight();
        idleFrogWidth = idleFrogImg.getIntrinsicWidth();
        idleFrogHeight = idleFrogImg.getIntrinsicHeight();
        flyFrogWidth = flyFrogImg.getIntrinsicWidth();
        flyFrogHeight = flyFrogImg.getIntrinsicHeight();
        bulkWidth = bulkImg.getIntrinsicWidth();
        bulkHeight = bulkImg.getIntrinsicHeight();
        kuvshinkaWidth = kuvshinkaImg.getIntrinsicWidth();//
        kuvshinkaHeight = kuvshinkaImg.getIntrinsicHeight();
        hippoWidth = hippoImg.getIntrinsicWidth();
        hippoHeight = hippoImg.getIntrinsicHeight();
        heartWidth = heartImg.getIntrinsicWidth();
        heartHeight = heartImg.getIntrinsicHeight();
        targetWidth = targetImg.getIntrinsicWidth();
        targetHeight = targetImg.getIntrinsicHeight();
    }
    private void calcullateScale() {
        Rect rectDisplay = new Rect();
        WindowManager w = ((Activity) context).getWindowManager();
        Display d = w.getDefaultDisplay();
        d.getRectSize(rectDisplay);
        mCanvasWidth = rectDisplay.width();
        mCanvasHeight = rectDisplay.height();
//        Log.w(TAG, "!!!!!!!!!!!!! mCanvasWidth= "+mCanvasWidth+"; mCanvasHeight= "+mCanvasHeight);
        lengthJump = (int) (mCanvasHeight/2.5);
        float coefficientX = (float) bgWidth/mCanvasWidth;
        float coefficientY = (float) bgHeight/mCanvasHeight;
        if(coefficientX > coefficientY){
            coefficientScale = coefficientY;
        } else coefficientScale = coefficientX;
    }
    private void resizeImg(){
        if (canResize) {
            kamishWidth = (int) (kamishWidth/coefficientScale);
            kamishHeight = (int) (kamishHeight/coefficientScale);
            idleFrogWidth = (int) (idleFrogWidth/coefficientScale);
            idleFrogHeight = (int) (idleFrogHeight/coefficientScale);
            flyFrogWidth = (int) (flyFrogWidth/coefficientScale);
            flyFrogHeight = (int) (flyFrogHeight/coefficientScale);
            bulkWidth = (int) (bulkWidth/coefficientScale);
            bulkHeight = (int) (bulkHeight/coefficientScale);
            kuvshinkaWidth = (int) (kuvshinkaWidth/coefficientScale);
            kuvshinkaHeight = (int) (kuvshinkaHeight/coefficientScale);
            hippoWidth = (int) (hippoWidth/coefficientScale);
            hippoHeight = (int) (hippoHeight/coefficientScale);

            heartWidth = (int) (heartWidth/coefficientScale);
            heartHeight = (int) (heartHeight/coefficientScale);
            targetWidth = (int) (targetWidth/coefficientScale);
            targetHeight = (int) (targetHeight/coefficientScale);
            canResize = false;
        }
    }
    private void putToHash() {
        hashMapImg = new HashMap<String, Drawable>();
        hashMapImg.put("game_overImg", game_overImg);
        hashMapImg.put("winImg", winImg);
        hashMapImg.put("kamishImg", kamishImg);
        hashMapImg.put("kuvshinkaImg", kuvshinkaImg);
        hashMapImg.put("idleFrogImg", idleFrogImg);
        hashMapImg.put("flyFrogImg", flyFrogImg);
        hashMapImg.put("bulkImg", bulkImg);
        hashMapImg.put("hippoImg", hippoImg);
        hashMapImg.put("heartImg", heartImg);
        hashMapImg.put("targetImg", targetImg);

        hashMapSize = new HashMap<String, Integer>();
        hashMapSize.put("kamishWidth",kamishWidth);
        hashMapSize.put("kamishHeight",kamishHeight);
        hashMapSize.put("idleFrogWidth",idleFrogWidth);
        hashMapSize.put("idleFrogHeight",idleFrogHeight);
        hashMapSize.put("flyFrogWidth",flyFrogWidth);
        hashMapSize.put("flyFrogHeight",flyFrogHeight);
        hashMapSize.put("bulkWidth",bulkWidth);
        hashMapSize.put("bulkHeight",bulkHeight);
        hashMapSize.put("kuvshinkaWidth",kuvshinkaWidth);
        hashMapSize.put("kuvshinkaHeight",kuvshinkaHeight);
        hashMapSize.put("hippoWidth",hippoWidth);
        hashMapSize.put("hippoHeight",hippoHeight);
        hashMapSize.put("lengthJump",lengthJump);
        hashMapSize.put("mCanvasWidth",mCanvasWidth);
        hashMapSize.put("mCanvasHeight",mCanvasHeight);
        hashMapSize.put("heartWidth",heartWidth);
        hashMapSize.put("heartHeight",heartHeight);
        hashMapSize.put("targetWidth",targetWidth);
        hashMapSize.put("targetHeight",targetHeight);
    }
    protected Map<String, Integer> getHashMapSize(){
        return hashMapSize;
    }
    protected Map<String, Drawable> getHashMapImg(){
        return hashMapImg;
    }
}
