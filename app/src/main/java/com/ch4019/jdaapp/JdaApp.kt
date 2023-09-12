package com.ch4019.jdaapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.serialization.json.Json
import rxhttp.wrapper.converter.SerializationConverter

@HiltAndroidApp
class JdaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
//        val converter: IConverter = SerializationConverter(Json, JsonConverter.MEDIA_TYPE)
////        RxHttpPlugins.init(Client.okHttpClient)
////            .setConverter(converter)
    }

    companion object {
        lateinit var instance: Application
            private set
    }
}
