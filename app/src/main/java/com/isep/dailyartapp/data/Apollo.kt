package com.isep.dailyartapp.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.isep.dailyartapp.BuildConfig
import com.isep.dailyartapp.MuseumsQuery

class Apollo {
    val apolloClient = ApolloClient.builder()
        .serverUrl(BuildConfig.API_URL)
        .build()
}