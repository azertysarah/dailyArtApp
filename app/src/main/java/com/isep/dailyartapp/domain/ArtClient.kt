package com.isep.dailyartapp.domain

import com.apollographql.apollo3.api.Optional

interface ArtClient {
    suspend fun getMuseums(): List<MuseumDTO>?
    suspend fun searchArtwork(queryTitle: String, queryMuseum: String, queryTimePeriod: String): List<ArtworkDTO>?

}