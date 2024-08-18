package com.example.harrypotter.data.remote.repository

import com.example.harrypotter.data.remote.api.HarryPotterApi
import com.example.harrypotter.data.remote.dto.CharacterItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteCharacterListService @Inject constructor(
    private val api: HarryPotterApi,
) {

    suspend fun getCharacterList(): Flow<Result<List<CharacterItem>>> {
        return flow {
            emit(Result.success(api.getCharacterList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}