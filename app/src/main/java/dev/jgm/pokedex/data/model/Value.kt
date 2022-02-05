package dev.jgm.pokedex.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Value(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)
