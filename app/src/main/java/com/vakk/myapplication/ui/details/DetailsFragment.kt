package com.vakk.myapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.transition.AutoTransition
import androidx.transition.ChangeBounds
import com.vakk.myapplication.R
import com.vakk.myapplication.ui.utils.loadImage
import com.vakk.starter.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import kotlinx.android.synthetic.main.fragment_pokemon_details.view.*

class DetailsFragment : BaseFragment<DetailsViewModel>(
    DetailsViewModel::class.java,
    layoutId = R.layout.fragment_pokemon_details
) {

    val spritesAdapter by lazy { SpritesAdapter() }

    val abilityAdapter by lazy { AbilityAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = AutoTransition()
        sharedElementReturnTransition = AutoTransition()
        return super.onCreateView(inflater, container, savedInstanceState)?.apply {
            arguments?.getLong("id")?.let {
                this.tvName.transitionName = "title_${it}"
                this.ivIcon.transitionName = "icon_${it}"
                postponeEnterTransition()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getLong("id")?.let {
            viewModel.pokemonId = it
        } ?: activity?.onBackPressed()
        prepareLists()
    }

    override fun prepareViewModelObservers() {
        viewModel.onDetailsLoaded.observe(Observer {
            tvName.text = it.pokemon.name
            ivIcon.loadImage(it.pokemon.iconUrl)
            abilityAdapter.replaceItems(it.abilities)
            startPostponedEnterTransition()
        })

        viewModel.onSpritesLoaded.observe(Observer {
            spritesAdapter.replaceItems(it)
        })
    }


    fun prepareLists() {
        rvAbilities.adapter = abilityAdapter
        rvSprites.adapter = spritesAdapter
    }
}