package com.ch4019.jdaapp.util

import java.lang.Integer.parseInt

/**
 * 编解码工具类
 */
object B64 {
    private const val b64map = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
    private const val b64pad = '='
    private const val hexCode = "0123456789abcdef"

    // 获取对应16进制字符
    private fun int2char(a: Int): Char {
        return hexCode[a]
    }

    /**
     * Base64转16进制
     * @param s
     * @return
     */
    fun b64ToHex(s: String): String {
        var ret = ""
        var k = 0
        var slop = 0
        for (i in s.indices) {
            if (s[i] == b64pad) break
            val v = b64map.indexOf(s[i])
            if (v < 0) continue
            when (k) {
                0 -> {
                    ret += int2char(v shr 2)
                    slop = v and 3
                    k = 1
                }
                1 -> {
                    ret += int2char((slop shl 2) or (v shr 4))
                    slop = v and 0xf
                    k = 2
                }
                2 -> {
                    ret += int2char(slop)
                    ret += int2char(v shr 2)
                    slop = v and 3
                    k = 3
                }
                else -> {
                    ret += int2char((slop shl 2) or (v shr 4))
                    ret += int2char(v and 0xf)
                    k = 0
                }
            }
        }
        if (k == 1) ret += int2char(slop shl 2)
        return ret
    }

    /**
     * 16进制转Base64
     * @param h
     * @return
     */
    fun hexToB64(h: String): String {
        var c: Int
        val ret = StringBuilder()
        var i = 0
        while (i + 3 <= h.length) {
            c = parseInt(h.substring(i, i + 3), 16)
            ret.append(b64map[c shr 6])
            ret.append(b64map[c and 63])
            i += 3
        }
        when (h.length) {
            i + 1 -> {
                c = parseInt(h.substring(i, i + 1), 16)
                ret.append(b64map[c shl 2])
            }
            i + 2 -> {
                c = parseInt(h.substring(i, i + 2), 16)
                ret.append(b64map[c shr 2])
                ret.append(b64map[(c and 3) shl 4])
            }
        }
        while (ret.length and 3 > 0) ret.append(b64pad)
        return ret.toString()
    }
}
