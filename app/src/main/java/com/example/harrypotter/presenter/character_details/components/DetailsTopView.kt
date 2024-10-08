package com.example.harrypotter.presenter.character_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotter.R
import com.example.harrypotter.data.remote.dto.Wand
import com.example.harrypotter.domain.model.CharacterItem
import com.example.harrypotter.util.formatDate

@Composable
fun DetailsTopView(
    characterDetails: CharacterItem
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_view_rounded_shape))
            ),
        contentAlignment = Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.column_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        characterDetails.image?.ifEmpty { R.drawable.harry_potter }
                    )
                    .placeholder(R.drawable.harry_potter)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.harry_potter),
                contentDescription = stringResource(R.string.character_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.details_view_image_size))
                    .padding(dimensionResource(id = R.dimen.default_app_padding)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = characterDetails.name?:"",
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = characterDetails.species?:"",
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.default_app_padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Left,
                    text = characterDetails.actor?:"",
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Right,
                    text = if (!characterDetails.dateOfBirth.isNullOrEmpty()) formatDate(characterDetails.dateOfBirth) else "",
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsTopViewPreview() {

    DetailsTopView(
        characterDetails =
        CharacterItem(
            actor = "Daniel Radcliffe",
            alive = true,
            alternateActors = emptyList(),
            alternateNames = emptyList(),
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