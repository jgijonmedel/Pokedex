package dev.jgm.pokedex.ui.pokemondetail.sections.about

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dev.jgm.pokedex.R
import dev.jgm.pokedex.data.model.FlavorTextEntry
import dev.jgm.pokedex.data.model.PokemonColor
import dev.jgm.pokedex.data.model.PokemonEggGroup
import dev.jgm.pokedex.data.model.PokemonShape
import dev.jgm.pokedex.data.model.specie.Genera
import dev.jgm.pokedex.data.model.specie.Specie
import dev.jgm.pokedex.data.model.specie.Variety
import dev.jgm.pokedex.databinding.FragmentPokemonAboutBinding
import dev.jgm.pokedex.ui.pokemondetail.adapter.VarietiesAdapter
import dev.jgm.pokedex.ui.pokemondetail.sections.BaseFragmentSection
import dev.jgm.pokedex.utils.extension.addEggGroup
import dev.jgm.pokedex.utils.extension.formatToUppercase
import dev.jgm.pokedex.utils.extension.getIdFromUrl
import dev.jgm.pokedex.utils.extension.removeLineBreaks
import java.util.*

class PokemonAboutFragment : BaseFragmentSection(R.layout.fragment_pokemon_about) {

    private lateinit var binding: FragmentPokemonAboutBinding
    private val viewModel: PokemonAboutViewModel by viewModels()
    val language = Locale.getDefault().language

    override fun initFragment(view: View) {
        binding = FragmentPokemonAboutBinding.bind(view)
        initObservers()
        arguments?.let { args ->
            val specieName = args.getString("key_specie") ?: "No_specie"
            viewModel.loadSpecie(specieName)
        }
    }

    override fun getTitleId(): Int = R.string.about

    private fun initObservers() {
        viewModel.specie.observe(viewLifecycleOwner, { specie ->
            if (specie != null) {
                val descriptions = specie.flavor_text_entries.filter{ it.language.name == language }
                val genera = specie.genera.filter{ it.language.name == language }
                setUpDescription(descriptions, genera)
                pokedexData(specie)
                setUpVarieties(specie.varieties)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if(isLoading) {
                binding.content.visibility = View.GONE
            } else {

                binding.content.visibility = View.VISIBLE
                binding.phAbout.root.visibility = View.GONE
            }
        })
    }

    private fun setUpDescription(descriptions: List<FlavorTextEntry>, generals: List<Genera>) {
        val description = binding.tvDescription
        val genera = binding.tvGenera
        when (descriptions.isNotEmpty()){
            true -> description.text = descriptions[descriptions.indices.random()].flavor_text.removeLineBreaks()
            else -> description.text = getString(R.string.unknown_description)
        }

        when (generals.isNotEmpty()){
            true -> genera.text = generals[generals.indices.random()].genus
            else -> genera.visibility = View.GONE
        }
    }

    private fun pokedexData(specie: Specie){
        val pokemonColor = PokemonColor.valueOf(specie.color.name.formatToUppercase())
        val color = ContextCompat.getColor(requireContext(), pokemonColor.color)
        binding.ivColor.imageTintList = ColorStateList.valueOf(color)
        binding.tvColor.text = specie.color.name

        val specieNames = specie.names.filter { it.language.name == language }
        binding.tvSpecie.text = specieNames[specieNames.indices.random()].name

        binding.tvCaptureRate.text = "${specie.capture_rate}%"
        specie.habitat?.let { binding.tvHabitat.text = it.name }

        specie.egg_groups.forEach{
            val nameEggGroup = it.name.formatToUppercase()
            val eggGroup = PokemonEggGroup.valueOf(nameEggGroup)
            binding.eggsGroups.addEggGroup(eggGroup)
        }

        val shape = PokemonShape.valueOf(specie.shape.name.formatToUppercase())
        binding.tvShape.text = getString(shape.text)
    }

    private fun setUpVarieties(varieties: List<Variety>) {
        val pokemonId = arguments?.getString("key_id") ?: "No_id"
        val list = varieties.filter { it.pokemon.url.getIdFromUrl() != pokemonId }

        if (list.isNotEmpty()) {
            val layoutManager = GridLayoutManager(binding.root.context, 3)
            val adapter = VarietiesAdapter(list)
            binding.rvVarieties.layoutManager = layoutManager
            binding.rvVarieties.adapter = adapter
        } else{
            binding.tvNoVarieties.visibility = View.VISIBLE
        }
    }
}
