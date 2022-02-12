package dev.jgm.pokedex.ui.pokemondetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jgm.pokedex.R
import dev.jgm.pokedex.data.model.ability.Ability
import dev.jgm.pokedex.databinding.ItemAbilityListBinding
import dev.jgm.pokedex.utils.extension.removeLineBreaks
import java.util.*

class AbilitiesAdapter(private val list: List<Ability>) : RecyclerView.Adapter<AbilityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_ability_list, parent, false)
        return AbilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val ability = list[position]
        holder.render(ability)
    }

    override fun getItemCount(): Int = list.size
}

class AbilityViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemAbilityListBinding.bind(view)

    fun render(ability: Ability) {
        val language = Locale.getDefault().language

        val names = ability.names.filter { it.language.name == language }
        val texts = ability.flavor_text_entries.filter { it.language.name == language }

        binding.tvName.text = names[names.indices.random()].name
        binding.tvText.text = texts[texts.indices.random()].flavor_text.removeLineBreaks()
    }
}
