package com.ch4019.jdaapp.model.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppVersion(
    @SerialName("newVersionCode")
    val newVersionCode: Long = 0
)
