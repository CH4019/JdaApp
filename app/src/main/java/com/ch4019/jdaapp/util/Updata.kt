package com.ch4019.jdaapp.util

import com.ch4019.jdaapp.viewmodel.github.AppVersion
import com.ch4019.jdaapp.viewmodel.github.UpDataList
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

//fun upData(): UpDataList {
//    val githubUrl = "https://api.github.com/repos/CH4019/xinximenhu/releases/latest"
//    val client = OkHttpClient()
//    val request = Request.Builder()
//        .url(githubUrl)
//        .header(
//            "User-Agent",
//            "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Mobile Safari/537.36 Edg/115.0.1901.203"
//        )
//        .build()
//    val response = client.newCall(request).execute()
//    val responseBody = response.body?.string() ?: "{}"
//    val json = Json { ignoreUnknownKeys = true }
//    return json.decodeFromString<UpDataList>(responseBody)
//}
//
//fun getNewVersionCode(): AppVersion {
//    val baseUrl1 = "https://jdaassistant.ch4019.fun/newApp.json"
//    val client = OkHttpClient()
//    val request = Request.Builder()
//        .url(baseUrl1)
//        .header(
//            "User-Agent",
//            "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Mobile Safari/537.36 Edg/115.0.1901.203"
//        )
//        .build()
//    val response = client.newCall(request).execute()
//    val responseBody = response.body?.string() ?: "{}"
//    val json = Json { ignoreUnknownKeys = true }
//    return json.decodeFromString<AppVersion>(responseBody)
//}