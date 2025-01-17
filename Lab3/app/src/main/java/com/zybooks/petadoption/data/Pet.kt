package com.zybooks.petadoption.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zybooks.petadoption.ui.PetAppBar
import com.zybooks.petadoption.ui.theme.PetAdoptionTheme

enum class PetType {
   DOG, CAT, OTHER
}

enum class PetGender {
   MALE, FEMALE
}

data class Pet (
   val id: Int = 0,
   val type: PetType = PetType.DOG,
   val name: String = "",
   val gender: PetGender = PetGender.FEMALE,
   val age: Int = 0,
   val description: String = "",
   val imageId: Int = 0
)


@Preview
@Composable
fun PreviewListScreen() {
   PetAdoptionTheme {
      ListScreen(
         petList = PetDataSource().loadPets(),
         onImageClick = { }
      )
   }
}

@Composable
fun ListScreen(
   petList: List<Pet>,
   onImageClick: (Pet) -> Unit,
   modifier: Modifier = Modifier
) {
   Scaffold(
      topBar = {
         PetAppBar(
            title = "Find a Friend"
         )
      }
   ) { innerPadding ->
      LazyVerticalGrid(
         columns = GridCells.Adaptive(minSize = 128.dp),
         contentPadding = PaddingValues(0.dp),
         modifier = modifier.padding(innerPadding)
      ) {
         items(petList) { pet ->
            Image(
               painter = painterResource(id = pet.imageId),
               contentDescription = "${pet.type} ${pet.gender}",
               modifier = Modifier.clickable(
                  onClick = { onImageClick(pet) },
                  onClickLabel = "Select the pet"
               )
            )
         }
      }
   }
}

@Composable
fun DetailScreen(
   pet: Pet,
   onAdoptClick: () -> Unit,
   modifier: Modifier = Modifier,
   onUpClick: () -> Unit = { }
) {
   val gender = if (pet.gender == PetGender.MALE) "Male" else "Female"

   Scaffold(
      topBar = {
         PetAppBar(
            title = "Details",
         )
      }
   ) { innerPadding ->
      Column(
         modifier = modifier.padding(innerPadding)
      ) {
         Image(
            painter = painterResource(pet.imageId),
            contentDescription = pet.name,
            contentScale = ContentScale.FillWidth
         )
         Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = modifier.padding(6.dp)
         ) {
            Row(
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,
               modifier = modifier.fillMaxWidth()
            ) {
               Text(
                  text = pet.name,
                  style = MaterialTheme.typography.headlineMedium
               )
               Button (onClick = onAdoptClick) {
                  Text("Adopt Me!")
               }
            }
            Text(
               text = "Gender: $gender",
               style = MaterialTheme.typography.bodyLarge
            )
            Text(
               text = "Age: ${pet.age}",
               style = MaterialTheme.typography.bodyLarge
            )
            Text(
               text = pet.description,
               style = MaterialTheme.typography.bodyMedium
            )
         }
      }
   }
}

@Preview
@Composable
fun PreviewDetailScreen() {
   val pet = PetDataSource().loadPets()[0]
   PetAdoptionTheme {
      DetailScreen(
         pet = pet,
         onAdoptClick = { }
      )
   }
}

@Composable
fun AdoptScreen(
   pet: Pet,
   modifier: Modifier = Modifier,
   onUpClick: () -> Unit = { }
) {
   Scaffold(
      topBar = {
         PetAppBar(
            title = "Thank You!",
         )
      }
   ) { innerPadding ->
      Column(
         modifier = modifier.padding(innerPadding)
      ) {
         Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
               painter = painterResource(pet.imageId),
               contentDescription = pet.name,
               modifier = modifier.size(150.dp)
            )
            Text(
               text = "Thank you for adopting ${pet.name}!",
               modifier = modifier.padding(horizontal = 28.dp),
               textAlign = TextAlign.Center,
               style = MaterialTheme.typography.headlineLarge,
            )
         }
         Text(
            text = "Please pick up your new family member during business hours.",
            modifier = modifier.padding(6.dp),
         )
         Button(
            onClick = { },
            modifier = modifier.padding(6.dp)
         ) {
            Icon(Icons.Default.Share, null)
            Text("Share", modifier = modifier.padding(start = 8.dp))
         }
      }
   }
}

@Preview
@Composable
fun PreviewAdoptScreen() {
   val pet = PetDataSource().loadPets()[0]
   PetAdoptionTheme {
      AdoptScreen(pet)
   }
}
