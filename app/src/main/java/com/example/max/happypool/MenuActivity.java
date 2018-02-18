package com.example.max.happypool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ToggleButton;

public class MenuActivity extends Activity implements Constants {

    Intent intent;
    //    ToggleButton btn_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);


//        btn_new = (ToggleButton) findViewById(R.id.btn_new);
    }
    public void newGame(View v){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
//        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        intent.putExtra("BEGIN_FROM", START);
        startActivity(intent);
    }
    public void load(View v){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        intent.putExtra("BEGIN_FROM", LOAD);
        startActivity(intent);
    }
    public void exit(View v){
        finish();
    }
}
