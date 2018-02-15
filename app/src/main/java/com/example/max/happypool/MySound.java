package com.example.max.happypool;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MySound implements OnLoadCompleteListener{

//    final String LOG_TAG = "myLogs";
    final int MAX_STREAMS = 5;

    SoundPool soundPool;
    int soundJump, soundBulk, BgMusic;
//    int ;
//    int streamIDShot;
//    int streamIDExplosion;

    MySound(){

        soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(this);

        soundJump = soundPool.load(this, R.raw.jump, 1);
//        Log.d(LOG_TAG, "soundIdShot = " + soundIdShot);

//        try {
//            soundIdExplosion = sp.load(getAssets().openFd("egor.mp3"), 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Log.d(LOG_TAG, "soundIdExplosion = " + soundIdExplosion);
//
//    }

    public void onClick(View view) {
        sp.play(soundIdShot, 1, 1, 0, 0, 1);
        sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Log.d(LOG_TAG, "onLoadComplete, sampleId = " + sampleId + ", status = " + status);
    }

}
