package com.example.harrypotter.presenter.character_list

import com.example.harrypotter.data.remote.dto.CharacterItem


data class CharacterListState(
    val isLoading: Boolean = false,
    val characterList: List<CharacterItem>? = emptyList()
)
