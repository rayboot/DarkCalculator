package com.justforfun.calc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PrivacyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, PrivacyActivity.class));
    }
}