package com.ch4019.jdaapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import io.appwrite.Client

class AppViewMModel : ViewModel() {
//计划重新写viewModel相关文件

    fun initAppState(context: Context): Client {
        return Client(context)
            .setEndpoint("https://cloud.appwrite.io/v1")
            .setProject("[PROJECT_ID]")
            .setSelfSigned(true)

    }

}



