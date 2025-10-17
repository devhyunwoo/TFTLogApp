package com.tft.log.data.utils

object ImageUtils {
    fun createImageUrl(id: String, type: String, version: String): String {
        val regex = Regex("""\d+\.\d+""")
        val match = regex.find(version)?.value
        val currentVersion = "$match.1"
        val imageName = when (type) {
            ImageType.ITEM.type, ImageType.PROFILE.type -> {
                "$id.png"
            }

            else -> {
                if (id.contains("png")) {
                    id
                } else {
                    val season = Regex("""TFT(\d+)""").find(id.uppercase())?.groupValues?.get(1)
                    "$id.TFT_Set$season.png"
                }
            }
        }
        return "https://ddragon.leagueoflegends.com/cdn/$currentVersion/img/$type/$imageName"
    }
}

enum class ImageType(val type: String) {
    CHAMPION("tft-champion"),
    ITEM("tft-item"),

    TRAIT("tft-trait"),
    PROFILE("profileicon")
}