package com.example.harrypotter

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.harrypotter.data.database.CharacterDao
import com.example.harrypotter.data.database.CharacterListDatabase
import com.example.harrypotter.data.database.Converters
import com.example.harrypotter.data.remote.dto.CharacterItem
import com.example.harrypotter.data.remote.dto.Wand
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CharactersDatabaseTest {

    private lateinit var dao: CharacterDao
    private lateinit var characterListDatabase: CharacterListDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val converters = Converters()

        characterListDatabase = Room.inMemoryDatabaseBuilder(
            context, CharacterListDatabase::class.java
        )
            .addTypeConverter(converters)
            .build()

        dao = characterListDatabase.characterListDao()
    }

    @Test
    fun writeAndReadCharacterDetails() = runTest {
        dao.insertCharacterList(
            listOf(
                CharacterItem(
                    actor = "Daniel Radcliffe",
                    alive = true,
                    alternate_actors = emptyList(),
                    alternate_names = emptyList(),
                    ancestry = "half-blood",
                    dateOfBirth = "31-07-1980",
                    eyeColour = "white",
                    gender = "male",
                    hairColour = "black",
                    hogwartsStaff = false,
                    hogwartsStudent = true,
                    house = "Gryffindor",
                    id = "1",
                    image = "https://ik.imagekit.io/hpapi/harry.jpg",
                    name = "Harry Potter",
                    patronus = "stag",
                    species = "human",
                    wand = Wand("phoenix tail feather", 11.0, "holly"),
                    wizard = true,
                    yearOfBirth = 1980
                )
            )
        )
        assertEquals(1, dao.getAllCharacterList().first().size)
    }
}