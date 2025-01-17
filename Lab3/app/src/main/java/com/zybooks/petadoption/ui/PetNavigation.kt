package com.zybooks.petadoption.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object List

    @Serializable
    data object Detail

    @Serializable
    data object Adopt
}

@Composable
fun PetApp(
    petViewModel: PetViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.List
    ) {
        composable<Routes.List> {
            ListScreen(
                petList = petViewModel.petList,
                onImageClick = { pet ->
                    petViewModel.selectedPet = pet
                    navController.navigate(Routes.Detail)
                }
            )
        }
        composable<Routes.Detail> {
            DetailScreen(
                pet = petViewModel.selectedPet,
                onAdoptClick = {
                    navController.navigate(Routes.Adopt)
                },
            )
        }
        composable<Routes.Adopt> {
            AdoptScreen(
                pet = petViewModel.selectedPet,
            )
        }
    }
}