package com.tft.log.data.utils

object ImageUtils {
    fun createImageUrl(id: String, type: String, version: String): String {
        val regex = Regex("""\d+\.\d+""")
        val match = regex.find(version)?.value
        val currentVersion = "$match.1"
        val imageName = when (type) {
            ImageType.CHAMPION.type -> {
                val season = Regex("""TFT(\d+)""").find(id)?.groupValues?.get(1)
                "$id.TFT_Set$season"
            }

            else -> id
        }
        return "https://ddragon.leagueoflegends.com/cdn/$currentVersion/img/$type/$imageName.png"
    }
}

enum class ImageType(val type: String) {
    CHAMPION("tft-champion"),
    ITEM("tft-item")
}