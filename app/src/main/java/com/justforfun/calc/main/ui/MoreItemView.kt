package com.justforfun.calc.main.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.justforfun.calc.R

/**
 * TODO: document your custom view class.
 */
class MoreItemView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    var iv_header: ImageView
    var tv_title: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_more, this, true)
        iv_header = findViewById(R.id.iv_header)
        tv_title = findViewById(R.id.tv_title)
        initAttrs(attrs)
    }

    fun initAttrs(attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MoreItemView, 0, 0)
        val title = a.getString(R.styleable.MoreItemView_moreText)
        val img = a.getDrawable(R.styleable.MoreItemView_moreImg)
        a.recycle()
        tv_title.setText(title)
        iv_header.setImageDrawable(img)
    }
}