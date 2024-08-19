package com.example.harrypotter.presenter.character_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.harrypotter.R
import com.example.harrypotter.presenter.character_list.components.SingleCharacterItem
import com.example.harrypotter.presenter.navigation.CharacterDetails
import com.example.harrypotter.presenter.navigation.SearchScreenView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    navController: NavHostController,
    state: CharacterListState,
    onSearchSelected: () -> Unit
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.default_app_padding)),
                actions = {
                    IconButton(
                        onClick = {
                            onSearchSelected()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = stringResource(id = R.string.search_icon)
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = R.dimen.extra_large_font_size).value.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        if (state.characterList != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                if (state.isLoading)
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onBackground
                    )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.characterList) { character ->
                        SingleCharacterItem(
                            characterItem = character
                        ) { characterId ->
                            navController.navigate(
                                CharacterDetails(
                                    characterId = characterId
                                )
                            )
                        }
                    }
                }
            }
        } else {
            Toast.makeText(
                context,
                "No internet connection...",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}
