package com.justforfun.calc.guide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.justforfun.calc.R

class GuideViewPagerAdapter : RecyclerView.Adapter<GuideViewPagerAdapter.ViewHolder>() {

    val imgs = listOf(R.mipmap.guide_img_1, R.mipmap.guide_img_2, R.mipmap.guide_img_3)
    val listTip1 = listOf("计算", "随时记录", "我是一款支持复数运算和解方程的综合科学计算器")
    val listTip2 = listOf("是一种别具匠心的艺术", "好记性不如烂笔头", "")
    val tip1Size = listOf(30f, 30f, 20f)


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tip1: TextView
        var tip2: TextView
        var img: ImageView
        var btn: Button
        var tvBottom: TextView

        init {
            tip1 = itemView.findViewById(R.id.tv_tip1)
            tip2 = itemView.findViewById(R.id.tv_tip2)
            img = itemView.findViewById(R.id.iv_tip)
            btn = itemView.findViewById(R.id.btn_go)
            tvBottom = itemView.findViewById(R.id.tv_bottom)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.guide_viewpager_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tip1.setText(listTip1[position])
        holder.tip1.textSize = tip1Size[position]
        holder.tip2.setText(listTip2[position])
        holder.tip2.visibility = if (position == 2) View.GONE else View.VISIBLE
        holder.img.setImageResource(imgs[position])
        holder.btn.visibility = if (position != 2) View.GONE else View.VISIBLE
        holder.tvBottom.visibility = if (position != 2) View.GONE else View.VISIBLE
    }

    override fun getItemCount(): Int = 3

}

