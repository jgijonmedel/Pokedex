package dev.jgm.pokedex.ui.pokemonlist

import android.animation.LayoutTransition
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.jgm.pokedex.R
import dev.jgm.pokedex.data.model.PokedexItem
import dev.jgm.pokedex.databinding.FragmentPokemonListBinding
import dev.jgm.pokedex.ui.pokemonlist.adapter.PokemonListAdapter
import dev.jgm.pokedex.utils.OrderPokedexValue
import dev.jgm.pokedex.utils.extension.hideKeyboard

class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {

    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonListViewModel by viewModels()
    private lateinit var adapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonListBinding.bind(view)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setUpRecycler()
        setUpFilter()
        setUpFloatingButton()
        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_with_search, menu)
        val itemMenu = menu.findItem(R.id.item_menu_search)
        val searchView: SearchView = (itemMenu.actionView as SearchView)
        searchView.queryHint = getString(R.string.what_pokemon_are_you_looking_for)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    hideKeyboard()
                    viewModel.searchPokemon(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) viewModel.searchPokemon(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_menu_help -> {
                Toast.makeText(context, getString(R.string.help), Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpRecycler() {
        val layoutManager = GridLayoutManager(binding.root.context, 3)
        adapter = PokemonListAdapter { openPokemonDetail(it) }
        binding.rvPokemonList.layoutManager = layoutManager
        binding.rvPokemonList.adapter = adapter
        binding.refreshLayout.setOnRefreshListener {
            hideKeyboard()
            viewModel.loadPokedex()
        }
        binding.rvPokemonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val isVisible =
                    recyclerView.layoutManager?.findViewByPosition(9)?.isVisible.toString()

                if (isVisible != "true" && recyclerView.childCount > 12) {
                    binding.btnScrollableManager.show()
                } else {
                    binding.btnScrollableManager.hide()
                }
            }
        })
    }

    private fun setUpFloatingButton() {
        binding.btnScrollableManager.hide()
        binding.btnScrollableManager.setOnClickListener {
            binding.rvPokemonList.smoothScrollToPosition(1)
        }
    }

    private fun setUpFilter() {
        val filter = binding.filter
        val container = binding.filter.container
        container.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        filter.title.setOnClickListener {
            if (container.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(filter.root, AutoTransition())
                container.visibility = View.VISIBLE
                binding.filter.icon.rotation = 90f
            } else {
                container.visibility = View.GONE
                binding.filter.icon.rotation = -90f
            }
        }

        filter.btnApply.setOnClickListener {
            val orderPokedexType = when {
                filter.rbName.isChecked -> OrderPokedexValue.NAME
                else -> OrderPokedexValue.NUMBER
            }
            viewModel.sortedBy(orderPokedexType, filter.rbOrderAsc.isChecked)
            container.visibility = View.GONE
        }
    }

    private fun initObservers() {
        viewModel.pokedexList.observe(viewLifecycleOwner) { pokemonList ->
            if (pokemonList.isNotEmpty()) {
                adapter.pokemonList = pokemonList
                hideMessage()
            } else {
                showMessageEmptyList()
            }
        }

        viewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError) showMessageError() else hideMessage()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                hideMessage()
                binding.placeholder.root.visibility = View.VISIBLE
                val anim = AnimationUtils.loadAnimation(context, R.anim.infinite_fade)
                binding.placeholder.view.startAnimation(anim)
            } else {
                binding.refreshLayout.isRefreshing = isLoading
                binding.refreshLayout.visibility = View.VISIBLE
                binding.rvPokemonList.visibility = View.VISIBLE
                binding.placeholder.view.clearAnimation()
                binding.placeholder.root.visibility = View.GONE
            }
            resetFilter()
        }
    }

    private fun openPokemonDetail(pokedexItem: PokedexItem) {
        val action = PokemonListFragmentDirections.actionToPokemonDetail(pokedexItem.pokemonName)
        findNavController().navigate(action)
    }

    private fun resetFilter() {
        binding.filter.rbNumber.isChecked = true
        binding.filter.rbOrderAsc.isChecked = true
    }

    private fun showMessageEmptyList() {
        binding.message.image.setImageResource(R.drawable.img_pokeball_empty)
        binding.message.title.text = getString(R.string.empty_pokemon_list)
        binding.message.body.text = getString(R.string.no_pokemon_matches_the_search)
        binding.message.container.visibility = View.VISIBLE
    }

    private fun showMessageError() {
        binding.message.image.setImageResource(R.drawable.img_no_internet_connection)
        binding.message.title.text = getString(R.string.no_internet_connection)
        binding.message.body.text = getString(R.string.need_internet_connection)
        binding.message.button.text = getString(R.string.try_again)
        binding.message.button.setOnClickListener { viewModel.loadPokedex() }
        binding.message.button.visibility = View.VISIBLE
        binding.message.container.visibility = View.VISIBLE
    }

    private fun hideMessage() {
        binding.message.container.visibility = View.GONE
        binding.message.button.visibility = View.GONE
    }
}