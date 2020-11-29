package com.justforfun.calc.calc.normal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.justforfun.calc.MainActivity
import com.justforfun.calc.R

class NumAdapter(private val text: List<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return text.size
    }

    override fun getItem(position: Int): Any {
        return text[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private inner class ViewHolder {
        var title: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(MainActivity.activity).inflate(R.layout.button_numeric, parent, false)
            viewHolder = ViewHolder()
            viewHolder.title = view.findViewById<View>(R.id.text_item) as TextView
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.title!!.text = text[position]
        return view
    }

}
