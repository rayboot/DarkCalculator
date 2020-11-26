package com.justforfun.calc.memo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.justforfun.calc.BaseActivity
import com.justforfun.calc.R
import java.text.SimpleDateFormat

class MemoActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, MemoActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}


fun transToString(time: Long): String {
    return SimpleDateFormat("yyyy年MM月dd日").format(time)
}

