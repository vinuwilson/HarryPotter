package com.example.harrypotter

import com.example.harrypotter.data.remote.dto.CharacterItem
import com.example.harrypotter.domain.repository.CharacterListRepository
import com.example.harrypotter.domain.usecase.GetAllCharactersUseCase
import com.example.harrypotter.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock

class GetAllCharactersUseCaseShould : BaseUnitTest() {

    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase
    private val repository: CharacterListRepository = mock()
    private val characterItem: List<CharacterItem> = mock()
    private val expected = Result.success(characterItem)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `fetch all the character list from repository`() = runTest {

        mockSuccessfulCase()

        getAllCharactersUseCase.getAllCharacterList()

        verify(repository, times(1)).getCharacterList()
    }

    @Test
    fun `emit all the character list receive from repository`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, getAllCharactersUseCase.getAllCharacterList().first())
    }


    @Test
    fun `emit error when receive error from repository`() = runTest {

        mockFailureCase()

        assertEquals(exception, getAllCharactersUseCase.getAllCharacterList().first().exceptionOrNull())
    }


    private suspend fun mockSuccessfulCase() {
        whenever(repository.getCharacterList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        getAllCharactersUseCase = GetAllCharactersUseCase(repository)
    }

    private suspend fun mockFailureCase() {
        whenever(repository.getCharacterList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        getAllCharactersUseCase = GetAllCharactersUseCase(repository)
    }
}