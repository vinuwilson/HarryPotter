package com.example.harrypotter.presenter.character_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.harrypotter.presenter.character_list.components.SingleCharacterItem
import com.example.harrypotter.presenter.navigation.CharacterDetails

@Composable
fun CharacterListScreen(
    navController: NavHostController,
    state: CharacterListState,
) {
    val context = LocalContext.current
    if (state.characterList != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading)
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onBackground
                )
            
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.characterList) {character ->
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
