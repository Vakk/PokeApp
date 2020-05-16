package com.vakk.myapplication.ui.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.vakk.domain.entity.Pokemon
import com.vakk.myapplication.R
import com.vakk.starter.ui.AdapterClickListener
import com.vakk.starter.ui.BaseFragment

class PokemonsListFragment : BaseFragment<PokemonsListViewModel>(
    viewModelClass = PokemonsListViewModel::class.java,
    layoutId = R.layout.fragment_list
), AdapterClickListener<Pokemon> {
    val adapter by lazy { PokemonsAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState).let {
            viewModel.onPokemonsLoaded.observe(this@PokemonsListFragment, Observer {

            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(item: Pokemon, view: View) {
        // TODO: Implement actions.
    }
}