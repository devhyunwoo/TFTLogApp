package com.example.tft_log.utils

import androidx.compose.ui.graphics.Color
import com.example.tft_log.ui.theme.AppColors


object ColorUtils {
    fun getTraitColor(style: Int): Color {
        return when (style) {
            1 -> {
                AppColors.Bronze
            }

            2 -> {
                AppColors.Silver
            }

            3 -> {
                AppColors.Gold
            }

            else -> {
                AppColors.Chromatic
            }
        }
    }
}