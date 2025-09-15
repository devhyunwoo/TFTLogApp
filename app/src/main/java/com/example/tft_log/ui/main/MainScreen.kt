package com.example.tft_log.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tft_log.ui.theme.Colors

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding(),
        containerColor = Colors.White
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            
        }
    }
}