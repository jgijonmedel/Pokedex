package dev.jgm.pokedex.ui.pokemondetail.sections.stat

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.jgm.pokedex.R
import dev.jgm.pokedex.data.model.PokemonStat
import dev.jgm.pokedex.data.model.pokemon.AbilityValue
import dev.jgm.pokedex.data.model.pokemon.Stat
import dev.jgm.pokedex.databinding.FragmentPokemonStatsBinding
import dev.jgm.pokedex.databinding.ItemStatListBinding
import dev.jgm.pokedex.ui.pokemondetail.adapter.AbilitiesAdapter
import dev.jgm.pokedex.ui.pokemondetail.sections.BaseFragmentSection
import dev.jgm.pokedex.utils.extension.formatToUppercase

class PokemonStatsFragment : BaseFragmentSection(R.layout.fragment_pokemon_stats) {

    private lateinit var binding: FragmentPokemonStatsBinding
    private val viewModel: PokemonStatViewModel by viewModels()

    override fun initFragment(view: View) {
        binding = FragmentPokemonStatsBinding.bind(view)
        initObservers()

        binding.root.post {
            arguments?.let { args ->
                @Suppress("UNCHECKED_CAST")
                val stats: ArrayList<Stat>? = args.getSerializable("stats") as? ArrayList<Stat>?
                setUpStates(stats)

                @Suppress("UNCHECKED_CAST")
                val abilities: ArrayList<AbilityValue>? = args.getSerializable("abilities") as? ArrayList<AbilityValue>?
                if(abilities != null) {
                    viewModel.loadAbilities(abilities)
                } else {
                    binding.tvNoSpecialAbilities.visibility = View.VISIBLE
                    binding.rvSpecialAbilities.visibility = View.GONE
                }
            }
        }
    }

    override fun getTitleId(): Int = R.string.stat

    private fun setUpStates(stats: ArrayList<Stat>?) {
        stats?.let { listStat ->
            PokemonStat.values().forEach { pokemonStat ->
                val view = when (pokemonStat) {
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

    private fun initObservers() {
        viewModel.abilities.observe(viewLifecycleOwner) { abilities ->
            if (abilities.isNotEmpty()) {
                val layoutManager = LinearLayoutManager(binding.root.context)
                val adapter = AbilitiesAdapter(abilities)
                binding.rvSpecialAbilities.layoutManager = layoutManager
                binding.rvSpecialAbilities.adapter = adapter
                binding.rvSpecialAbilities.visibility = View.VISIBLE
                binding.tvNoSpecialAbilities.visibility = View.GONE
            } else{
                binding.tvNoSpecialAbilities.visibility = View.VISIBLE
                binding.rvSpecialAbilities.visibility = View.GONE
            }
        }

        viewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                binding.tvNoSpecialAbilities.visibility = View.VISIBLE
                binding.loading.root.visibility = View.GONE
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if(isLoading) {
                binding.tvNoSpecialAbilities.visibility = View.GONE
                binding.loading.root.visibility = View.VISIBLE
            } else {
                binding.loading.root.visibility = View.GONE
            }
        }
    }
}