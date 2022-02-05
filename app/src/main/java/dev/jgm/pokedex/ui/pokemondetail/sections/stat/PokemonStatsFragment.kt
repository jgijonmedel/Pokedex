package dev.jgm.pokedex.ui.pokemondetail.sections.stat

import android.view.View
import dev.jgm.pokedex.R
import dev.jgm.pokedex.data.model.PokemonStat
import dev.jgm.pokedex.data.model.pokemon.Stat
import dev.jgm.pokedex.databinding.FragmentPokemonStatsBinding
import dev.jgm.pokedex.databinding.ItemStatListBinding
import dev.jgm.pokedex.ui.pokemondetail.sections.BaseFragmentSection
import dev.jgm.pokedex.utils.extension.formatToUppercase

class PokemonStatsFragment : BaseFragmentSection(R.layout.fragment_pokemon_stats) {

    private lateinit var binding: FragmentPokemonStatsBinding

    override fun initFragment(view: View) {
        binding = FragmentPokemonStatsBinding.bind(view)
        setUpStates()
    }

    override fun getTitleId(): Int = R.string.base_stats

    private fun setUpStates() {
        arguments?.let { args ->
            @Suppress("UNCHECKED_CAST")
            val stats: ArrayList<Stat>? = args.getSerializable("stats") as? ArrayList<Stat>?

            stats?.let { listStat ->
                PokemonStat.values().forEach { pokemonStat ->
                    val view = when(pokemonStat){
                        PokemonStat.HP -> binding.hp
                        PokemonStat.ATTACK -> binding.attack
                        PokemonStat.DEFENSE -> binding.defense
                        PokemonStat.SPECIAL_ATTACK -> binding.specialAttack
                        PokemonStat.SPECIAL_DEFENSE -> binding.specialDefense
                        PokemonStat.SPEED -> binding.speed
                        PokemonStat.ACCURACY -> binding.accuracy
                        PokemonStat.EVASION -> binding.evasion
                    }
                    listStat.setupStat(pokemonStat, view)
                }
            }
        }
    }

    private fun List<Stat>.setupStat(pokemonStat: PokemonStat, view: ItemStatListBinding) {
        val stat = this.find { it.stat.name.formatToUppercase() == pokemonStat.toString() }
        if (stat != null) {
            view.name.text = getString(pokemonStat.text)
            view.number.text = stat.base_stat.toString()
            view.bar.progress = stat.base_stat
        } else {
            view.root.visibility = View.GONE
        }
    }
}