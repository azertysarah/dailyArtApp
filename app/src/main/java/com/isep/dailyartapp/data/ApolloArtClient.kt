package com.isep.dailyartapp.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.isep.dailyartapp.ArtDetailsQuery
import com.isep.dailyartapp.BuildConfig
import com.isep.dailyartapp.MuseumsQuery
import com.isep.dailyartapp.SearchQuery
import com.isep.dailyartapp.UuidListQuery
import com.isep.dailyartapp.domain.MuseumDTO
import com.isep.dailyartapp.domain.ArtClient
import com.isep.dailyartapp.domain.ArtDetailsDTO
import com.isep.dailyartapp.domain.ArtworkDTO
import com.isep.dailyartapp.domain.UuidListDTO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.future
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.CompletableFuture
import kotlin.random.Random

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


    // Utilisez un tableau pour uuidList
    val uuidList = arrayOf("651c7e41-f63e-4d27-b5e8-497aaa93272f", "904978f2-31e7-464b-bb70-50520eaed9e9", "84a76517-40a4-4476-a846-d59ccb4cb8d2")

    // UUID aléatoire parmi le tableau
    val randomUUID = uuidList.random()


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

    /*fun getUuidListAsync() : CompletableFuture<List<UuidListDTO>> = GlobalScope.future{
        getUuidList()
    }
    override suspend fun getUuidList(): List<UuidListDTO> {
        val apolloClient = buildClient()
        return apolloClient
            .query(UuidListQuery())
            .execute()
            .data
            ?.nodeQuery?.entities
            ?.map { it!!.toUuidListDTO() } !!
    }*/

    // Get art work according to the user input in the search field (search on title)
    fun searchArtworkAsync(queryTitle: String) : CompletableFuture<List<ArtworkDTO>> = GlobalScope.future{
        searchArtwork(queryTitle)
    }

    override suspend fun searchArtwork(queryTitle: String): List<ArtworkDTO> {
        val apolloClient = buildClient()
        val validQueryTitle = "%$queryTitle%"

        return apolloClient
            .query(SearchQuery(validQueryTitle))
            .execute()
            .data
            ?.nodeQuery?.entities
            ?.map { it!!.toArtworkDTO() } !!
    }

    fun artDetailsAsync(queryID: String) : CompletableFuture<List<ArtDetailsDTO>> = GlobalScope.future {
        artDetails(queryID)
    }
    override suspend fun artDetails(queryID: String): List<ArtDetailsDTO> {
        val apolloClient = buildClient()
        val validQueryID = randomUUID
        return apolloClient
            .query(ArtDetailsQuery(validQueryID))
            .execute()
            .data
            ?.nodeQuery?.entities
            ?.map { it!!.toArtDetailsDTO() } !!
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