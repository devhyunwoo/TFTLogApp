package com.example.tft_log.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Main : Route
}