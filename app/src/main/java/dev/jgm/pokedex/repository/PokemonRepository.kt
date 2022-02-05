package dev.jgm.pokedex.repository

import dev.jgm.pokedex.core.RetrofitHelper
import dev.jgm.pokedex.core.response.Response
import dev.jgm.pokedex.data.model.PokeApiResponse
import dev.jgm.pokedex.data.model.pokemon.Pokemon
import dev.jgm.pokedex.data.model.specie.Specie
import dev.jgm.pokedex.data.network.PokeApiService
import java.lang.Exception

class PokemonRepository(
    private val api: PokeApiService = RetrofitHelper.getRetrofit().create(PokeApiService::class.java)
    ) {

    /*
    suspend fun getVarietyList(limit: Int, offset: Int): Response<PokeApiResponse> {
        val response = try {
            api.getVarietyList(limit, offset)
        } catch(e: Exception) {
            return Response.Failure(e)
        }
        if (response.isSuccessful){
            val data = response.body()
            return if ( data != null) {
                Response.Success(data)
            } else {
                Response.Failure(Exception("No datos en la lista"))
            }
        } else {
            return Response.Failure(Exception("La llamada fallo"))
        }
    }
    */

    suspend fun getPokemonLis(limit:Int, offset: Int): Response<PokeApiResponse> {
        return try {
            val call = api.getPokemonList(limit, offset)
            val response: PokeApiResponse? = call.body()
            if (response != null) {
                Response.Success(response)
            } else {
                Response.Failure(Exception("La lista esta vacia"))
            }
        } catch (ex: Exception) {
            Response.Failure(ex)
        }
    }


    suspend fun getPokemonDetail(name: String): Response<Pokemon> {
        return try {
            val call = api.getPokemonDetail(name)
            val response: Pokemon? = call.body()
            if (response != null) {
                Response.Success(response)
            } else {
                Response.Failure(Exception("Ocurrio un error al buscar el pokemon"))
            }
        } catch (ex: Exception) {
            Response.Failure(ex)
        }
    }

    suspend fun getPokemonSpecie(name: String): Response<Specie> {
        return try {
            val call = api.getPokemonSpecie(name)
            val response: Specie? = call.body()
            if (response != null) {
                Response.Success(response)
            } else {
                Response.Failure(Exception("Ocurrio un error al buscar el pokemon"))
            }
        } catch (ex: Exception) {
            Response.Failure(ex)
        }
    }
}

/*
    Manejo de ERRORES

    https://programmerclick.com/article/66871206516/

 */