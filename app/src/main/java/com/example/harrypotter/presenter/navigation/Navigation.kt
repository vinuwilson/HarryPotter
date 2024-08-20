package com.example.harrypotter.presenter.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.harrypotter.presenter.search.SearchScreen

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
            ) {
                navController.navigate(SearchScreenView)
            }
        }

        composable<CharacterDetails>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }
        ) { entry ->
            val viewModel =
                entry.sharedViewModel<CharacterListViewModel>(navController = navController)
            val state by viewModel.characterListState.collectAsStateWithLifecycle()

            val args = entry.toRoute<CharacterDetails>()
            LaunchedEffect(state) {
                viewModel.getSingleCharacterDetails(args.characterId)
            }

            CharacterDetailsScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable<SearchScreenView>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }
        ) { entry ->
            val viewModel =
                entry.sharedViewModel<CharacterListViewModel>(navController = navController)
            val state by viewModel.characterListState.collectAsStateWithLifecycle()

            SearchScreen(
                state = state,
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