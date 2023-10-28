package dev.vengateshm.marvelcharacterapp.view

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import dev.vengateshm.marvelcharacterapp.Destination
import dev.vengateshm.marvelcharacterapp.R

@Composable
fun CharactersBottomNav(navController: NavHostController) {
    BottomAppBar(
        actions = {
            IconButton(onClick = {
                navController.navigate(Destination.Library.route) {
                    popUpTo(Destination.Library.route) // When switched between destinations clicking again should pop up other destinations from backstack
                    launchSingleTop = true // Prevent adding same destinations in backstack
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_library),
                    contentDescription = "Library icon"
                )
            }
            IconButton(onClick = {
                navController.navigate(Destination.Collection.route) {
                    popUpTo(Destination.Collection.route)
                    launchSingleTop = true
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_collections),
                    contentDescription = "Collections icon"
                )
            }
        })
}