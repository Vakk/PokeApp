package com.vakk.myapplication.ui.pokemons

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vakk.domain.entity.Pokemon
import com.vakk.myapplication.R
import com.vakk.starter.ui.AdapterClickListener
import com.vakk.starter.ui.BaseFragment
import com.vakk.starter.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_pokemons.*
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonsListFragment : BaseFragment<PokemonsListViewModel>(
    viewModelClass = PokemonsListViewModel::class.java,
    layoutId = R.layout.fragment_pokemons
), AdapterClickListener<Pokemon> {

    val adapter by lazy { PokemonsAdapter(this) }

    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareListView()
    }

    override fun prepareViewModelObservers() {
        viewModel.onPokemonsLoaded.observe(Observer {
            adapter.updateItems(
                it, PokemonsDiffUtils(
                    oldList = adapter.all,
                    newList = it
                )
            )
            startPostponedEnterTransition()
            tvLimitedResults.visibility = if (viewModel.searchQuery.isNotEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        viewModel.onItemsLoading.observe(Observer {
            adapter.isProgress = it
        })
    }

    override fun onClick(item: Pokemon, view: View) {
        val extras = FragmentNavigatorExtras(
            view.tvName to view.tvName.transitionName,
            view.ivIcon to view.ivIcon.transitionName
        )
        findNavController().navigate(
            R.id.action_fragmentPokemonsList_to_detailsFragment,
            Bundle().apply {
                putLong("id", item.id)
            },
            null,
            extras
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query)
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> findNavController().navigate(
                R.id.action_fragmentPokemonsList_to_settingsFragment
            )
        }
        return super.onOptionsItemSelected(item)
    }

    private fun prepareListView() {
        rvItems.adapter = adapter
        rvItems.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
        rvItems.addOnScrollListener(
            PaginationScrollListener(
                viewModel,
                pagesThreshold = 0
            )
        )
    }
}