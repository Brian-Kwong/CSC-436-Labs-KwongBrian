package com.zybooks.petadoption.ui

import android.content.Context
import android.content.Intent
import com.zybooks.petadoption.data.Pet

fun shareAdoption(context: Context, pet: Pet) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Meet ${pet.name}!")
        putExtra(Intent.EXTRA_TEXT, "I've adopted ${pet.name}!")
    }

    context.startActivity(
        Intent.createChooser(intent, "Pet Adoption")
    )
}