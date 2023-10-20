package com.isep.dailyartapp.data

import com.apollographql.apollo3.ApolloClient
import com.isep.dailyartapp.BuildConfig
import com.isep.dailyartapp.MuseumsQuery
import com.isep.dailyartapp.domain.Museum
import com.isep.dailyartapp.domain.MuseumClient

class ApolloMuseumClient(): MuseumClient {
    override suspend fun getMuseums(): List<Museum> {
        val apolloClient = ApolloClient.Builder()
            .serverUrl(BuildConfig.API_URL)
            .build()

        return apolloClient
            .query(MuseumsQuery())
            .execute()
            .data
            ?.taxonomyTermQuery?.entities
            ?.map { it.toMuseum() } !!
    }
}