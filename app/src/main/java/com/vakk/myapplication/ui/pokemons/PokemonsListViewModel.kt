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

    val onItemsLoading = MutableLiveData<Boolean>()

    val onPokemonsLoaded = MutableLiveData<List<Pokemon>>()

    override var isLastPage: Boolean = false

    override var isPaginationInProcess: Boolean = false
        set(value) {
            field = value
            onItemsLoading.value = value
        }
    override var itemsPerPage: Int = 20

    val items = mutableListOf<Pokemon>()

    init {
        loadMoreItems()
    }

    override fun loadMoreItems() {
        isPaginationInProcess = true
        val take = itemsPerPage
        viewModelScope.launchSafe(onComplete = {
            isPaginationInProcess = false
        }) {
            val skip = items.size
            val pokemons = getPokemonsUseCase(skip = skip, take = take)
            items.addAll(pokemons)
            isLastPage = pokemons.size != take
            onPokemonsLoaded.value = items
        }
    }

}