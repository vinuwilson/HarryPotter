package com.example.harrypotter.data.remote.repository

import com.example.harrypotter.domain.model.CharacterItem
import kotlinx.coroutines.flow.Flow

class CharacterListService {

    suspend fun getCharacterList(): Flow<Result<List<CharacterItem>>> {
        TODO("Not yet implemented")
    }
}