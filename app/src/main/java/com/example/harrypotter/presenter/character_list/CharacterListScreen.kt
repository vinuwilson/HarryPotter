package com.example.harrypotter.presenter.character_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun CharacterListScreen(
    navController: NavHostController,
    state: CharacterListState,
) {

    if (state.characterList != null)
        Text(text = state.characterList.toString())

}