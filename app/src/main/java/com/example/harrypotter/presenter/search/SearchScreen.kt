package com.example.harrypotter.presenter.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.harrypotter.R
import com.example.harrypotter.presenter.character_list.CharacterListState
import com.example.harrypotter.presenter.character_list.CharacterListViewModel
import com.example.harrypotter.presenter.character_list.components.SingleCharacterItem
import com.example.harrypotter.presenter.navigation.CharacterDetails
import com.example.harrypotter.presenter.search.components.SearchField


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    state: CharacterListState,
    viewModel: CharacterListViewModel = hiltViewModel(),
    navController: NavHostController
) {

    var value by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchField { searchValue ->
                        value = searchValue
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = stringResource(id = R.string.search_close_icon)
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {

            if (state.characterList != null && value != "") {
                val filteredList = viewModel.searchCharacterList(value)

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Spacer(modifier = Modifier.height(it.calculateTopPadding()))
                    }
                    items(filteredList) { character ->
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
        }
    }
}

