package com.example.harrypotter.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harrypotter.presenter.character_list.CharacterListScreen
import com.example.harrypotter.presenter.character_list.CharacterListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CharacterList
    ) {
        composable<CharacterList> {
            val viewModel : CharacterListViewModel = hiltViewModel()
            val character by viewModel.characterListState.collectAsStateWithLifecycle()
            CharacterListScreen(
                navController = navController,
                state = character
            )
        }
    }
}

