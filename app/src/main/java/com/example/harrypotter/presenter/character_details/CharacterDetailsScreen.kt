package com.example.harrypotter.presenter.character_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.harrypotter.R
import com.example.harrypotter.data.remote.dto.CharacterItem
import com.example.harrypotter.presenter.character_details.components.DetailsInfoSection
import com.example.harrypotter.presenter.character_details.components.DetailsTopView

@Composable
fun CharacterDetailsScreen(
    characterDetails: CharacterItem,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(id = R.dimen.column_padding))
    ) {

        DetailsTopView(characterDetails = characterDetails)

        Text(
            text = stringResource(id = R.string.more_info),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.default_app_padding))
        )

        DetailsInfoSection(characterDetails = characterDetails)
    }
}


