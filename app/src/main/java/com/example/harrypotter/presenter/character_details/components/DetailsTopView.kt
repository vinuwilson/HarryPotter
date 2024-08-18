package com.example.harrypotter.presenter.character_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotter.R
import com.example.harrypotter.data.remote.dto.CharacterItem
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
                        characterDetails.image.ifEmpty { R.drawable.harry_potter }
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

            Text(
                text = characterDetails.name,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.default_app_padding)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = characterDetails.actor,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp
                )

                Text(
                    text = characterDetails.species,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp
                )

                Text(
                    text = if (!characterDetails.dateOfBirth.isNullOrEmpty()) formatDate(characterDetails.dateOfBirth) else "",
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp
                )
            }
        }
    }
}
