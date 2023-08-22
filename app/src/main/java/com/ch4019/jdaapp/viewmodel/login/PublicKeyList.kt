package com.ch4019.jdaapp.viewmodel.login

import kotlinx.serialization.Serializable

@Serializable
data class PublicKeyList(
    val modulus: String,
    val exponent: String
)