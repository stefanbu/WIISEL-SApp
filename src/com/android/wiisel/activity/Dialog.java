package com.android.wiisel.activity;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.android.wiisel.R;
import com.android.wiisel.manager.SoundManager;

public class Dialog extends Activity {

    public static WeakReference<Dialog> activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        activity = new WeakReference<Dialog>(this);

        final SoundManager instance = SoundManager.getSoundManagerWithoutCreating();
        if (instance == null) {
            finish();
        }

        findViewById(R.id.btn_i_am_ok).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View paramView) {
                instance.stop();
                finish();
            }
        });
        findViewById(R.id.btn_i_fall).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View paramView) {
                instance.finishUserFell();
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        unlockScreen();
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        activity.clear();
        activity = null;
        super.onDestroy();
    }

    private void unlockScreen() {
        Window window = this.getWindow();
        window.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);
    }
}
