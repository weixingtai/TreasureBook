package com.suromo.network.impl

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.GsonBuilder
import com.suromo.common.BaseApplication
import com.suromo.network.BaseNetworkManager
import com.suromo.network.interceptor.CacheInterceptor
import com.suromo.network.interceptor.HeadInterceptor
import com.suromo.network.interceptor.LogInterceptor
import com.suromo.network.interceptor.TokenOutInterceptor
import com.suromo.network.manager.mApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/20
 * desc   : 网络请求构建器,自定义拦截器，结果解析等
 */
//双重校验锁式-单例 封装networkRequest 方便直接快速调用简单的接口
val networkRequest: NetworkRequest by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkManager.INSTANCE.getRequest(NetworkRequest::class.java, NetworkUrl.URL_BASE)
}

class NetworkManager : BaseNetworkManager() {

    companion object {
        val INSTANCE: NetworkManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkManager()
        }
    }

    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            //设置缓存配置 缓存最大10M
            cache(Cache(File(BaseApplication.INSTANCE.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
            //添加Cookies自动持久化
            cookieJar(persistentCookieJar)
            //添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
            addInterceptor(HeadInterceptor())
            //添加缓存拦截器 可传入缓存天数，不传默认7天
            addInterceptor(CacheInterceptor())
            addInterceptor(TokenOutInterceptor())
            // 日志拦截器
            addInterceptor(LogInterceptor())
            //超时时间 连接、读、写
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
        return builder
    }

    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
    }

    private val persistentCookieJar: PersistentCookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(BaseApplication.INSTANCE))
    }

    public suspend fun getMarkSixHistoryByYear():Any?{
        return networkRequest.getMarkSixHistoryByYear(22,2023).data
    }


}