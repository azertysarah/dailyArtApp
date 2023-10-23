package com.isep.dailyartapp.domain

import com.apollographql.apollo3.api.Optional

interface ArtClient {
    suspend fun getMuseums(): List<MuseumDTO>?
    //suspend fun getUuidList(): List<UuidListDTO>?
    suspend fun searchArtwork(queryTitle: String): List<ArtworkDTO>?
    suspend fun artDetails(queryID: String): List<ArtDetailsDTO>
}