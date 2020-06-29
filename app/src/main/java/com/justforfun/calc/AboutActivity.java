package com.justforfun.calc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((TextView) findViewById(R.id.version)).setText("版本：" + BuildConfig.VERSION_NAME);
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }
}
