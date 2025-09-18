package com.example.tft_log.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tft_log.ui.main.composable.MainTopbar
import com.example.tft_log.ui.main.composable.matchItemsComponent
import com.example.tft_log.ui.theme.AppColors

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel<MainViewModel>(),
    showToast: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MainContract.Effect.ShowErrorMessage -> {
                    showToast(effect.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.PrimaryColor)
            .systemBarsPadding()
            .imePadding()
            .padding(all = 20.dp),
        containerColor = AppColors.PrimaryColor,
        topBar = {
            MainTopbar(
                onClickSearch = { viewModel.setEvent(MainContract.Event.OnClickSearch(it)) }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            state.matchItems?.let {
                matchItemsComponent(matchItems = it)
            }
        }
    }
}