package com.example.max.happypool;

        import android.content.Context;
        import android.view.SurfaceView;
        import android.view.SurfaceHolder;

public class MyView extends SurfaceView implements SurfaceHolder.Callback, Constants {

    MyThread thread ;

    MyView(Context context, int startFrom) {
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread = new MyThread(startFrom, this, holder, context);
        setFocusable(true);
    }
    public MyThread getThread() {
        return thread;
    }
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) thread.pause();
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        thread.setSurfaceSize();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean re = true;
        thread.setRunning(false);
        while (re){
            try {
                thread.join();
                re=false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

