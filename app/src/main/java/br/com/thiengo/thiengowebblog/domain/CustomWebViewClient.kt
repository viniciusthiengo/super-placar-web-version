package br.com.thiengo.thiengowebblog.domain

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import br.com.thiengo.thiengowebblog.MainActivity
import br.com.thiengo.thiengowebblog.extra.UrlUtil
import kotlinx.android.synthetic.main.activity_main.*


class CustomWebViewClient(val activity: MainActivity) : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        UrlUtil.saveLastUrl( url )
        activity.fl_page_load.visibility = View.VISIBLE
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onPageCommitVisible(view: WebView?, url: String?) {
        super.onPageCommitVisible(view, url)
        activity.fl_page_load.visibility = View.GONE
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        activity.fl_page_load.visibility = View.GONE
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?) = false

    @SuppressWarnings("deprecation")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?) = false

    @TargetApi(Build.VERSION_CODES.M)
    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
        activity.loadPage()
    }

    @SuppressWarnings("deprecation")
    override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
        super.onReceivedError(view, errorCode, description, failingUrl)
        activity.loadPage()
    }
}