package dev.jgm.pokedex.ui.pokemonlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jgm.pokedex.R
import dev.jgm.pokedex.data.model.PokedexItem
import kotlin.properties.Delegates

class PokemonListAdapter(
    private val onClick: (PokedexItem) -> Unit
): RecyclerView.Adapter<PokemonListViewHolder>() {

    var animated = false

    var pokemonList: List<PokedexItem> by Delegates.observable(emptyList()) { _, old, new ->
        if(animated){
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int = old.size
                override fun getNewListSize(): Int = new.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return (old[oldItemPosition].number == new[newItemPosition].number)
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return old[oldItemPosition] == new[newItemPosition]
                }
            }).dispatchUpdatesTo(this)
        } else {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pokemon_list, parent, false)
        return PokemonListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokedexItem = pokemonList[position]
        holder.render(pokedexItem, onClick)
    }

    override fun getItemCount(): Int = pokemonList.size
}