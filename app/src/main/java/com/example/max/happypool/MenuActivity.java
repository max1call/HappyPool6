package com.example.max.happypool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MenuActivity extends Activity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void newGame(View v){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
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
