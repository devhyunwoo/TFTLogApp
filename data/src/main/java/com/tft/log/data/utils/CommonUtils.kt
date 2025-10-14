package com.tft.log.data.utils

object CommonUtils {
    fun String.compareVersion(other: String): Int {
        val parts1 = this.split(".").map { it.toIntOrNull() ?: 0 }
        val parts2 = other.split(".").map { it.toIntOrNull() ?: 0 }

        val maxLength = maxOf(parts1.size, parts2.size)

        for (i in 0 until maxLength) {
            val v1 = parts1.getOrNull(i) ?: 0
            val v2 = parts2.getOrNull(i) ?: 0

            if (v1 != v2) return v1.compareTo(v2)
        }

        return 0
    }

    fun formatTftRound(round: Int): String {
        if (round <= 0) return "-"
        val stage = (round - 1) / 7 + 1
        val subRound = (round - 1) % 7 + 1
        return "$stage-$subRound"
    }
}


