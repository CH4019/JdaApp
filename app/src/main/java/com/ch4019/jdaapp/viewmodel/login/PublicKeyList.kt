package com.ch4019.jdaapp.viewmodel.login

import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class PublicKeyList(
    val modulus: String,
    val exponent: String
)
data class RsaKey(
    val keyN : BigInteger? = null,
    val keyE : BigInteger? = null
)

data class RequestCookie(
    val cookie : String = ""
)