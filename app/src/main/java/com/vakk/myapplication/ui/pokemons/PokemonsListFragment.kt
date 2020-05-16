package com.vakk.myapplication.ui.pokemons

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.vakk.domain.entity.Pokemon
import com.vakk.myapplication.R
import com.vakk.starter.ui.AdapterClickListener
import com.vakk.starter.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_list.*

class PokemonsListFragment : BaseFragment<PokemonsListViewModel>(
    viewModelClass = PokemonsListViewModel::class.java,
    layoutId = R.layout.fragment_list
), AdapterClickListener<Pokemon> {

    val adapter by lazy { PokemonsAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareListView()
    }

    override fun prepareViewModelObservers() {
        viewModel.onPokemonsLoaded.observe(Observer {
            adapter.replaceItems(it)
        })
    }

    override fun onClick(item: Pokemon, view: View) {
        // TODO: Implement actions.
    }

    private fun prepareListView() {
        rvItems.adapter = adapter
    }
}