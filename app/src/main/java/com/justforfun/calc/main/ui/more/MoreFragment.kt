package com.justforfun.calc.main.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.justforfun.calc.AboutActivity
import com.justforfun.calc.PrivacyActivity
import com.justforfun.calc.R
import com.justforfun.calc.UserPrivacyActivity
import com.justforfun.calc.WebActivity.Companion.actionStart

class MoreFragment : Fragment() {

    private lateinit var moreViewModel: MoreViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        moreViewModel =
                ViewModelProvider(this).get(MoreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_more, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        moreViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        root.findViewById<View>(R.id.enter_more_1).setOnClickListener {
            // 彩铃

            //从其他浏览器打开
            actionStart(requireContext(), "热门铃声", "https://iring.diyring.cc/friend/9ea019bbb41600c7")
        }
        root.findViewById<View>(R.id.enter_more_2).setOnClickListener {
            // 更新
            updateFlow()
        }
        root.findViewById<View>(R.id.enter_more_3).setOnClickListener {
            // 帮助
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle("帮助")
                    .setMessage(R.string.app_help)
                    .setPositiveButton("确定", null)
                    .show()
        }
        root.findViewById<View>(R.id.enter_more_4).setOnClickListener {
            // 隐私
            PrivacyActivity.actionStart(requireContext())
        }
        root.findViewById<View>(R.id.enter_more_5).setOnClickListener {
            // 用户协议
            UserPrivacyActivity.actionStart(requireContext())
        }
        root.findViewById<View>(R.id.enter_more_6).setOnClickListener {
            // 关于
            AboutActivity.actionStart(requireContext())
        }
        return root
    }

    private fun updateFlow() {
        TODO("Not yet implemented")
    }
}