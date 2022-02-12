package dev.jgm.pokedex.ui.pokemonlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jgm.pokedex.core.response.Response
import dev.jgm.pokedex.data.model.PokeApiResponse
import dev.jgm.pokedex.data.model.PokedexItem
import dev.jgm.pokedex.repository.PokemonRepository
import dev.jgm.pokedex.utils.Constants.URL_DEFAULT_IMAGE
import dev.jgm.pokedex.utils.OrderPokedexValue
import dev.jgm.pokedex.utils.extension.getIdFromUrl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    private val repository: PokemonRepository = PokemonRepository()

    private var cacheList: List<PokedexItem> = emptyList()
    val pokedexList = MutableLiveData<List<PokedexItem>>(emptyList())
    val isLoading = MutableLiveData<Boolean>()
    // var started = false
    val isError = MutableLiveData<Boolean>()

    init {
        loadPokedex()
    }

    fun loadPokedex() {
        viewModelScope.launch {
            isLoading.value = true
            cacheList = emptyList()
            val response = repository.getPokemonLis(1119, 0)
            when (response) {
                is Response.Loading -> isLoading.value = true
                is Response.Success -> {
                    onSuccessResponse(response.data)
                    delay(1000)
                    isLoading.value = false
                    // started = true
                }
                is Response.Failure -> {
                    isError.value = true
                    isLoading.value = false
                }
            }
        }
    }

    fun searchPokemon(query: String) {
        viewModelScope.launch {
            if (query.isNotEmpty()) {
                val result = cacheList.filter {
                    it.pokemonName.contains(query.trim(), true) ||
                            it.number.toString().contains(query.trim(), true)
                }
                pokedexList.value = result
            } else {
                pokedexList.value = cacheList
            }
        }
    }

    fun sortedBy(field: OrderPokedexValue, isAscending: Boolean) {
        viewModelScope.launch {
            if(!pokedexList.value.isNullOrEmpty()) {
                val orderedList = when (field) {
                    OrderPokedexValue.NAME -> pokedexList.value!!.sortedBy { it.pokemonName }
                    OrderPokedexValue.NUMBER -> pokedexList.value!!.sortedBy { it.number }
                }
                pokedexList.value = if (isAscending) orderedList else orderedList.reversed()
            }
        }
    }

    private fun onSuccessResponse(response: PokeApiResponse){
        val data = response.results ?: emptyList()
        data.map { pokeResult ->
            val number = pokeResult.url.getIdFromUrl()
            val pokedexItem = PokedexItem(
                pokeResult.name,
                URL_DEFAULT_IMAGE.plus("${number}.png"),
                number.toInt()
            )
            cacheList = cacheList + pokedexItem
        }
        pokedexList.value = cacheList
    }
}