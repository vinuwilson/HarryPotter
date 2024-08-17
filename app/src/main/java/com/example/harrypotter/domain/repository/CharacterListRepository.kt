package com.example.harrypotter.domain.repository

import com.example.harrypotter.domain.model.CharacterItem
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {

    suspend fun getCharacterList(): Flow<Result<List<CharacterItem>>>
}
