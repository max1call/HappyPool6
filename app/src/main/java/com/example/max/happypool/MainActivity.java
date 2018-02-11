package com.example.max.happypool;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import javax.security.auth.login.LoginException;

public class MainActivity extends Activity {

    String TAG="Target";
    MyView myView;
    MyThread myThread;
    public TextView tvt1;
    Context context;
    AttributeSet attrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView = new MyView(this);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(myView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        myView=  findViewById(R.id.myview);
//        myView = new MyView(context);
        myThread=myView.getThread();
        tvt1 = findViewById(R.id.tv1);
//        myView.setTextView(tvt1);
    }
}