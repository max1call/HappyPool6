package com.example.max.happypool;

        import android.graphics.drawable.Drawable;
        import java.util.Map;

public class Kuvshinka extends PlayObject{

    Kuvshinka(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, int x, int y){
        super(hashMapImg, hashMapSize, x, y);
        curentImg = hashMapImg.get("kuvshinkaImg");
        rect.set(x, y, x + hashMapSize.get("kuvshinkaWidth"), y + hashMapSize.get("kuvshinkaHeight"));
    }
}


