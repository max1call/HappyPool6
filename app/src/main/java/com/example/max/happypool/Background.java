package com.example.max.happypool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.util.Map;


public class Background {

    Bitmap backgroundImg;

    Background(Map<String, Integer> hashMapSize, Context context) {

        backgroundImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.background3);
        backgroundImg = Bitmap.createScaledBitmap(backgroundImg, hashMapSize.get("mCanvasWidth"), hashMapSize.get("mCanvasHeight"), true);
    }
    public Bitmap getBgImg(){
        return backgroundImg;
    }
}
