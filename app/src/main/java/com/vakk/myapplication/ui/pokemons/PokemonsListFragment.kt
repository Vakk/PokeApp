package com.vakk.myapplication.ui.pokemons

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vakk.domain.entity.Pokemon
import com.vakk.myapplication.R
import com.vakk.starter.ui.AdapterClickListener
import com.vakk.starter.ui.BaseFragment
import com.vakk.starter.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_pokemons.*

class PokemonsListFragment : BaseFragment<PokemonsListViewModel>(
    viewModelClass = PokemonsListViewModel::class.java,
    layoutId = R.layout.fragment_pokemons
), AdapterClickListener<Pokemon> {

    val adapter by lazy { PokemonsAdapter(this) }

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
        })
    }

    override fun onClick(item: Pokemon, view: View) {
        // TODO: Implement actions.
    }

    private fun prepareListView() {
        rvItems.adapter = adapter
        rvItems.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
//        rvItems.addOnScrollListener(PaginationScrollListener(viewModel))
    }
}