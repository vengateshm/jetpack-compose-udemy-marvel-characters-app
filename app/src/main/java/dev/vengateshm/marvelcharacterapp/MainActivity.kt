package dev.vengateshm.marvelcharacterapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.vengateshm.marvelcharacterapp.ui.theme.MarvelCharacterAppJetpackComposeUdemyTheme
import dev.vengateshm.marvelcharacterapp.view.CharacterDetailScreen
import dev.vengateshm.marvelcharacterapp.view.CharactersBottomNav
import dev.vengateshm.marvelcharacterapp.view.CollectionScreen
import dev.vengateshm.marvelcharacterapp.view.LibraryScreen
import dev.vengateshm.marvelcharacterapp.viewmodel.CollectionDbViewModel
import dev.vengateshm.marvelcharacterapp.viewmodel.LibraryApiViewModel

sealed class Destination(val route: String) {
    object Library : Destination("library")
    object Collection : Destination("collection")
    object CharacterDetail : Destination("character/{characterId}") {
        fun createRoute(characterId: Int?) = "character/$characterId"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val libraryApiViewModel by viewModels<LibraryApiViewModel>()
    private val collectionDbViewModel by viewModels<CollectionDbViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelCharacterAppJetpackComposeUdemyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    CharactersScaffold(
                        navController = navController,
                        libraryApiViewModel = libraryApiViewModel,
                        collectionDbViewModel = collectionDbViewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScaffold(
    navController: NavHostController,
    libraryApiViewModel: LibraryApiViewModel,
    collectionDbViewModel: CollectionDbViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            CharactersBottomNav(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Destination.Library.route
        ) {
            composable(Destination.Library.route) {
                LibraryScreen(
                    navController = navController,
                    viewModel = libraryApiViewModel,
                    paddingValues = paddingValues
                )
            }
            composable(Destination.Collection.route) {
                CollectionScreen()
            }
            composable(Destination.CharacterDetail.route) { navBackStackEntry ->
                val characterId =
                    navBackStackEntry.arguments?.getString("characterId")?.toIntOrNull()
                if (characterId == null) {
                    Toast.makeText(context, "Character id is required", Toast.LENGTH_SHORT).show()
                } else {
                    libraryApiViewModel.getSingleCharacter(characterId)
                    CharacterDetailScreen(
                        libraryApiViewModel = libraryApiViewModel,
                        collectionDbViewModel = collectionDbViewModel,
                        paddingValues = paddingValues,
                        navController = navController
                    )
                }
            }
        }
    }
}