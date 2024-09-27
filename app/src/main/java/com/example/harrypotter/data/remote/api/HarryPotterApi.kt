package com.example.harrypotter.data.remote.api

import com.example.harrypotter.data.remote.dto.CharacterItemDto
import retrofit2.http.GET

interface HarryPotterApi {

    @GET("characters")
    suspend fun getCharacterList(): List<CharacterItemDto>
}