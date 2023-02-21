package com.suromo.common

import android.app.Application
import com.suromo.logger.MLog

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/21
 * desc   :
 */
open class BaseApplication : Application() {

    companion object {
        lateinit var INSTANCE:BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        MLog.initLogger()
    }
}