package com.example.harrypotter.data.remote.repository

import android.util.Log
import com.example.harrypotter.data.database.repository.DbCharacterListRepository
import com.example.harrypotter.data.remote.dto.CharacterItem
import com.example.harrypotter.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterListRepositoryImpl @Inject constructor(
    private val service: RemoteCharacterListService,
    private val dbRepository: DbCharacterListRepository
) : CharacterListRepository {

    override suspend fun getCharacterList(): Flow<Result<List<CharacterItem>>> {
        val result = service.getCharacterList()
        if (result.first().isSuccess) {
            try {
                dbRepository.insertCharacterList(result.first().getOrNull()!!)
            } catch (exception: Exception) {
                Log.d("TAG", "getCharacterList: ${exception.message}")
            }
            return result
        } else {
            return flow {
                val dbResult = dbRepository.getCharacterList().first()
                if(dbResult.isNotEmpty())
                    emit(Result.success(dbResult))
                else
                    emit(Result.failure(RuntimeException("Character list is empty")))
            }.catch {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        }
    }
}