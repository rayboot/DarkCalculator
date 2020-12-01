package com.justforfun.calc.main.ui.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.justforfun.calc.R
import com.justforfun.calc.memo.AddMemoActivity
import com.justforfun.calc.memo.DeleteDiagram
import com.justforfun.calc.memo.MemoAdapter
import com.justforfun.calc.memo.MemoItemClick
import com.justforfun.calc.memo.db.Memo
import com.justforfun.calc.memo.db.MemoDbProvider
import org.jetbrains.anko.find

class MemoFragment : Fragment() {

    private lateinit var rvMemo: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var llNormal: LinearLayout
    private lateinit var llMemo: LinearLayout

    var mData = mutableListOf<Memo>()
    lateinit var adapter: MemoAdapter
    lateinit var delDiagram: DeleteDiagram
    var delId = 0L

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_memo, container, false)

        rvMemo = root.find(R.id.rv_content)
        fabAdd = root.find(R.id.fab_add)
        llNormal = root.find(R.id.ll_normal)
        llMemo = root.find(R.id.ll_memo)
        root.findViewById<View>(R.id.enter_memo_1).setOnClickListener { fabAdd.performClick() }

        loadData()
        initView()
        loadView()
        loadData()

        return root
    }

    private fun loadData() {
        mData = MemoDbProvider.instance.queryAll()
    }

    private fun initView() {
        fabAdd.setOnClickListener { AddMemoActivity.actionStart(requireActivity()) }

        adapter = MemoAdapter(this.requireContext(), mData, object : MemoItemClick {
            override fun onClick(id: Long) {
                mData.firstOrNull { it.id == id }?.let {
                    AddMemoActivity.actionStart(requireActivity(), it.id, it.content, it.date)
                }
            }

            override fun onLongClick(id: Long) {
                delId = id
                delDiagram.show(parentFragmentManager, "dialog")
                delDiagram.isCancelable = true
            }
        })
        rvMemo.adapter = adapter
        val layoutManager = LinearLayoutManager(this.requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvMemo.layoutManager = layoutManager
        rvMemo.itemAnimator = DefaultItemAnimator()

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

    private fun refresh() {
        loadData()
        loadView()
        adapter.resetData(mData)
        adapter.notifyDataSetChanged()
    }

    private fun loadView() {
        if (mData.isEmpty()) {
            llNormal.visibility = View.VISIBLE
            fabAdd.visibility = View.GONE
            llMemo.visibility = View.GONE
        } else {
            llNormal.visibility = View.GONE
            fabAdd.visibility = View.VISIBLE
            llMemo.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        refresh()
    }
}