package com.isep.dailyartapp.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.isep.dailyartapp.BuildConfig
import com.isep.dailyartapp.MuseumsQuery
import com.isep.dailyartapp.domain.Museum
import com.isep.dailyartapp.domain.MuseumClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.future
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.CompletableFuture

class ApolloMuseumClient: MuseumClient {
    fun doSomethingAsync() : CompletableFuture<List<Museum>> = GlobalScope.future{
        getMuseums()
    }
    override suspend fun getMuseums(): List<Museum> {
        val apolloClient = ApolloClient.Builder()
            .serverUrl(BuildConfig.API_URL)
            .okHttpClient(
                OkHttpClient.Builder()
                    .addInterceptor(AuthorizationInterceptor())
                    .build()
            )
            .build()

        return apolloClient
            .query(MuseumsQuery())
            .execute()
            .data
            ?.taxonomyTermQuery?.entities
            ?.map { it!!.toMuseum() } !!
    }
}

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