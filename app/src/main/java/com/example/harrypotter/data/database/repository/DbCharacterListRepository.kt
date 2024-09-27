package com.example.harrypotter.data.database.repository

import com.example.harrypotter.data.database.CharacterDao
import com.example.harrypotter.domain.model.CharacterItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DbCharacterListRepository @Inject constructor(
    private val characterDao: CharacterDao
) {

    fun getCharacterList(): Flow<List<CharacterItem>> = characterDao.getAllCharacterList()
    suspend fun insertCharacterList(characterItem: List<CharacterItem>) =
        characterDao.insertCharacterList(characterItem)
}