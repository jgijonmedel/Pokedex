package dev.jgm.pokedex.ui.pokemondetail.sections.about

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jgm.pokedex.core.response.Response
import dev.jgm.pokedex.data.model.specie.Specie
import dev.jgm.pokedex.repository.PokemonRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonAboutViewModel: ViewModel() {

    private val repository: PokemonRepository = PokemonRepository()

    val specie = MutableLiveData<Specie?>(null)
    val isLoading = MutableLiveData<Boolean>()

    fun loadSpecie(specieName: String) {
        viewModelScope.launch {
            isLoading.value = true
            val response = repository.getPokemonSpecie(specieName)
            when (response) {
                is Response.Loading -> {
                    isLoading.value = true
                }
                is Response.Success -> {
                    specie.value = response.data
                    delay(1000)
                    isLoading.value = false
                }
                is Response.Failure -> {
                    Log.e("ABOUT_VM",response.exception.message.toString())
                    isLoading.value = false
                }
            }
        }
    }
}