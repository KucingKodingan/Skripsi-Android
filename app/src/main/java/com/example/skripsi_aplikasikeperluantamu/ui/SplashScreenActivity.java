package com.example.skripsi_aplikasikeperluantamu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.skripsi_aplikasikeperluantamu.R;
import com.wang.avi.AVLoadingIndicatorView;

public class SplashScreenActivity extends Activity {

    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator("BallClipRotateMultipleIndicator");
//        avi.smoothToShow();
        avi.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, ScanCodeActivity.class));
                finish();
            }
        }, 3000);
    }
}