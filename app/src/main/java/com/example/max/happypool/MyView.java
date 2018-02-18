package com.example.max.happypool; //сохранение текущ состояния

        import android.content.Context;
        import android.os.Handler;
        import android.os.Message;
        import android.util.AttributeSet;


        import android.view.SurfaceView;
        import android.view.SurfaceHolder;

        import android.widget.RelativeLayout;
        import android.widget.TextView;


public class MyView extends SurfaceView implements SurfaceHolder.Callback, Constants {

    MyThread thread ;
//    public TextView tvt1;
    private String str;


    MyView(Context context, int startFrom) {
        super(context);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);



//        tvt1 = findViewById(mLayout.idtv1);
//        tvt1 = (TextView) findViewById(R.id.tv1);
//        tvt1 = new TextView(context);

        thread = new MyThread(startFrom, this, holder, context, new Handler() {
            @Override
            public void handleMessage(Message m) {
                if(m.what==STATE_LOSE){
//                    tvt1.setText("Xtttt");//R.string.mode_lose
//                    tvt1.setVisibility();
                }
            }
        });
//        tvt1.setText("Fgfhd dhdf");
        setFocusable(true);
    }

//    public MyView(Context context) {
//        super(context);
//    }

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
        thread.setSurfaceSize(width, height);
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


//    public void setTextView(TextView tvt1) {
//        this.tvt1=tvt1;
//    }
}

