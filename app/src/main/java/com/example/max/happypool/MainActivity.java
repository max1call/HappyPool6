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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class MainActivity extends Activity {

    String TAG="Target";
    MyView myView;
    MyThread myThread;
//    public TextView tvt1;
    Context context;
    AttributeSet attrs;
    int startFrom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrom = getIntent().getIntExtra("BEGIN_FROM", 3);
        myView = new MyView(this, startFrom);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(myView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        myView=  findViewById(R.id.myview);
//        myView = new MyView(context);
        myThread=myView.getThread();
        myThread.setMainAktivity(this);
        Log.d("Load", "2savedInstanceState = "+savedInstanceState);
//        if (savedInstanceState != null) {
////            myThread.restoreState(savedInstanceState);
//            Log.w("Load", "savedInstanceState = "+savedInstanceState);
//        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Log.d("LoadM", "ONsavedInstanceState");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LoadM", "Pause");
        myThread.saveData();
    }
}