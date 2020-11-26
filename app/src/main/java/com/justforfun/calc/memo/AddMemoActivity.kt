package com.justforfun.calc.memo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.justforfun.calc.R

class AddMemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_memo)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}