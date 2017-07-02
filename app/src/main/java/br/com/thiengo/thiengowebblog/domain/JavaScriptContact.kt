package br.com.thiengo.thiengowebblog.domain

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import br.com.thiengo.thiengowebblog.MainActivity
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

/**
 * Created by viniciusthiengo on 01/07/17.
 */
class JavaScriptContact(val activity : MainActivity) {
    @JavascriptInterface
    fun reloadPage(){
        async(UI){
            activity.loadPage()
        }
    }
}