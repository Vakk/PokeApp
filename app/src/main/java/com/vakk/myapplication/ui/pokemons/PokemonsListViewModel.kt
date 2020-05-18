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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    var searchQuery: String = ""

    var items = mutableListOf<Pokemon>()

    var searchJob: Job? = null
    var loadingJob: Job? = null

    init {
        loadMoreItems()
    }

    override fun loadMoreItems() {
        if (loadingJob?.isCompleted == false) {
            return
        }
        isPaginationInProcess = true
        val take = itemsPerPage
        loadingJob = viewModelScope.launchSafe(onComplete = {
            isPaginationInProcess = false
        }) {
            val skip = items.size
            val pokemons = getPokemonsUseCase(searchQuery, skip = skip, take = take)
            items.addAll(pokemons)
            isLastPage = pokemons.isEmpty()
            onPokemonsLoaded.value = items
        }
    }

    fun search(newText: String?) {
        searchQuery = newText ?: ""
        if (searchJob == null || searchJob?.isCompleted == true) {
            searchJob = viewModelScope.launchSafe {
                delay(500)
                loadingJob?.cancel()
                loadingJob = null
                isLastPage = false
                isPaginationInProcess = false
                items = mutableListOf()
                loadMoreItems()
            }
        }
    }

}