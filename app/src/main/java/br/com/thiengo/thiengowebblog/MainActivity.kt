package br.com.thiengo.thiengowebblog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.thiengo.thiengowebblog.domain.CustomWebViewClient
import br.com.thiengo.thiengowebblog.domain.JavaScriptContact
import br.com.thiengo.thiengowebblog.extra.UrlUtil
import com.blankj.utilcode.util.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UrlUtil.clear()

        wv_no_internet.settings.javaScriptEnabled = true
        wv_no_internet.setBackgroundColor( R.color.colorBackground )
        wv_no_internet.addJavascriptInterface( JavaScriptContact(this), JavaScriptContact.INTERFACE_NAME )

        wv_site.settings.javaScriptEnabled = true
        wv_site.setBackgroundColor( android.R.color.transparent )
        wv_site.webViewClient = CustomWebViewClient(this)
    }

    override fun onResume() {
        super.onResume()
        loadPage()
    }

    fun loadPage(){
        if( NetworkUtils.isConnected() ){
            wv_site.loadUrl( UrlUtil.getLastUrl() )
            wv_no_internet.visibility = View.GONE
            wv_site.visibility = View.VISIBLE
        }
        else{
            wv_no_internet.loadUrl( UrlUtil.URL_NO_INTERNET )
            wv_no_internet.visibility = View.VISIBLE
            wv_site.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if( wv_site.visibility == View.VISIBLE
                && wv_site.canGoBack() ){
            wv_site.goBack()
        }
        else{
            super.onBackPressed()
        }
    }
}
