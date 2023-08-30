package com.ch4019.jdaapp.util


import java.math.BigInteger
import java.util.Random

/**
 * RSA编码器
 */
object RSAEncoder {
    private var n: BigInteger? = null
    private var e: BigInteger? = null

    fun rsaEncrypt(pwd: String, nStr: String, eStr: String): String {
        n = BigInteger(nStr, 16)
        e = BigInteger(eStr, 16)

        val r = rsaDoPublic(pkcs1pad2(pwd, (n!!.bitLength() + 7) shr 3)?:BigInteger.ZERO)
        var sp = r.toString(16)
        if (sp.length and 1 != 0) sp = "0$sp"
        return sp
    }

    private fun rsaDoPublic(x: BigInteger): BigInteger {
        if (e != null && n != null) {
            return x.modPow(e, n)
        } else {
            throw IllegalStateException("e or n is null")
        }
    }


    private fun pkcs1pad2(s: String, n: Int): BigInteger? {
        var n = n
        if (n < s.length + 11) {
            System.err.println("Message too long for RSAEncoder")
            return null
        }
        val ba = ByteArray(n)
        var i = s.length - 1
        while (i >= 0 && n > 0) {
            val c = s.codePointAt(i--)
            if (c < 128) { // encode using utf-8
                ba[--n] = c.toByte()
            } else if (c in 128..2047) {
                ba[--n] = (c and 63 or 128).toByte()
                ba[--n] = (c shr 6 or 192).toByte()
            } else {
                ba[--n] = (c and 63 or 128).toByte()
                ba[--n] = ((c shr 6) and 63 or 128).toByte()
                ba[--n] = (c shr 12 or 224).toByte()
            }
        }
        ba[--n] = "0".toByte()
        val temp = ByteArray(1)
        val rdm = Random(47L)
        while (n > 2) { // random non-zero pad
            temp[0] = "0".toByte()
            while (temp[0] == "0".toByte()) rdm.nextBytes(temp)
            ba[--n] = temp[0]
        }
        ba[--n] = 2.toByte()
        ba[--n] = "0".toByte()
        return BigInteger(ba)
    }
}
