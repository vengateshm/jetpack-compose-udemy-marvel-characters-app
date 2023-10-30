package dev.vengateshm.marvelcharacterapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.vengateshm.marvelcharacterapp.Destination
import dev.vengateshm.marvelcharacterapp.R

@Composable
fun CharactersBottomNav(navController: NavHostController) {
    BottomAppBar(
        actions = {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                modifier = Modifier.clickable {
                    navController.navigate(Destination.Library.route) {
                        popUpTo(Destination.Library.route) // When switched between destinations clicking again should pop up other destinations from backstack
                        launchSingleTop = true // Prevent adding same destinations in backstack
                    }
                },
                painter = painterResource(id = R.drawable.ic_library),
                contentDescription = "Library icon"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                modifier = Modifier.clickable {
                    navController.navigate(Destination.Collection.route) {
                        popUpTo(Destination.Collection.route)
                        launchSingleTop = true
                    }
                },
                painter = painterResource(id = R.drawable.ic_collections),
                contentDescription = "Collections icon"
            )
        })
}