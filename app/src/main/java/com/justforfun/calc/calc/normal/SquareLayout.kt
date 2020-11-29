package com.justforfun.calc.calc.normal

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlin.math.min


class SquareLayout : RelativeLayout {
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?) : super(context) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(min(heightMeasureSpec, widthMeasureSpec), min(heightMeasureSpec, widthMeasureSpec))
    }
}