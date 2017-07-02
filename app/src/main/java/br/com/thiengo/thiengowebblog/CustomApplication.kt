package br.com.thiengo.thiengowebblog

import android.app.Application
import com.blankj.utilcode.util.Utils

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}