package com.example.harrypotter

import com.example.harrypotter.data.remote.repository.CharacterListRepositoryImpl
import com.example.harrypotter.data.remote.repository.CharacterListService
import com.example.harrypotter.domain.model.CharacterItem
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

class CharacterListRepositoryShould : BaseUnitTest() {

    private lateinit var characterListRepository: CharacterListRepositoryImpl
    private val service: CharacterListService = mock()
    private val characterItem: List<CharacterItem> = mock()
    private val expected = Result.success(characterItem)
    private val exception = RuntimeException("Something went wrong")


    @Test
    fun `fetch all the character list from service`() = runTest {

        mockSuccessfulCase()

        characterListRepository.getCharacterList()

        verify(service, times(1)).getCharacterList()
    }

    @Test
    fun `emit all the character list from service`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, characterListRepository.getCharacterList().first())
    }

    @Test
    fun `emit error when receive error from service`() = runTest {

        mockFailureCase()

        assertEquals(exception, characterListRepository.getCharacterList().first().exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.getCharacterList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        characterListRepository = CharacterListRepositoryImpl(service)
    }

    private suspend fun mockFailureCase() {
        whenever(service.getCharacterList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        characterListRepository = CharacterListRepositoryImpl(service)
    }
}