package com.justforfun.calc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {
    private Context context;
    private TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_splash);
        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                MainActivity.actionStart(context);
                SplashActivity.this.finish();
            }
        });
        timerTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private Timer timer;
    private int secondLeft = 4;

    private void timerTask() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        secondLeft--;
                        skip.setText("跳过（" + (secondLeft + 1) + ")");
                        if (secondLeft < 0) {
                            timer.cancel();
                            MainActivity.actionStart(context);
                            SplashActivity.this.finish();
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 1000);
    }
}