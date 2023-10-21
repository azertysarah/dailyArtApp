package com.isep.dailyartapp.data

import com.isep.dailyartapp.MuseumsQuery
import com.isep.dailyartapp.SearchQuery
import com.isep.dailyartapp.domain.ArtworkDTO
import com.isep.dailyartapp.domain.MuseumDTO

fun MuseumsQuery.Entity.toMuseumDTO() : MuseumDTO {
    return MuseumDTO(
        uuid = entityUuid,
        name = entityLabel
    )
}

fun SearchQuery.Entity.toArtworkDTO() : ArtworkDTO {
    return ArtworkDTO(
        uuid = entityUuid,
        name = entityLabel,
        //picture = ???
    )
}