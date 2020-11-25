package com.justforfun.calc.main.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.justforfun.calc.R

/**
 * TODO: document your custom view class.
 */
class EnterViewV2(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    var ll_view: LinearLayout
    var tv_title: TextView
    var tv_desc: TextView
    var img: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_enter, this, true)
        ll_view = findViewById(R.id.ll_view)
        tv_title = findViewById(R.id.tv_title)
        tv_desc = findViewById(R.id.tv_desc)
        img = findViewById(R.id.iv_title)
        initAttrs(attrs)
    }

    fun initAttrs(attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.EnterView, 0, 0)
        val title = a.getString(R.styleable.EnterView_text)
        val desc = a.getString(R.styleable.EnterView_desc)
        val bg = a.getDrawable(R.styleable.EnterView_bg)
        val img = a.getDrawable(R.styleable.EnterView_img)
        a.recycle()

        tv_title.setText(title)
        tv_desc.setText(desc)
        ll_view.background = bg
        this.img.setImageDrawable(img)

    }


}