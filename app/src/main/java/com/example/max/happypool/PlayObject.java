package com.example.max.happypool;

        import android.graphics.Rect;
        import android.graphics.drawable.Drawable;
        import java.util.Map;

public class PlayObject implements Constants{

    protected Drawable curentImg;
    protected int x, y;
    Map<String, Drawable> hashMapImg;
    Map<String, Integer> hashMapSize;
    Rect rect;
    PlayObject(Map<String, Drawable> hashMapImg, Map<String, Integer> hashMapSize, int x, int y){
        this.hashMapImg = hashMapImg;
        this.hashMapSize = hashMapSize;
        this.x=x;
        this.y=y;
        rect = new Rect();
    }
    public Drawable getCurentImg() {
        return curentImg;
    }
    public Rect getRect() {
        return rect;
    }
    public void setPoisition(int x, int y) {
        rect.offset(x-rect.left, y-rect.top);
    }
}

