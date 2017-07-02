package br.com.thiengo.thiengowebblog.domain

import android.webkit.JavascriptInterface
import br.com.thiengo.thiengowebblog.MainActivity
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async


class JavaScriptContact(val activity : MainActivity) {
    companion object{
        @JvmField val INTERFACE_NAME = "AndroidInstance"
    }

    @JavascriptInterface
    fun reloadPage(){
        async(UI){
            activity.loadPage()
        }
    }
}