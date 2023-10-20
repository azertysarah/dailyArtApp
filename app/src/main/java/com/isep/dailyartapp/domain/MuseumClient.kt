package com.isep.dailyartapp.domain

interface MuseumClient {
    suspend fun getMuseums(): List<Museum>?
}