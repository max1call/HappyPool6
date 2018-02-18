package com.example.max.happypool;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class MainActivity extends Activity {

    String TAG="Target";
    MyView myView;
    MyThread myThread;
    int startFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrom = getIntent().getIntExtra("BEGIN_FROM", 3);
        myView = new MyView(this, startFrom);
        setContentView(myView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        myThread=myView.getThread();
        myThread.setMainAktivity(this);
        Log.d("Load", "2savedInstanceState = "+savedInstanceState);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LoadM", "Pause");
        myThread.saveData();
    }
}