package com.isep.dailyartapp.data

import android.util.Log
import com.isep.dailyartapp.MuseumsQuery
import com.isep.dailyartapp.SearchQuery
import com.isep.dailyartapp.domain.ArtworkDTO
import com.isep.dailyartapp.domain.MuseumDTO

// Data structure in : app/build/generated/source/apollo/service/com/isep/dailyartapp/SearchQuery.kt
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
        /*picture = onNodeOeuvre?.
            fieldVisuels?.get(0)?.
            entity?.
            publicUrl,
        artistName = onNodeOeuvre?.
            fieldOeuvreAuteurs?.get(0)?.
            entity?.
            fieldAuteurAuteur?.
            entity?.
            name,*/
        museum = onNodeOeuvre?.fieldMusee?.entity?.name,
        timePeriod = onNodeOeuvre?.fieldOeuvreSiecle?.entity?.name
    )
}