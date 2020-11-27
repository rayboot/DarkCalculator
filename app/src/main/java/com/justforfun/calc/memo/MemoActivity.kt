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
    lateinit var delDiagram: DeleteDiagram
    var delId = 0L

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


        delDiagram = DeleteDiagram("提示", "是否确认删除此条备忘?")
        delDiagram.setOnItemClickListener(object : DeleteDiagram.onItemClickListener {
            override fun onYesClick(v: View) {
                if (delId != 0L) {
                    MemoDbProvider.instance.deleteData(delId)
                    refresh()
                }
                delId = 0L
                delDiagram.dismiss()
            }

            override fun onNoClick(v: View) {
                delId = 0
                delDiagram.dismiss()
            }
        })
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
        delId = id
        delDiagram.show(supportFragmentManager, "dialog")
        delDiagram.isCancelable = true
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

