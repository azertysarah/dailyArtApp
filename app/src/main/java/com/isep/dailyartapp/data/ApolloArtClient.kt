package com.isep.dailyartapp.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.network.okHttpClient
import com.isep.dailyartapp.BuildConfig
import com.isep.dailyartapp.MuseumsQuery
import com.isep.dailyartapp.SearchQuery
import com.isep.dailyartapp.domain.MuseumDTO
import com.isep.dailyartapp.domain.ArtClient
import com.isep.dailyartapp.domain.ArtworkDTO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.future
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.CompletableFuture

class ApolloArtClient: ArtClient {
    private fun buildClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BuildConfig.API_URL)
            .okHttpClient(
                OkHttpClient.Builder()
                    .addInterceptor(AuthorizationInterceptor())
                    .build()
            )
            .build()
    }

    // Get a list of all museums of the API
    fun getMuseumsAsync() : CompletableFuture<List<MuseumDTO>> = GlobalScope.future{
        getMuseums()
    }
    override suspend fun getMuseums(): List<MuseumDTO> {
        val apolloClient = buildClient()
        return apolloClient
            .query(MuseumsQuery())
            .execute()
            .data
            ?.taxonomyTermQuery?.entities
            ?.map { it!!.toMuseumDTO() } !!
    }

    // Get art work according to the user input in the search field (search on title)
    fun searchArtworkAsync(queryTitle: String, queryMuseums: List<String>, queryTimePeriods: List<String>) : CompletableFuture<List<ArtworkDTO>> = GlobalScope.future{
        searchArtwork(queryTitle, queryMuseums, queryTimePeriods)
    }

    override suspend fun searchArtwork(queryTitle: String, queryMuseums: List<String>, queryTimePeriods: List<String>): List<ArtworkDTO> {
        val apolloClient = buildClient()
        val validQueryTitle = "%$queryTitle%"

        return apolloClient
            .query(SearchQuery(validQueryTitle, queryMuseums, queryTimePeriods))
            .execute()
            .data
            ?.nodeQuery?.entities
            ?.map { it!!.toArtworkDTO() } !!
    }
}

// Attach our authentication token to requests header
private class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .apply {
                addHeader("auth-token", BuildConfig.AUTH_TOKEN)
            }
            .build()
        return chain.proceed(request)
    }
}