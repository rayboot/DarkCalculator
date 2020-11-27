package com.justforfun.calc.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.justforfun.calc.BaseActivity;
import com.justforfun.calc.MainActivity;
import com.justforfun.calc.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {
    private Context context;
    private TextView skip;
    private TipDiagram tipDiagram;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        tipDiagram = new TipDiagram();
        tipDiagram.setOnItemClickListener(new TipDiagram.onItemClickListener() {
            @Override
            public void onYesClick(View v) {
                tipDiagram.dismiss();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("agree", true);
                editor.apply();
                timerFlow();
            }

            @Override
            public void onNoClick(View v) {
                ((SplashActivity) context).finish();
            }

        });
        setContentView(R.layout.activity_splash);
        skip = findViewById(R.id.skip);

        boolean agreed = sharedPreferences.getBoolean("agree", false);
        if (!agreed) {
            tipDiagram.show(getSupportFragmentManager(), "dialog");
            tipDiagram.setCancelable(false);
        } else {
            timerFlow();
        }
    }

    private void timerFlow() {
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