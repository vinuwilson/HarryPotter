package com.example.harrypotter.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.harrypotter.presenter.character_details.CharacterDetailsScreen
import com.example.harrypotter.presenter.character_list.CharacterListScreen
import com.example.harrypotter.presenter.character_list.CharacterListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CharacterList
    ) {
        composable<CharacterList> { entry ->
            val viewModel =
                entry.sharedViewModel<CharacterListViewModel>(navController = navController)
            val character by viewModel.characterListState.collectAsStateWithLifecycle()
            CharacterListScreen(
                navController = navController,
                state = character
            )
        }

        composable<CharacterDetails> { entry ->
            val viewModel =
                entry.sharedViewModel<CharacterListViewModel>(navController = navController)
            val state by viewModel.characterListState.collectAsStateWithLifecycle()

            val args = entry.toRoute<CharacterDetails>()
            val characterDetails = state.characterList?.find {
                it.id == args.characterId
            }
            if (characterDetails != null)
                CharacterDetailsScreen(
                    characterDetails = characterDetails,
                navController = navController
            )
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}