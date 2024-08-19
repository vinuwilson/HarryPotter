package com.example.harrypotter.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.harrypotter.data.remote.dto.Wand
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun listToJsonString(value: List<String>?): String? = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String?) =
        Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromContributor(str: Wand): String {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun toContributor(str: String): Wand {
        return Gson().fromJson(str, object : TypeToken<Wand>() {}.type)
    }
}