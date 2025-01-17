package com.zybooks.petadoption.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zybooks.petadoption.data.PetDataSource
import com.zybooks.petadoption.ui.theme.PetAdoptionTheme

@Composable
fun AdoptScreen(
    petId: Int,
    modifier: Modifier = Modifier,
    viewModel: AdoptViewModel = viewModel(),
    onUpClick: () -> Unit = { }
) {
    val pet = viewModel.getPet(petId)
    Scaffold(
        topBar = {
            PetAppBar(
                title = "Thank You!",
                canNavigateBack = true,
                onUpClick = onUpClick
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
        AdoptScreen(pet.id)
    }
}
