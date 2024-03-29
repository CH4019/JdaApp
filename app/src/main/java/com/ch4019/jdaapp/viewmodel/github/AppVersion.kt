package com.ch4019.jdaapp.viewmodel.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppVersion(
    @SerialName("newVersionCode")
    val newVersionCode: Long = 0
)

enum class IsUpdateApp {
    NULL, UPDATE, NO
}
