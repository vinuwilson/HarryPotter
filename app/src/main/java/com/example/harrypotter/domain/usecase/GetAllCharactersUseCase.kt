package com.example.harrypotter.domain.usecase

import com.example.harrypotter.domain.model.CharacterItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllCharactersUseCase {

    suspend fun getAllCharacterList() : Flow<Result<List<CharacterItem>>> {
        return flow {  }
    }

}