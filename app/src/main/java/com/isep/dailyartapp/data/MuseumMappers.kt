package com.isep.dailyartapp.data

import com.isep.dailyartapp.MuseumsQuery
import com.isep.dailyartapp.domain.Museum

fun MuseumsQuery.Entity.toMuseum() : Museum {
    return Museum(
        uuid = entityUuid,
        name = entityLabel
    )
}