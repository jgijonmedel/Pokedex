package dev.jgm.pokedex.ui.pokemondetail.sections.stat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jgm.pokedex.core.response.Response
import dev.jgm.pokedex.data.model.ability.Ability
import dev.jgm.pokedex.data.model.pokemon.AbilityValue
import dev.jgm.pokedex.repository.PokemonRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonStatViewModel: ViewModel() {

    private val repository: PokemonRepository = PokemonRepository()

    val abilities = MutableLiveData<List<Ability>>(emptyList())
    val isLoading = MutableLiveData<Boolean>()
    // var started = false
    val isError = MutableLiveData<Boolean>()


    fun loadAbilities(list: List<AbilityValue>) {
        viewModelScope.launch {
            isLoading.value = true
            val response = repository.getPokemonAbility(list)
            when (response) {
                is Response.Loading -> isLoading.value = true
                is Response.Success -> {
                    abilities.value = response.data ?: emptyList()
                    delay(1000)
                    isLoading.value = false
                }
                is Response.Failure -> {
                    isError.value = true
                    isLoading.value = false
                }
            }
        }
    }
}