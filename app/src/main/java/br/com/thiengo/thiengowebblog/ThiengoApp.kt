package br.com.thiengo.thiengowebblog

import android.app.Application
import com.blankj.utilcode.util.Utils

/**
 * Created by viniciusthiengo on 01/07/17.
 */
class ThiengoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init( this );
    }
}