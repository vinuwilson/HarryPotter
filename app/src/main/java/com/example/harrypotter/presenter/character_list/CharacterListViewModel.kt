package com.example.harrypotter.presenter.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.remote.dto.CharacterItem
import com.example.harrypotter.domain.usecase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    private val _characterListState = MutableStateFlow(CharacterListState())
    val characterListState = _characterListState.asStateFlow()

    init {
        getAllCharacterList()
    }

    private fun getAllCharacterList() {
        viewModelScope.launch {
            _characterListState.update {
                it.copy(
                    isLoading = true
                )
            }
        }
        viewModelScope.launch {
            _characterListState.update {
                it.copy(
                    characterList = getAllCharacters().first().getOrNull(),
                    isLoading = false
                )
            }
        }
    }

    suspend fun getAllCharacters() : Flow<Result<List<CharacterItem>>> {
        return  getAllCharactersUseCase.getAllCharacterList()
    }
}
