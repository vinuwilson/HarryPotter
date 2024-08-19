package com.example.harrypotter.presenter.character_details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterDetailsScreen(
    characterDetails: CharacterItem,
    navController: NavHostController
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = characterDetails.name ?:"",
                        fontSize = dimensionResource(id = R.dimen.extra_large_font_size).value.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_arrow_icon)
                        )
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(id = R.dimen.column_padding))
        ) {

            Spacer(modifier = Modifier.height(it.calculateTopPadding()))

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
}


