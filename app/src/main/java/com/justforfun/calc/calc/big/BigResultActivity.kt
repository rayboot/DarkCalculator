package com.justforfun.calc.calc.big

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.justforfun.calc.R


class BigResultActivity : AppCompatActivity() {
    lateinit var tv_res: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_result)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tv_res = findViewById(R.id.tv_res)
        tv_res.setText(getIntent().getStringExtra("results"));
    }

    fun onClickCopy(v: View) {
        val cm: ClipboardManager = baseContext.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        cm.setPrimaryClip(ClipData.newPlainText(null, tv_res.text))
        Snackbar.make(v, "已复制结果", Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        fun actionStart(context: Context, results: String?) {
            val intent = Intent(context, BigResultActivity::class.java)
            intent.putExtra("results", results)
            context.startActivity(intent)
        }
    }
}