package dev.jgm.pokedex.ui.pokemonlist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dev.jgm.pokedex.utils.extension.loadImage
import dev.jgm.pokedex.data.model.PokedexItem
import dev.jgm.pokedex.databinding.ItemPokemonListBinding
import dev.jgm.pokedex.utils.extension.formatDisplay

class PokemonListViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemPokemonListBinding.bind(view)

    fun render(pokedexItem: PokedexItem, onClick: (PokedexItem) -> Unit) {
        binding.image.loadImage(pokedexItem.imageUrl)
        binding.name.text = pokedexItem.pokemonName
        binding.number.text = pokedexItem.number.formatDisplay()
        binding.root.setOnClickListener { onClick(pokedexItem) }
    }
}