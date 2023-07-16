package com.ch4019.jdaapp.network

import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient

object Client {
    private val builder = OkHttpClient.Builder().apply {
        connectionSpecs(
            listOf(
                ConnectionSpec.CLEARTEXT,
                ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .allEnabledTlsVersions()
                    .allEnabledCipherSuites()
                    .build()
            )
        )
    }

    val okHttpClient = builder.build()
}
