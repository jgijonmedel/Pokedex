package dev.jgm.pokedex.ui.pokemondetail

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dev.jgm.pokedex.R
import dev.jgm.pokedex.data.model.PokemonType
import dev.jgm.pokedex.data.model.pokemon.Pokemon
import dev.jgm.pokedex.data.model.pokemon.TypeValue
import dev.jgm.pokedex.databinding.FragmentPokemonDetailBinding
import dev.jgm.pokedex.ui.pokemondetail.adapter.PagerAdapter
import dev.jgm.pokedex.ui.pokemondetail.sections.BaseFragmentSection
import dev.jgm.pokedex.ui.pokemondetail.sections.about.PokemonAboutFragment
import dev.jgm.pokedex.ui.pokemondetail.sections.stat.PokemonStatsFragment
import dev.jgm.pokedex.utils.extension.*
import kotlin.collections.ArrayList

class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {

    private lateinit var binding: FragmentPokemonDetailBinding
    private val viewModel: PokemonDetailViewModel by viewModels()
    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonDetailBinding.bind(view)
        initObservers()
        binding.root.post {
            if (args.pokemonName.isNotEmpty()) {
                viewModel.loadPokemon(args.pokemonName)
            }
        }
    }

    private fun setUpTypes(types: List<TypeValue>) {
        binding.types.removeViews()
        types.forEach { currentType ->
            val nameType = currentType.type.name.formatToUppercase()
            val type = PokemonType.valueOf(nameType)
            binding.types.addType(type)
        }
    }

    private fun setUpTabs(pokemon: Pokemon) {
        val fragmentsTabs: List<BaseFragmentSection> = listOf(
            PokemonAboutFragment().apply {
                arguments = bundleOf(
                    "key_specie" to pokemon.species.url.getIdFromUrl(),
                    "key_id" to pokemon.id.toString()
                )
            },
            PokemonStatsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("stats", ArrayList(pokemon.stats))
                }
            }
        )
        val pagerAdapter = PagerAdapter(this, fragmentsTabs)
        binding.pager.adapter = pagerAdapter
        binding.pager.isUserInputEnabled = true
        TabLayoutMediator(binding.tab, binding.pager)
        { tab, position ->
            val resTitleFragment = fragmentsTabs[position].getTitleId()
            tab.text = getString(resTitleFragment)
        }.attach()
    }

    private fun setUpDetails(height: Int, weight: Int) {
        binding.tvHeight.text = getString(R.string.height, height.toDouble().calculateDimen())
        binding.tvWeight.text = getString(R.string.weight, weight.toDouble().calculateDimen())
    }

    private fun initObservers() {
        viewModel.pokemon.observe(viewLifecycleOwner, { pokemon ->
            if (pokemon != null) {
                binding.tvName.text = pokemon.name.firstCapitalLetter()
                binding.tvNumber.text = pokemon.id.formatDisplay()
                binding.tvHeight.text = getString(R.string.height, pokemon.height.toDouble().calculateDimen())
                binding.tvWeight.text = getString(R.string.weight, pokemon.weight.toDouble().calculateDimen())

                pokemon.sprites.other.official_artwork?.front_default?.let {
                    binding.ivImage.loadImage(it)
                }
                setUpTabs(pokemon)
                setUpTypes(pokemon.types)
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                binding.loading.root.visibility = View.VISIBLE
                //binding.phDetail.shimmerView.visibility = View.VISIBLE
                //binding.phDetail.shimmerView.startShimmer()
            } else {
                //binding.phDetail.shimmerView.stopShimmer()
                //binding.phDetail.shimmerView.visibility = View.GONE
                binding.loading.root.visibility = View.GONE
            }
        })
    }
}

/*
    https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/6.gif
 */