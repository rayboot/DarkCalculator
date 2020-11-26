package com.justforfun.calc.calc.capital

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.justforfun.calc.AutofitHelper
import com.justforfun.calc.R

class CapitalMoneyActivity : AppCompatActivity() {

    lateinit var tv_res: TextView
    lateinit var et_input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capital_money2)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tv_res = findViewById(R.id.tv_res)
        et_input = findViewById(R.id.et_input)
        AutofitHelper.create(tv_res).maxLines = 6

        AutofitHelper.create(et_input)


        et_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = s.toString()
                if (s.length == 0 || str == ".") {
                    tv_res.setText("···")
                    return
                }
                val i = str.indexOf(".")
                if (i != -1) {
                    if (str.substring(i, str.length).length > 7) {
                        tv_res.setText("小数点后不得超过6位")
                        return
                    }
                }
                tv_res.setText(format(s.toString()))
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private val cnNumbers = charArrayOf('零', '壹', '贰', '叁', '肆', '伍',
            '陆', '柒', '捌', '玖')

    private val units = charArrayOf('纳', '微', '毫', '厘', '分', '角', '元', '拾', '佰',
            '仟', '萬', '拾', '佰', '仟', '億', '拾', '佰', '仟', '兆', '拾',
            '佰', '仟', '京', '拾', '佰', '仟', '垓', '拾', '佰', '仟', '杼',
            '拾', '佰', '仟', '穰', '拾', '佰', '仟', '溝', '拾', '佰', '仟',
            '澗', '拾', '佰', '仟', '正', '拾', '佰', '仟', '載', '拾', '佰',
            '仟', '極', '拾', '佰', '仟')

    fun format(s: String): String {
        return if (s.toDouble() > 10E51) "数值太大，无法转换" else transform(s)
    }

    private fun transform(original: String): String {
        var integerPart = ""
        var floatPart = ""
        if (original.indexOf(".") > -1) {
            val dotIndex = original.indexOf(".")
            integerPart = original.substring(0, dotIndex)
            floatPart = original.substring(dotIndex + 1)
        } else {
            integerPart = original
        }
        val sb = StringBuffer()
        for (i in 0 until integerPart.length) {
            val number = integerPart[i].toString().toInt()
            sb.append(cnNumbers[number])
            sb.append(units[integerPart.length + 5 - i])
        }
        if (floatPart.length >= 1) {
            for (i in 0 until floatPart.length) {
                val number = floatPart[i].toString().toInt()
                sb.append(cnNumbers[number])
                if (i < 6) {
                    sb.append(units[5 - i])
                }
            }
        } else {
            sb.append('整')
        }
        return sb.toString()
    }

    fun onClickCopy(v: View) {
        val cm: ClipboardManager = baseContext.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        cm.setPrimaryClip(ClipData.newPlainText(null, tv_res.text))
        Snackbar.make(v, "已复制结果", Snackbar.LENGTH_SHORT).show()
    }

    fun onClickClear(v: View) {
        et_input.setText("")
    }
}