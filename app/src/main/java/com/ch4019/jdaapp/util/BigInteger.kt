package com.ch4019.jdaapp.util

import java.math.BigInteger

class BigIntegerKotlin {
    private var value: BigInteger

    constructor(a: String, b: Int) {
        value = fromString(a, b)
    }

    val dbIts = 28
    private fun intArray(): IntArray {
        val intArray = IntArray(256){-1}
        var rr = '0'.code
        for (vv in 0..9) intArray[rr++] = vv
        rr = 'a'.code
        for (vv in 10 until 36) intArray[rr++] = vv
        rr = 'A'.code
        for (vv in 10 until 36) intArray[rr++] = vv
        return intArray
    }
    private val intArrayOf = intArray()

    private fun intAt(s: String, i: Int): Int {
        val code = s[i].code
        return if (code in intArrayOf.indices) intArrayOf[code] else -1
    }


    private fun fromString(a: String, b: Int): BigInteger {
        // 这里应该实现 fromString 方法的具体逻辑
        val k: Int = when (b){
            2 -> 1
            4 -> 2
            8 -> 3
            16 -> 4
            32 -> 5
            else -> 8
        }
        var t = 0
        var s = 0
        var i = a.length
        var mi = false
        var sh = 0
        while (--i >= 0) {
            val x = if(k == 8)  a[i].code and 0xff else intAt(a,i)
                if (x < 0) {
                    if (a[i] == '-') mi = true
                    continue
                }
            mi = false
            if (sh == 0) {
            }
        }
        return BigInteger(a, b)
    }
}
