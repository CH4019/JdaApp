package com.ch4019.jdaapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import github.leavesczy.monitor.MonitorInterceptor
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.callback.IConverter
import rxhttp.wrapper.callback.JsonConverter
import rxhttp.wrapper.converter.SerializationConverter

@HiltAndroidApp
class JdaApp : Application() {
//    经过检查，下面代码出现
//      new JsonPayload exception:Unterminated object at character 79 of
//      {pkg:com.ch4019.jdaapp,id:539166472,tag:"null",channelid:" ",
//      channelname:Http Notif,isOnGoing:"false",importance:3,isCustomNotification:false,
//      language:zh,style:"android.app.Notification$InboxStyle",isFromPush:"false",
//      isNoClear:false,isForeground:false}
//     问题

//    再次添加又无报错

    override fun onCreate() {
        super.onCreate()
        instance = this
        val converter: IConverter = SerializationConverter(Json, JsonConverter.MEDIA_TYPE)
        RxHttpPlugins.init(okHttpClient)
            .setConverter(converter)
    }

    companion object {
        private lateinit var instance: Application
        val okHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor(MonitorInterceptor(instance))
                .build()
        }
    }
}
