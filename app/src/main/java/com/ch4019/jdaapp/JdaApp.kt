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
