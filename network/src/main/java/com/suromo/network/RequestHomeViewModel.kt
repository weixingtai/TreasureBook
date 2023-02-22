package com.suromo.network

import android.util.Log
import com.suromo.common.BaseApplication.Companion.mApplicationContext
import com.suromo.database.TreasureDatabase
import com.suromo.database.dao.MarkSixDao
import com.suromo.database.di.DatabaseModule

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/27
 * 描述　: 有两种回调方式：
 * 1.首页文章列表 将返回的数据放在ViewModel中过滤包装给activity/fragment去使用
 * 2.首页轮播图 将返回的数据直接给activity/fragment去处理使用
 * 可以根据个人理解与喜好使用(建议 简单的不需要做数据过滤包装的能直接用返回数据的可以直接用2   复杂的需要自己封装一下让使用变的更方便的可以使用1  )
 */
class RequestHomeViewModel : BaseViewModel() {

    fun getMarSix(){
        requestNoCheck({ HttpRequestCoroutine.getMarkSix() }, {
            Log.d("wxt","请求成功！！！")
            val markHistory = it.body.item

            for (item in markHistory){
                val markSix : com.suromo.database.bean.MarkSix = com.suromo.database.bean.MarkSix(
                    item.longperiod,
                    item.period,
                    item.numbers,
                    item.sx,
                    item.wx,
                    item.date,
                )

//                val treasureDatabase:TreasureDatabase = TreasureDatabase.getInstance(mApplicationContext)
//                treasureDatabase.markSixDao().insert(markSix)

            }



            Log.d("wxt","markHistory:${markHistory}")
        }, {
            //请求失败
            Log.d("wxt","请求失败")
        })
    }
}