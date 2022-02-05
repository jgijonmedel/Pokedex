package dev.jgm.pokedex.ui.pokemondetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jgm.pokedex.R
import dev.jgm.pokedex.data.model.specie.Variety
import dev.jgm.pokedex.databinding.ItemPokemonListBinding
import dev.jgm.pokedex.utils.Constants
import dev.jgm.pokedex.utils.extension.formatDisplay
import dev.jgm.pokedex.utils.extension.getIdFromUrl
import dev.jgm.pokedex.utils.extension.loadImage

class VarietiesAdapter(private val varietyList: List<Variety>) : RecyclerView.Adapter<VarietiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VarietiesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pokemon_list, parent, false)
        return VarietiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: VarietiesViewHolder, position: Int) {
        val variety = varietyList[position]
        holder.render(variety)
    }

    override fun getItemCount(): Int = varietyList.size
}

class VarietiesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonListBinding.bind(view)

    fun render(variety: Variety) {
        val id = variety.pokemon.url.getIdFromUrl()
        binding.image.loadImage(Constants.URL_DEFAULT_IMAGE.plus("$id.png"))
        binding.name.text = variety.pokemon.name
        binding.number.text = id.formatDisplay()
    }
}
