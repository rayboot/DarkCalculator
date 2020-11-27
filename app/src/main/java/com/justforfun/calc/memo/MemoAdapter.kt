package com.justforfun.calc.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.justforfun.calc.R
import com.justforfun.calc.memo.db.Memo
import java.text.SimpleDateFormat

class MemoAdapter(var context: Context,
                  orgData: List<Memo>,
                  val onItemClick: MemoItemClick) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {
    var mData = mutableListOf<FormatMemoData>()
    val line = listOf(R.mipmap.memo_line_1, R.mipmap.memo_line_2, R.mipmap.memo_line_3)

    init {
        convertData(orgData)
    }

    fun resetData(data: List<Memo>) {
        mData.clear()
        convertData(data)
    }

    fun convertData(data: List<Memo>) {
        data.groupBy { formatDate(it.date) }
                .forEach { (date, memos) ->
                    mData.add(FormatMemoData(null, date))
                    mData.addAll(memos.map { FormatMemoData(it) })
                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                when (viewType) {
                    1 -> LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
                    else -> LayoutInflater.from(parent.context).inflate(R.layout.item_memo_date, parent, false)
                }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData[position]
        data.date?.let {
            holder.tv_date?.setText(it)
        }
        data.memo?.let {
            holder.tv_time?.setText(SimpleDateFormat("yyyy-MM-dd  HH:mm").format(it.date))
            holder.tv_title?.setText(it.content)
            holder.iv_line?.setImageResource(line.get((it.date % 3).toInt()))
            holder.view_memo?.setTag(it.id)

            holder.view_memo?.setOnClickListener {
                onItemClick.onClick(it.getTag() as Long)
            }

            holder.view_memo?.setOnLongClickListener {
                onItemClick.onLongClick(it.getTag() as Long)
                true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData[position].memo != null) 1 else 0
    }

    override fun getItemCount(): Int = mData.size

    data class FormatMemoData(
            val memo: Memo? = null,
            val date: String? = null
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_date = itemView.findViewById(R.id.tv_date) as? TextView
        var tv_title = itemView.findViewById(R.id.tv_title) as? TextView
        var tv_time = itemView.findViewById(R.id.tv_time) as? TextView
        var iv_line = itemView.findViewById(R.id.iv_line) as? ImageView
        var view_memo = itemView.findViewById(R.id.view_memo) as? View
    }

    fun formatDate(time: Long): String {
        return SimpleDateFormat("yyyy年MM月").format(time)
    }

}

interface MemoItemClick {
    fun onClick(id: Long)
    fun onLongClick(id: Long)
}