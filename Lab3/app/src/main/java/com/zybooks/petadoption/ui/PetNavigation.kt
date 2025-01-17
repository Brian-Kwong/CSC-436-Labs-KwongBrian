package com.zybooks.petadoption.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object List

    @Serializable
    data class Detail(
        val petId: Int
    )

    @Serializable
    data class Adopt(
        val petId: Int
    )
}

@Composable
fun PetApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.List
    ) {
        composable<Routes.List> {
            ListScreen(
                onImageClick = { pet ->
                    navController.navigate(
                        Routes.Detail(pet.id)
                    )
                }
            )
        }
        composable<Routes.Detail> { backstackEntry ->
            val details: Routes.Detail = backstackEntry.toRoute()

            DetailScreen(
                petId = details.petId,
                onAdoptClick = {
                    navController.navigate(
                        Routes.Adopt(details.petId)
                    )
                },
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Adopt> { backstackEntry ->
            val adopt: Routes.Adopt = backstackEntry.toRoute()

            AdoptScreen(
                petId = adopt.petId,
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}