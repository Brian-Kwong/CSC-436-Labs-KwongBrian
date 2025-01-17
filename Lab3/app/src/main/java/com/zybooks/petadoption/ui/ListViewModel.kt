package com.zybooks.petadoption.ui


import androidx.lifecycle.ViewModel
import com.zybooks.petadoption.data.PetDataSource

class ListViewModel {
        val petList = PetDataSource().loadPets()
}