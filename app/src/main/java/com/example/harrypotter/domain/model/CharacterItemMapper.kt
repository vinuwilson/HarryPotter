package com.example.harrypotter.domain.model

import com.example.harrypotter.data.remote.dto.CharacterItemDto
import com.example.harrypotter.data.remote.dto.toCharacterModel
import javax.inject.Inject

class CharacterItemMapper @Inject constructor(): Function1<List<CharacterItemDto>, List<CharacterItem>>{
    override fun invoke(characterItem: List<CharacterItemDto>): List<CharacterItem> {
        return characterItem.map {
            it.toCharacterModel()
        }
    }
}