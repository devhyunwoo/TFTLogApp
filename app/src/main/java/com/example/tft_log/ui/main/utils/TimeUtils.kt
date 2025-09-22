package com.example.tft_log.ui.main.utils

import android.annotation.SuppressLint

object TimeUtils {
    fun Long.toYyMMddHHmm(): String {
        val diffTimeMillis = System.currentTimeMillis() - this
        val diffSeconds = diffTimeMillis / 1000
        val diffMinutes = diffSeconds / 60
        val diffHours = diffMinutes / 60
        val diffDays = diffHours / 24
        val diffWeeks = diffDays / 7
        val diffMonths = diffDays / 30
        val diffYears = diffDays / 365

        return when {
            diffYears > 0 -> "${diffYears}년전"
            diffMonths > 0 -> "${diffMonths}달전"
            diffWeeks > 0 -> "${diffWeeks}주전"
            diffDays > 0 -> "${diffDays}일전"
            diffHours > 0 -> "${diffHours}시간전"
            diffMinutes > 0 -> "${diffMinutes}분전"
            else -> "${diffSeconds}초전"
        }
    }

    @SuppressLint("DefaultLocale")
    fun Float.toTimeFormat(): String {
        val totalSeconds = this.toInt()
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return if (hours > 0) {
            String.format("%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }

    @SuppressLint("DefaultLocale")
    fun Float.toMinutes(): String  {
        val totalSeconds = this.toInt()
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return if (minutes > 0) {
            String.format("%d분 %d초", minutes, seconds)
        } else {
            String.format("%d초", seconds)
        }
    }
}