package com.example.max.happypool;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class MySound implements OnLoadCompleteListener{

    private SoundPool soundPool;
    private int soundJump, soundBulk, bgMusic, soundFall, soundWin, goSoundShlep;

    MySound(Context context){
        final int MAX_STREAMS = 3;
        soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(this);
        soundJump = soundPool.load(context, R.raw.jump, 1);
        soundBulk = soundPool.load(context, R.raw.bulk, 1);
        bgMusic = soundPool.load(context, R.raw.bgmusic, 1);
        soundFall = soundPool.load(context, R.raw.fall, 1);
        soundWin = soundPool.load(context, R.raw.victory, 1);
        goSoundShlep = soundPool.load(context, R.raw.shlep, 1);
    }
    void goSoundJump() {
        soundPool.play(soundJump, 1, 1, 0, 0, 1);
    }
    void goSoundBulk() {
        soundPool.play(soundBulk, 1, 1, 0, 0, 1);
        }
    void goSoundMusic() {
            soundPool.play(bgMusic, 1, 1, 0, 5, 1);
        }
    void goSoundFall() {
            soundPool.play(soundFall, 1, 1, 0, 0, 1);
            soundPool.stop(bgMusic);
        }
    void goSoundWin() {
            soundPool.play(soundWin, 1, 1, 0, 0, 1);
            soundPool.stop(bgMusic);
        }
    void goSoundShlep() {
            soundPool.play(goSoundShlep, 1, 1, 0, 0, 1);
        }
    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {}
    void stopSoundMusic() {
        if (bgMusic>0) soundPool.stop(bgMusic);
    }
    void finish() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
