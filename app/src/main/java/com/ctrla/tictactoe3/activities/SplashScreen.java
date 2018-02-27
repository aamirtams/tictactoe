package com.ctrla.tictactoe3.activities;

import com.ctrla.tictactoe3.R;
import com.google.android.gms.ads.MobileAds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class SplashScreen extends Activity {
    protected boolean _active = true;
    protected int _splashTime = 1000;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);
        int duration = Toast.LENGTH_LONG;
        MobileAds.initialize(this, "ca-app-pub-5246243065157193~4449969450");
        
        
        // thread for displaying the SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    
                    startActivity(new Intent(SplashScreen.this,TicTacToe.class));
                    finish();
                }
            }
        };
        splashTread.start();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
}