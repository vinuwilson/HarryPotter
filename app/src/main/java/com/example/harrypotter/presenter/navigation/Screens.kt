package com.example.harrypotter.presenter.navigation

import kotlinx.serialization.Serializable

@Serializable
object CharacterList

@Serializable
data class CharacterDetails(
    val characterId : String
)

@Serializable
object SearchScreenView