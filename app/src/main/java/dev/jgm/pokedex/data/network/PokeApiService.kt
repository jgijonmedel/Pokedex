package dev.jgm.pokedex.data.network

import dev.jgm.pokedex.data.model.PokeApiResponse
import dev.jgm.pokedex.data.model.ability.Ability
import dev.jgm.pokedex.data.model.pokemon.Pokemon
import dev.jgm.pokedex.data.model.specie.Specie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): Response<Pokemon>

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokeApiResponse>

    @GET("ability/{name}")
    suspend fun getPokemonAbility(@Path("name") name: String): Response<Ability>

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecie(@Path("name") name: String): Response<Specie>
}