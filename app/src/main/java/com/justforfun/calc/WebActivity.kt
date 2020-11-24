package com.justforfun.calc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView


class WebActivity : BaseActivity() {

    lateinit var webView: WebView

    companion object {
        fun actionStart(context: Context, title: String, url: String) {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", url)
            intent.putExtra("title", title)
            context.startActivity(intent)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView = findViewById(R.id.webview)
        supportActionBar!!.setTitle(intent.getStringExtra("title"))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.loadUrl(intent.getStringExtra("url"))
    }

    override fun onDestroy() {
        super.onDestroy()
        //webview停止加载
        webView.stopLoading()
        //webview销毁
        webView.destroy()
        //webview清理内存
        webView.clearCache(true)
        //webview清理历史记录
        webView.clearHistory()
    }

    override fun onBackPressed() {
        //点击返回键返回url的上一个页面，而不是返回app中的上个页面
        if (webView.canGoBack()) {
            // goBack()表示返回WebView的上一页面
            webView.goBack()
            return
        }
        super.onBackPressed()
    }

}