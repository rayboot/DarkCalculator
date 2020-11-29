package com.justforfun.calc.calc.normal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.justforfun.calc.R
import java.util.*
import java.util.regex.Pattern

class CalcActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, CalcActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val context: Context? = null
    private var toolbar: Toolbar? = null
    private val inText: EditText? = null
    private val stateText: TextView? = null
    private val outText: TextView? = null
    private val drawerPager: ViewPager? = null
    private val drawer: DrawerLayout? = null
    private val drawerPageList: ArrayList<View>? = null
    var delete: FrameLayout? = null
    private val TIME_EXIT = 2000
    private val mBackPressed: Long = 0
    private val showAD = false

    private val XX = intArrayOf(1, 3, 1, 3)
    private val YY = intArrayOf(6, 4, 5, 5)

    private val OPERATOR = arrayOf("÷", "×", "-", "+", "%", ",", "i")
    private val OPERATOR_VICE = arrayOf("√", "^", "!", "()", "°", "∞", "x")

    private val BUTTON = arrayOf(arrayOf("sqrt", "cbrt", "root", "rand", "randInt", "abs", "lg", "ln", "log",
            "min", "max", "fact", "sin", "cos", "tan", "asin", "acos",
            "atan", "re", "im", "arg", "norm", "reg", "conj", "diff",
            "sum", "lim", "eval", "fzero", "integ", "exp", "gcd", "lcm",
            "perm", "comb", "gamma", "round", "floor", "ceil", "sign",
            "remn", "prime", "isPrime", "prec", "base"), arrayOf("ans", "reg", "π", "e", "F", "h", "ћ", "γ", "φ", "c",
            "N", "R", "K", "k", "G", "Φ", "true", "false", "me", "mn", "mp"))

    private val BUTTON_VICE = arrayOf(arrayOf("平方根", "立方根", "开方", "随机复数", "随机整数", "绝对值", "常用对数", "自然对数", "对数",
            "最小", "最大", "阶乘", "正弦", "余弦", "正切", "反正弦", "反余弦", "反正切", "实部",
            "虚部", "辐角", "模长", "寄存", "共轭复数", "导函数", "累加求和", "极限", "求值",
            "函数零点", "定积分", "e底指数", "最大公约", "最小公倍", "排列", "组合", "伽玛函数",
            "四舍五入", "向下取整", "向上取整", "取正负号", "取余", "质数", "判断质数", "输出精度", "输出进制"), arrayOf("上次运算", "寄存器", "圆周率", "自然底数", "法拉第", "普朗克", "约化普朗克",
            "欧拉", "黄金分割", "光速", "阿伏伽德罗", "理想气体", "卡钦", "玻尔兹曼",
            "万有引力", "磁通量子", "真", "假", "电子质量", "质子质量", "中子质量"))

    private val FUNCTIONS_KEYWORDS = Pattern.compile(
            "\\b(" + "sqrt|cbrt|root|rand|randInt|lg|ln|log|abs|min|max|fact|" +
                    "sin|cos|tan|asin|acos|atan|re|im|arg|norm|reg|conj|diff|" +
                    "sum|lim|eval|fzero|integ|exp|gcd|lcm|perm|comb|round|floor|" +
                    "ceil|sign|gamma|remn|prime|isPrime|prec|base|Γ" + ")\\b")

    private val CONSTANS_KEYWORDS2 = Pattern.compile(
            "\\b(" + "ans|reg|true|false|me|mn|mp" + ")\\b")

    private val CONSTANS_KEYWORDS1 = Pattern.compile("[∞i°%πeFhћγφcNRkGΦ]")

    private val FUNCTION_LIST = arrayOf("科学计算", "大数计算", "大写数字")

    private val NUMERIC = arrayOf(
            "7", "8", "9",
            "4", "5", "6",
            "1", "2", "3",
            "·", "0", "=")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        setSupportActionBar(findViewById(R.id.toolbar))

        initToolBar()
    }

    private fun initToolBar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = null
        val actionBar = supportActionBar
        actionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        actionBar.title = "科学计算"
    }
}