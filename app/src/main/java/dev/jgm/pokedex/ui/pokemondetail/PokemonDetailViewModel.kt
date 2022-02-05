package dev.jgm.pokedex.ui.pokemondetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jgm.pokedex.core.response.Response
import dev.jgm.pokedex.data.model.pokemon.Pokemon
import dev.jgm.pokedex.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonDetailViewModel: ViewModel() {

    private val repository: PokemonRepository = PokemonRepository()

    val pokemon = MutableLiveData<Pokemon?>(null)
    val isLoading = MutableLiveData<Boolean>()


    fun loadPokemon(pokemonName: String) {
        viewModelScope.launch {
            isLoading.value = true
            val response = repository.getPokemonDetail(pokemonName)
            when (response) {
                is Response.Loading -> {
                    isLoading.value = true
                }
                is Response.Success -> {
                    pokemon.value = response.data
                    isLoading.value = false
                }
                is Response.Failure -> {
                    Log.e("DETAIL_VM",response.exception.message.toString())
                    isLoading.value = false
                }
            }
        }
    }

}