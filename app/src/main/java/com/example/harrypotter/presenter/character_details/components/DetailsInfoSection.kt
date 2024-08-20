package com.example.harrypotter.presenter.character_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.harrypotter.R
import com.example.harrypotter.data.remote.dto.CharacterItem
import com.example.harrypotter.data.remote.dto.Wand

@Composable
fun DetailsInfoSection(characterDetails: CharacterItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_view_rounded_shape))
            ),
        contentAlignment = Center
    ) {
        Column {
            DetailsRow(title = "Alive", value = characterDetails.alive)
            DetailsRow(title = "Species", value = characterDetails.species)
            DetailsRow(title = "Gender", value = characterDetails.gender)
            DetailsRow(title = "House", value = characterDetails.house)
            DetailsRow(title = "Date of birth", value = characterDetails.dateOfBirth)
            DetailsRow(title = "Year of birth", value = characterDetails.yearOfBirth)
            DetailsRow(title = "Wizard", value = characterDetails.wizard)
            DetailsRow(title = "Ancestry", value = characterDetails.ancestry)
            DetailsRow(title = "Eye colour", value = characterDetails.eyeColour)
            DetailsRow(title = "Hair colour", value = characterDetails.hairColour)
            DetailsRow(title = "Wand wood", value = characterDetails.wand?.wood)
            DetailsRow(title = "Wand core", value = characterDetails.wand?.core)
            DetailsRow(title = "Wand length", value = characterDetails.wand?.length)
            DetailsRow(title = "Patronus", value = characterDetails.patronus)
            DetailsRow(title = "Hogwarts student", value = characterDetails.hogwartsStudent)
            DetailsRow(title = "Hogwarts staff", value = characterDetails.hogwartsStaff)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsInfoSectionPreview() {
    DetailsInfoSection(
        characterDetails =
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
}