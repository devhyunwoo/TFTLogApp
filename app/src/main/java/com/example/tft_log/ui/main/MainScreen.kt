package com.example.tft_log.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tft_log.ui.common.LoadingView
import com.example.tft_log.ui.main.composable.MainTopbar
import com.example.tft_log.ui.main.composable.matchItemsComponent
import com.example.tft_log.ui.theme.AppColors

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel<MainViewModel>(),
    showToast: (String) -> Unit,
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
            .padding(top = 10.dp)
            .background(color = AppColors.PrimaryColor)
            .systemBarsPadding()
            .imePadding()
            .padding(horizontal = 20.dp),
        containerColor = AppColors.PrimaryColor,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .background(color = AppColors.PrimaryColor)
                        .padding(bottom = 10.dp)
                ) {
                    MainTopbar(
                        onClickSearch = { viewModel.setEvent(MainContract.Event.OnClickSearch(it)) },
                        initialText = state.initialText
                    )
                }
            }

            state.matchItems?.let {
                matchItemsComponent(matchItems = it, onClickID = { participant ->
                    viewModel.setEvent(MainContract.Event.OnClickID(participant = participant))
                })
            }
        }
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                LoadingView()
            }
        }
    }
}