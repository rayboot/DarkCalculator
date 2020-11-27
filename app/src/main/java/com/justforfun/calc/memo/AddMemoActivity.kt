package com.justforfun.calc.memo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.justforfun.calc.BaseActivity
import com.justforfun.calc.R
import com.justforfun.calc.memo.db.MemoDbProvider

class AddMemoActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Activity, id: Long? = null, content: String? = null, date: Long? = null) {
            val intent = Intent(context, AddMemoActivity::class.java)
            intent.putExtra("memo_id", id)
            intent.putExtra("memo_content", content)
            intent.putExtra("memo_date", date)
            context.startActivityForResult(intent, 1)
        }
    }

    lateinit var fab: FloatingActionButton
    lateinit var tv_date: TextView
    lateinit var et_content: EditText

    var mId: Long = 0
    var mDate: Long = 0
    var mContent: String? = ""
    var orgContent: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_memo)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fab = findViewById(R.id.fab_add)
        tv_date = findViewById(R.id.tv_date)
        et_content = findViewById(R.id.et_input)

        mId = intent.getLongExtra("memo_id", 0)
        mDate = intent.getLongExtra("memo_date", System.currentTimeMillis())
        mContent = intent.getStringExtra("memo_content")
        orgContent = mContent
        if (mId > 0) {
            et_content.setText(mContent)
        }
        tv_date.text = transToString(mDate)
    }

    fun onClickOpt(v: View) {
        mContent = et_content.text.toString()
        if (mContent.isNullOrEmpty()) {
            Snackbar.make(v, "内容不能为空", Snackbar.LENGTH_SHORT).show()
            return
        }
        MemoDbProvider.instance.insertData(mId, mContent!!, mDate)
        Snackbar.make(v, "保存成功", Snackbar.LENGTH_SHORT).show()

        if (orgContent != mContent) {
            val data = Intent()
            data.putExtra("change", true)
            setResult(RESULT_OK, data)
        }
        finish()
    }
}