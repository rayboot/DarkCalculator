package com.justforfun.calc.memo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.justforfun.calc.BaseActivity
import com.justforfun.calc.R
import com.justforfun.calc.memo.db.Memo
import com.justforfun.calc.memo.db.MemoDbProvider
import org.jetbrains.anko.find
import java.text.SimpleDateFormat


class MemoActivity : BaseActivity(), MemoItemClick {
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, MemoActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var rv_content: RecyclerView
    var mData = mutableListOf<Memo>()
    lateinit var adapter: MemoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rv_content = find(R.id.rv_content)
        initView()
        mData = MemoDbProvider.instance.queryAll()
        if (mData.isEmpty()) {
            return
        }
        adapter = MemoAdapter(this, mData, this)
        rv_content.adapter = adapter
    }

    private fun refresh() {
        mData = MemoDbProvider.instance.queryAll()
        adapter.resetData(mData)
        adapter.notifyDataSetChanged()
    }

    private fun initView() {

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_content.layoutManager = layoutManager
        rv_content.itemAnimator = DefaultItemAnimator()
    }

    private fun loadData() {
    }

    fun onClickAdd(v: View) {
        AddMemoActivity.actionStart(this)
    }

    override fun onClick(id: Long) {
        mData.firstOrNull { it.id == id }?.let {
            AddMemoActivity.actionStart(this, it.id, it.content, it.date)
        }
    }

    override fun onLongClick(id: Long) {
        // 删除
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != 1) {
            return
        }
        data?.getBooleanExtra("change", false)?.let { change ->
            if (change) {
                refresh()
            }
        }
    }
}


fun transToString(time: Long): String {
    return SimpleDateFormat("yyyy年MM月dd日").format(time)
}

