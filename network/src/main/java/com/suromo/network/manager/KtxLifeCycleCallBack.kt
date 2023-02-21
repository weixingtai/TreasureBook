package com.suromo.network.manager

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.suromo.logger.MLog

/**
 * 作者　: hegaojian
 * 时间　: 20120/1/7
 * 描述　:
 */
class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        KtxActivityManger.pushActivity(activity)
        MLog.d("onActivityCreated : ${activity.localClassName}")
    }
    override fun onActivityStarted(activity: Activity) {
        MLog.d( "onActivityStarted : ${activity.localClassName}")
    }

    override fun onActivityResumed(activity: Activity) {
        MLog.d("onActivityResumed : ${activity.localClassName}")
    }

    override fun onActivityPaused(activity: Activity) {
        MLog.d("onActivityPaused : ${activity.localClassName}")
    }


    override fun onActivityDestroyed(activity: Activity) {
        MLog.d("onActivityDestroyed : ${activity.localClassName}")
        KtxActivityManger.popActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityStopped(activity: Activity) {
        MLog.d("onActivityStopped : ${activity.localClassName}")
    }


}