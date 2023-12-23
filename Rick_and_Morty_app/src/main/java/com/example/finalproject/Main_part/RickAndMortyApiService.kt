package com.example.finalproject.Main_part

import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterListResponse

    // Add a new endpoint for character search
    @GET("character")
    suspend fun searchCharacter(@Query("name") name: String): CharacterListResponse
}
