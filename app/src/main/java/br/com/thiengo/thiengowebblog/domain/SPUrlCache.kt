package br.com.thiengo.thiengowebblog.domain

import android.util.Log
import com.blankj.utilcode.util.SPUtils

/**
 * Created by viniciusthiengo on 01/07/17.
 */
class SPUrlCache {
    companion object{
        var sp = SPUtils.getInstance("PREF")

        fun saveStatusBackClick( status: Boolean ){
            sp.put("was_back_clicked", status)
        }

        fun saveUrl( url: String? ){
            Log.i("LOG", "URL: $url");
            if( url!!.startsWith("file:///android_asset") ){
                return
            }
            Log.i("LOG", "URL after: $url")
            Log.i("LOG", "URL back status: ${sp.getBoolean("was_back_clicked")}")

            if( !sp.getBoolean("was_back_clicked") ){
                Log.i("LOG", "URL INSIDE: ${sp.getString("urls")}|$url")
                sp.put("urls", "${sp.getString("urls")}|$url")
            }
            else{
                saveStatusBackClick(false)
                removeTopStackUrl()
            }
        }

        fun removeTopStackUrl(){
            Log.i("Log", "URLs before: " + sp.getString("urls"));
            var urls = sp.getString("urls").split("|")
            urls = urls.subList(0 , urls.size - 1)
            sp.put("urls", urls.joinToString("|").trimStart('|'))
            Log.i("Log", "URLs after: " + sp.getString("urls"));
        }

        fun hasUrl() = sp.getString("urls").split("|").size - 1 > 0

        fun getLastUrl() = if( sp.getString("urls", "").isNotBlank() )
                sp.getString("urls").split("|").last()
            else
            "http://superplacar.com.br/"


        fun clear(){
            Log.i("Log", "URLs: CLEAR");
            sp.clear()
        }
    }
}