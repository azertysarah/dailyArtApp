package com.isep.dailyartapp.data

import com.isep.dailyartapp.ArtDetailsQuery
import com.isep.dailyartapp.MuseumsQuery
import com.isep.dailyartapp.SearchQuery
import com.isep.dailyartapp.adapter.ArtDetailsQuery_ResponseAdapter
import com.isep.dailyartapp.domain.ArtDetailsDTO
import com.isep.dailyartapp.domain.ArtworkDTO
import com.isep.dailyartapp.domain.MuseumDTO
import com.isep.dailyartapp.selections.ArtDetailsQuerySelections

fun MuseumsQuery.Entity.toMuseumDTO() : MuseumDTO {
    return MuseumDTO(
        uuid = entityUuid,
        name = entityLabel
    )
}

fun SearchQuery.Entity.toArtworkDTO() : ArtworkDTO {
    val oeuvre = this as? ArtDetailsQuery.OnNodeOeuvre
    return ArtworkDTO(
        uuid = entityUuid,
        name = entityLabel,
        artistName = oeuvre?.fieldOeuvreAuteurs?.get(0)?.entity?.fieldAuteurAuteur?.entity?.name
    )
}

fun ArtDetailsQuery.Entity.toArtDetailsDTO() : ArtDetailsDTO {
    val oeuvre = this as? ArtDetailsQuery.OnNodeOeuvre
    return ArtDetailsDTO(
        uuid = entityUuid,
        title = oeuvre?.title,
        artist = oeuvre?.fieldOeuvreAuteurs?.firstOrNull()?.entity?.fieldAuteurAuteur?.entity?.name,
        movment = oeuvre?.fieldDateProduction?.century,
        museum = oeuvre?.fieldMusee?.entity?.name,
        description = oeuvre?.fieldOeuvreDescriptionIcono?.value,
        picture = oeuvre?.fieldVisuels?.firstOrNull()?.entity?.publicUrl
    )
}