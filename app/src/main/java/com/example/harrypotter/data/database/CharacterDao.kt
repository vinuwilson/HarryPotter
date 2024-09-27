package com.example.harrypotter.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harrypotter.domain.model.CharacterItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterList(characterEntity: List<CharacterItem>)

    @Query("SELECT * from character_tbl")
    fun getAllCharacterList(): Flow<List<CharacterItem>>

}