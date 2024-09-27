package com.example.harrypotter

import com.example.harrypotter.data.remote.api.HarryPotterApi
import com.example.harrypotter.data.remote.dto.CharacterItemDto
import com.example.harrypotter.data.remote.repository.RemoteCharacterListService
import com.example.harrypotter.domain.model.CharacterItem
import com.example.harrypotter.domain.model.CharacterItemMapper
import com.example.harrypotter.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoteCharacterListServiceShould : BaseUnitTest() {

    private lateinit var characterListService: RemoteCharacterListService
    private val mapper: CharacterItemMapper = mock()
    private val api: HarryPotterApi = mock()
    private val characterItemDto: List<CharacterItemDto> = mock()
    private val characterItem: List<CharacterItem> = mock()
    private val expected = Result.success(characterItem)
    private val exception = "Something went wrong"

    @Test
    fun `fetch character list from the server`() = runTest {

        mockSuccessfulCase()

        api.getCharacterList()

        verify(api, times(1)).getCharacterList()
    }

    @Test
    fun `convert character list to flow and emit`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, characterListService.getCharacterList().first())
    }

    @Test
    fun `emit error when network fails`() = runTest {

        mockFailureCase()

        assertEquals(exception, characterListService.getCharacterList().first().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.getCharacterList()).thenReturn(characterItemDto)

        whenever(mapper.invoke(characterItemDto)).thenReturn(characterItem)

        characterListService = RemoteCharacterListService(api, mapper)
    }

    private suspend fun mockFailureCase() {
        whenever(api.getCharacterList()).thenThrow(RuntimeException("Damn backend exception"))

        characterListService = RemoteCharacterListService(api, mapper)
    }

}