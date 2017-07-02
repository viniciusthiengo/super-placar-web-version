package br.com.thiengo.thiengowebblog.extra

import com.blankj.utilcode.util.SPUtils


class UrlUtil {
    companion object{
        @JvmField val URL = "http://superplacar.com.br/"
        @JvmField val URL_NO_INTERNET = "file:///android_asset/html/no-internet.html"
        @JvmField val SP = SPUtils.getInstance("PREF")

        fun saveLastUrl(url: String?){
            SP.put("url", url ?: URL)
        }

        fun getLastUrl() = SP.getString("url", URL)

        fun clear(){
            SP.clear()
        }
    }
}