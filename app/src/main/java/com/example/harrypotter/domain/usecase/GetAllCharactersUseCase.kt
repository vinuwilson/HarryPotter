package com.example.harrypotter.domain.usecase

import com.example.harrypotter.domain.model.CharacterItem
import com.example.harrypotter.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharacterListRepository
) {

    suspend fun getAllCharacterList(): Flow<Result<List<CharacterItem>>> {
        return repository.getCharacterList()
    }

}