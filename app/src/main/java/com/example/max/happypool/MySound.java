package com.example.max.happypool;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MySound implements OnLoadCompleteListener{

    final int MAX_STREAMS = 6;

    SoundPool soundPool;
    int soundJump, soundBulk, bgMusic, soundFall, soundWin, goSoundShlep;
//    int ;
//    int streamIDShot;
//    int streamIDExplosion;

    MySound(Context context){

        soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(this);

        soundJump = soundPool.load(context, R.raw.jump, 1);
        soundBulk = soundPool.load(context, R.raw.bulk, 1);
        bgMusic = soundPool.load(context, R.raw.bgmusic, 1);
        soundFall = soundPool.load(context, R.raw.fall, 1);
        soundWin = soundPool.load(context, R.raw.victory, 1);
        goSoundShlep = soundPool.load(context, R.raw.shlep, 1);

//        try {
//            soundIdExplosion = sp.load(getAssets().openFd("egor.mp3"), 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
    }

    public void goSoundJump() {
        soundPool.play(soundJump, 1, 1, 0, 0, 1);
    }
    public void goSoundBulk() {
        soundPool.play(soundBulk, 1, 1, 0, 0, 1);
        }
    public void goSoundMusic() {
            soundPool.play(bgMusic, 1, 1, 0, 5, 1);
        }
    public void goSoundFall() {
            soundPool.play(soundFall, 1, 1, 0, 0, 1);
        }
    public void goSoundWin() {
            soundPool.play(soundWin, 1, 1, 0, 0, 1);
        }
    public void goSoundShlep() {
            soundPool.play(goSoundShlep, 1, 1, 0, 0, 1);
        }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
    }

    public void stopSoundMusic() {
        if (bgMusic>0) soundPool.stop(bgMusic);
    }
}
