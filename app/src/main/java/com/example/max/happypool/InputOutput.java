package com.example.max.happypool;

        import android.view.MotionEvent;
        import android.view.View;
        import android.view.View.OnTouchListener;

public class InputOutput implements OnTouchListener{

    private MyThread thread;
    float x;
    float y;

    InputOutput(MyView myView, MyThread myThread) {
        thread = myThread;
        myView.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: { // нажатие
                thread.setTouchDown(x, y);
                break;
            }
            case MotionEvent.ACTION_MOVE: // движение
                thread.setHeading(x, y);
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                thread.setTouchUp();
                break;
        }
        return true;
    }
}


