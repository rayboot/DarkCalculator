package com.justforfun.calc.calc.big

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.justforfun.calc.BaseActivity
import com.justforfun.calc.R
import java.math.BigDecimal

class BigDecimalActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, BigDecimalActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var etInput1: EditText
    lateinit var etInput2: EditText
    lateinit var btns: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_decimal2)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        etInput1 = findViewById(R.id.et_input1)
        etInput2 = findViewById(R.id.et_input2)
        btns = listOf(
                this.findViewById(R.id.btn_plus),
                this.findViewById(R.id.btn_minus),
                this.findViewById(R.id.btn_multi),
                this.findViewById(R.id.btn_div))
        btns[0].isSelected = true
    }

    fun onClickOpt(v: View) {
        btns.forEach {
            it.isSelected = false
        }
        v.isSelected = true
    }

    fun onClickStartCalc(v: View) {
        var a = etInput1.text.toString()
        var b = etInput2.text.toString()
        a = if (a.length == 0) "0" else a
        b = if (b.length == 0) "0" else b
        if (a.indexOf("..") != -1 || b.indexOf("..") != -1) {
            Snackbar.make(v, "小数点不能大于一个", Snackbar.LENGTH_SHORT).show()
            return
        }

        val a1 = BigDecimal(a)
        val b2 = BigDecimal(b)
        val selBtn = btns.firstOrNull { it.isSelected }
        if (selBtn == null) {
            Snackbar.make(v, "请先选择一个操作项", Snackbar.LENGTH_SHORT).show()
            return
        }
        when (selBtn.id) {
            R.id.btn_plus -> {
                BigResultActivity.actionStart(this, a1.add(b2).toString())
            }
            R.id.btn_minus -> {
                BigResultActivity.actionStart(this, a1.subtract(b2).toString())
            }
            R.id.btn_multi -> {
                BigResultActivity.actionStart(this, a1.multiply(b2).toString())
            }
            R.id.btn_div -> {
                if (b2.toDouble() == 0.0) {
                    Snackbar.make(v, "除数不能为零", Snackbar.LENGTH_SHORT).show()
                    return
                }
                BigResultActivity.actionStart(this, a1.divide(b2, 100000, BigDecimal.ROUND_HALF_UP).toString())
            }
        }
    }

    fun onClickClear(v: View) {
        (this.window.decorView.findFocus() as? EditText)?.setText("")
    }
}