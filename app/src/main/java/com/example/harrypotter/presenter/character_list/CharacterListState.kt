package com.example.harrypotter.presenter.character_list

import com.example.harrypotter.domain.model.CharacterItem


data class CharacterListState(
    val isLoading: Boolean = false,
    val characterList: List<CharacterItem>? = emptyList()
)
