package com.woo.tft_log

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import com.woo.tft_log.navigation.TFTLogNavDisplay
import com.woo.tft_log.ui.theme.AppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(AppColors.PrimaryColor.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(AppColors.PrimaryColor.toArgb())
        )
        setContent {
            TFTLogNavDisplay(
                finish = {
                    finish()
                }
            )
        }
    }
}
