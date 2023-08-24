package com.ch4019.jdaapp.viewmodel.login

import kotlinx.serialization.Serializable

@Serializable
data class PublicKeyList(
    val modulus: String,
    val exponent: String
)
data class RsaKey(
    val keyN : String? = null,
    val keyE : Long = 0
)