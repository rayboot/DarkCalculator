package com.justforfun.calc.main.ui.more

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.allenliu.versionchecklib.v2.AllenVersionChecker
import com.allenliu.versionchecklib.v2.builder.UIData
import com.google.gson.Gson
import com.justforfun.calc.*
import com.justforfun.calc.WebActivity.Companion.actionStart
import com.justforfun.http.AppUpdateInfo
import com.justforfun.http.DownloadToken
import com.justforfun.http.UpdateAppHttpUtil

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
        root.findViewById<View>(R.id.enter_more_1).setOnClickListener {
            // 彩铃
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

    var updateInfoUrl = "https://api.bq04.com/apps/latest/5ef99745b2eb46784beca2a4?api_token=c848b757cc666baf315362bd4daec4b7"
    var getDownloadUrl = "https://api.bq04.com/apps/5ef99745b2eb46784beca2a4/download_token?api_token=c848b757cc666baf315362bd4daec4b7"
    var downloadUrl = "http://download.bq04.com/apps/5ef99745b2eb46784beca2a4/install?api_token=c848b757cc666baf315362bd4daec4b7&download_token="

    var httpUtil = UpdateAppHttpUtil()

    fun updateFlow() {
        httpUtil.asyncGet(updateInfoUrl, object : UpdateAppHttpUtil.Callback {
            override fun onResponse(result: String) {
                val info = Gson().fromJson(result, AppUpdateInfo::class.java)
                if (info.version > BuildConfig.VERSION_CODE) {
                    httpUtil.asyncGet(getDownloadUrl, object : UpdateAppHttpUtil.Callback {
                        override fun onResponse(result: String) {
                            val dt = Gson().fromJson(result, DownloadToken::class.java)
                            val du = downloadUrl + dt.download_token
                            AllenVersionChecker
                                    .getInstance()
                                    .downloadOnly(
                                            UIData
                                                    .create()
                                                    .setTitle("检查到新版本")
                                                    .setContent(info.changelog)
                                                    .setDownloadUrl(du)
                                    )
                                    .executeMission(context)
                        }

                        override fun onError(error: String) {
                            Log.d("download", "11111111")
                        }
                    })
                } else {
                    Toast.makeText(context, "已是最新版本", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onError(error: String) {
                Log.d("download", "222222222222222")
            }
        })
    }
}