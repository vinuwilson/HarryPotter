package com.example.harrypotter.data.remote.repository

import com.example.harrypotter.domain.model.CharacterItem
import com.example.harrypotter.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterListRepositoryImpl @Inject constructor(
    private val service: CharacterListService
) : CharacterListRepository {

    override suspend fun getCharacterList(): Flow<Result<List<CharacterItem>>> {
        return service.getCharacterList()
    }
}