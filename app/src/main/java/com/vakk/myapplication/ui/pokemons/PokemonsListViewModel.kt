package com.vakk.myapplication.ui.pokemons

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vakk.common.cast
import com.vakk.common.launchSafe
import com.vakk.domain.entity.Pokemon
import com.vakk.domain.usecase.GetPokemonsUseCase
import com.vakk.starter.utils.PaginationCallback
import javax.inject.Inject

class PokemonsListViewModel @Inject constructor(
    context: Context,
    val getPokemonsUseCase: GetPokemonsUseCase
) : AndroidViewModel(context.applicationContext.cast()),
    PaginationCallback {

    val onPokemonsLoaded = MutableLiveData<List<Pokemon>>()

    override var isLastPage: Boolean = false

    override var isPaginationInProcess: Boolean = false

    override var itemsPerPage: Int = 20

    val items = listOf<Pokemon>()

    init {
        loadMoreItems()
    }

    override fun loadMoreItems() {
        isPaginationInProcess = true
        viewModelScope.launchSafe(onComplete = {
            isPaginationInProcess = false
        }) {
            val pokemons = getPokemonsUseCase(skip = items.size, take = itemsPerPage)
            onPokemonsLoaded.value = pokemons
        }
    }

}