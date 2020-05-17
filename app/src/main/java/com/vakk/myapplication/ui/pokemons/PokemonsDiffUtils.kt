package com.vakk.myapplication.ui.pokemons

import com.vakk.domain.entity.Pokemon
import com.vakk.starter.utils.BaseDiffUtilCallback

class PokemonsDiffUtils(
    override var oldList: List<Pokemon>,
    override var newList: List<Pokemon>
) : BaseDiffUtilCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }

}