package com.example.harrypotter.presenter.character_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotter.R
import com.example.harrypotter.data.remote.dto.CharacterItem
import com.example.harrypotter.ui.theme.gryffindor
import com.example.harrypotter.ui.theme.hufflepuff
import com.example.harrypotter.ui.theme.ravenclaw
import com.example.harrypotter.ui.theme.slytherin
import com.example.harrypotter.util.HouseColor

@Composable
fun SingleCharacterItem(
    characterItem: CharacterItem,
    onItemClick:(String) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(dimensionResource(id = R.dimen.card_view_padding))
            .clickable { onItemClick(characterItem.id) },
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = R.dimen.card_view_card_elevation)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_size))
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            characterItem.image.ifEmpty { R.drawable.harry_potter }
                        )
                        .placeholder(R.drawable.harry_potter)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.character_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.default_app_padding)),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.column_space)),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.column_padding))
                    .fillMaxSize()
            ) {
                Text(
                    text = characterItem.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
                    fontWeight = FontWeight.Bold
                )

                if (characterItem.species.isNotEmpty()) {
                    Text(
                        text = characterItem.species,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = dimensionResource(id = R.dimen.medium_font_size).value.sp
                    )
                }

                if (characterItem.actor.isNotEmpty()) {
                    Text(
                        text = characterItem.actor,
                        fontStyle = FontStyle.Italic,
                        fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                        color = MaterialTheme.colorScheme.onBackground
                        )
                }

                Surface(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.small_circle_size)),
                    shape = CircleShape,
                    color = when (characterItem.house) {
                        HouseColor.Gryffindor.toString() -> gryffindor
                        HouseColor.Slytherin.toString() -> slytherin
                        HouseColor.Ravenclaw.toString() -> ravenclaw
                        HouseColor.Hufflepuff.toString() -> hufflepuff
                        else -> MaterialTheme.colorScheme.primary
                    }
                ) {}
            }
        }
    }
}