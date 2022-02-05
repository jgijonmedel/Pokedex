package dev.jgm.pokedex.data.model.pokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OtherSprites(
    val dream_world: Sprites,
    val home: Sprites,
    @Expose @SerializedName("official-artwork") val official_artwork: Sprites?
)