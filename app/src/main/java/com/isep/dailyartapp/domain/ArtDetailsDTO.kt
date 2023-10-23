package com.isep.dailyartapp.domain

import android.media.Image

data class ArtDetailsDTO(
    val uuid: String?,
    val title: String?,
    val artist: String?,
    val movment: String?,
    val museum: String?,
    val description: String?,
    val picture: String?
)