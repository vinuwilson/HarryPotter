package com.example.harrypotter.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.harrypotter.domain.model.CharacterItem

@Database(entities = [CharacterItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterListDatabase : RoomDatabase() {
    abstract fun characterListDao(): CharacterDao
}