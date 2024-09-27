package com.example.harrypotter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harrypotter.data.remote.dto.Wand

@Entity(tableName = "character_tbl")
data class CharacterItem(
    val actor: String?,
    val alive: Boolean?,
    val alternateActors: List<String>?,
    val alternateNames: List<String>?,
    val ancestry: String?,
    val dateOfBirth: String?,
    val eyeColour: String?,
    val gender: String?,
    val hairColour: String?,
    val hogwartsStaff: Boolean?,
    val hogwartsStudent: Boolean?,
    val house: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String?,
    val name: String?,
    val patronus: String?,
    val species: String?,
    val wand: Wand?,
    val wizard: Boolean?,
    val yearOfBirth: Int?
)