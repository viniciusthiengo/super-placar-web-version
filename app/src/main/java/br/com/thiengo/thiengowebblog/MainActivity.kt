package br.com.thiengo.thiengowebblog

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.*
import com.blankj.utilcode.util.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*
import br.com.thiengo.thiengowebblog.domain.JavaScriptContact
import br.com.thiengo.thiengowebblog.domain.SPUrlCache
import com.blankj.utilcode.util.SPUtils


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SPUrlCache.clear()

        wv_no_internet.settings.javaScriptEnabled = true
        wv_no_internet.setBackgroundColor( R.color.colorBackground )
        wv_no_internet.addJavascriptInterface( JavaScriptContact(this), "AndroidInstance" )
        wv_no_internet.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }

        wv_site.settings.javaScriptEnabled = true
        wv_site.setBackgroundColor( R.color.colorBackground )
        wv_site.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                SPUrlCache.saveUrl( url )
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                //SPUtils.getInstance().getInt("page-index", 1)
                //SPUtils.getInstance().getString("page-" + SPUtils.getInstance().getInt("page-index"), request.toString())
                //super.shouldOverrideUrlLoading(view, request)
                //view?.loadUrl( view?.url )
                return false
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                loadPage()
            }
        }

        loadPage()
    }

    fun loadPage(){
        if( NetworkUtils.isConnected() ){
            wv_site.loadUrl( SPUrlCache.getLastUrl() )
            wv_no_internet.visibility = View.GONE
            wv_site.visibility = View.VISIBLE
        }
        else{
            //SPUrlCache.saveStatusBackClick(true)
            wv_no_internet.loadUrl("file:///android_asset/html/no-internet.html")
            wv_no_internet.visibility = View.VISIBLE
            wv_site.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        SPUrlCache.saveStatusBackClick(true)
        if( wv_site.canGoBack() ){
            Log.i("log", "URL onBackPressed: 1");
            wv_site.goBack()
        }
        /*else if( SPUrlCache.hasUrl() ){
            Log.i("log", "URL onBackPressed: 2");
            wv_site.loadUrl( SPUrlCache.getLastUrl() )
            //SPUrlCache.removeTopStackUrl()
        }*/
        else{
            super.onBackPressed()
        }
    }
}
