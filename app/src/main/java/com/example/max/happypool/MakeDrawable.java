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
    private Drawable kuvshinkaImg, idleFrogImg, flyFrogImg, bulkImg, hippoImg, kamishImg, heartImg;
    private Drawable game_overImg, winImg, targetImg, congratImg, stage1Img, stage2Img, stage3Img;
    private int mCanvasWidth, mCanvasHeight, bgWidth, bgHeight, heartWidth, heartHeight;
    private int idleFrogWidth, idleFrogHeight, flyFrogWidth, flyFrogHeight, bulkWidth, bulkHeight, kuvshinkaWidth;
    private int kuvshinkaHeight, hippoWidth, hippoHeight, kamishWidth, kamishHeight, targetWidth, targetHeight;
    private int congratWidth, congratHeight, stage1Width, stage1Height, stage2Width, stage2Height, stage3Width, stage3Height;
    private float coefficientScale;
    private int lengthJump;
    private Context context;
    private boolean canResize;
    Map<String, Drawable> hashMapImg;
    Map<String, Integer> hashMapSize;

    MakeDrawable(Context context, MyThread myThread){
        this.context = context;
        canResize = true;
        initImg();
        defineSizeImg();
        calcullateScale();
        resizeImg();
        putToHash();
        myThread.setHash(hashMapImg, hashMapSize, backgroundImg);
    }

    private void initImg() {
        backgroundImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.background3);
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
        congratImg = context.getResources().getDrawable(R.drawable.congrat);
        stage1Img = context.getResources().getDrawable(R.drawable.stage1);
        stage2Img = context.getResources().getDrawable(R.drawable.stage2);
        stage3Img = context.getResources().getDrawable(R.drawable.stage3);
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
        congratWidth = congratImg.getIntrinsicWidth();
        congratHeight = congratImg.getIntrinsicHeight();
        stage1Width = stage1Img.getIntrinsicWidth();
        stage1Height = stage1Img.getIntrinsicHeight();
        stage2Width = stage2Img.getIntrinsicWidth();
        stage2Height = stage2Img.getIntrinsicHeight();
        stage3Width = stage3Img.getIntrinsicWidth();
        stage3Height = stage3Img.getIntrinsicHeight();
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
            backgroundImg = Bitmap.createScaledBitmap(backgroundImg, mCanvasWidth, mCanvasHeight, true);
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

            congratWidth = (int) (congratWidth/coefficientScale);
            congratHeight = (int) (congratHeight/coefficientScale);
            stage1Width = (int) (stage1Width/coefficientScale);
            stage1Height = (int) (stage1Height/coefficientScale);
            stage2Width = (int) (stage2Width/coefficientScale);
            stage2Height = (int) (stage2Height/coefficientScale);
            stage3Width = (int) (stage3Width/coefficientScale);
            stage3Height = (int) (stage3Height/coefficientScale);
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

        hashMapImg.put("congratImg", congratImg);
        hashMapImg.put("stage1Img", stage1Img);
        hashMapImg.put("stage2Img", stage2Img);
        hashMapImg.put("stage3Img", stage3Img);

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

        hashMapSize.put("congratWidth",congratWidth);
        hashMapSize.put("congratHeight",congratHeight);
        hashMapSize.put("stage1Width",stage1Width);
        hashMapSize.put("stage1Height",stage1Height);
        hashMapSize.put("stage2Width",stage2Width);
        hashMapSize.put("stage2Height",stage2Height);
        hashMapSize.put("stage3Width",stage3Width);
        hashMapSize.put("stage3Height",stage3Height);
    }
//    protected Map<String, Integer> getHashMapSize(){
//        return hashMapSize;
//    }
//    protected Map<String, Drawable> getHashMapImg(){
//        return hashMapImg;
//    }
}
