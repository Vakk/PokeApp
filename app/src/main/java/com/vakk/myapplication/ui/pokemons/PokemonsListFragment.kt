package com.vakk.myapplication.ui.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.transition.ChangeBounds
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