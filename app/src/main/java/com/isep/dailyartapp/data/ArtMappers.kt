package com.isep.dailyartapp.data

import com.isep.dailyartapp.ArtDetailsQuery
import com.isep.dailyartapp.MuseumsQuery
import com.isep.dailyartapp.SearchQuery
import com.isep.dailyartapp.UuidListQuery
import com.isep.dailyartapp.domain.ArtDetailsDTO
import com.isep.dailyartapp.domain.ArtworkDTO
import com.isep.dailyartapp.domain.MuseumDTO
import com.isep.dailyartapp.domain.UuidListDTO

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

/*fun UuidListQuery.Entity.toUuidListDTO() : UuidListDTO {
    return UuidListDTO(
        uuid = entityUuid
    )
}*/

fun ArtDetailsQuery.Entity.toArtDetailsDTO() : ArtDetailsDTO {
    return ArtDetailsDTO(
        uuid = entityUuid,
        title = onNodeOeuvre?.title,
        artist = onNodeOeuvre?.fieldOeuvreAuteurs?.firstOrNull()?.entity?.fieldAuteurAuteur?.entity?.name,
        movment = onNodeOeuvre?.fieldDateProduction?.century,
        museum = onNodeOeuvre?.fieldMusee?.entity?.name,
        description = onNodeOeuvre?.fieldOeuvreDescriptionIcono?.value,
        picture = onNodeOeuvre?.fieldVisuels?.firstOrNull()?.entity?.publicUrl
    )
}