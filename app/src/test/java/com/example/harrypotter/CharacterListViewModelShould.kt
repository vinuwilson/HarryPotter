package com.example.harrypotter

import com.example.harrypotter.data.remote.dto.CharacterItem
import com.example.harrypotter.domain.usecase.GetAllCharactersUseCase
import com.example.harrypotter.presenter.character_list.CharacterListViewModel
import com.example.harrypotter.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class CharacterListViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: CharacterListViewModel
    private val getAllCharactersUseCase: GetAllCharactersUseCase = mock()
    private val characterItem: List<CharacterItem> = mock()
    private val expected = Result.success(characterItem)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchAllCharacterList() = runTest {

        mockSuccessfulCase()

        verify(getAllCharactersUseCase, times(1)).getAllCharacterList()
    }

    @Test
    fun `emit all the character list receive from the use case`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, viewModel.getAllCharacters().first())
    }

    @Test
    fun `emit error when receive error from use case`() = runTest {

        mockFailureCase()

        assertEquals(exception, viewModel.getAllCharacters().first().exceptionOrNull())
    }


    private suspend fun mockSuccessfulCase() {
        whenever(getAllCharactersUseCase.getAllCharacterList()).thenReturn(
            flow {
                emit(expected)
            }
        )
        viewModel = CharacterListViewModel(getAllCharactersUseCase)
    }

    private suspend fun mockFailureCase() {
        whenever(getAllCharactersUseCase.getAllCharacterList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        viewModel = CharacterListViewModel(getAllCharactersUseCase)
    }
}