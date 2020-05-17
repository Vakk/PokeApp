package com.vakk.starter.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vakk.common.safeCast
import java.lang.ref.WeakReference

/**
 * Use this scroll listener with LinearLayoutManager.
 */
class PaginationScrollListener(
    paginationCallback: PaginationCallback,
    val pagesThreshold: Int = LOAD_MORE_PAGES_TRESHOLD,
    val visibleItemsPerPageMultiplier: Int = VISIBLE_ITEMS_PER_PAGE_MULTIPLIER
) : RecyclerView.OnScrollListener() {

    companion object {
        private const val VISIBLE_ITEMS_PER_PAGE_MULTIPLIER = 3
        private const val LOAD_MORE_PAGES_TRESHOLD = 3
    }

    private val callbackWR = WeakReference(paginationCallback)

    private val callback: PaginationCallback?
        get() = callbackWR.get()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val callback = callback ?: return
        val layoutManager = recyclerView.layoutManager!!
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.safeCast<LinearLayoutManager>()?.let {
            it.findFirstVisibleItemPosition()
        } ?: layoutManager.safeCast<StaggeredGridLayoutManager>()?.let {
            it.findLastVisibleItemPositions(null).last()
        } ?: return //TODO: something went wrong for this case implement action.
        if (callback.itemsPerPage < visibleItemCount * visibleItemsPerPageMultiplier) {
            callback.itemsPerPage = visibleItemCount * visibleItemsPerPageMultiplier
        }

        val isNeedToLoadMore =
            (firstVisibleItemPosition + visibleItemCount >= (totalItemCount - callback.itemsPerPage * pagesThreshold) && firstVisibleItemPosition >= 0)
        if (isNeedToLoadMore && !callback.isLastPage && !callback.isPaginationInProcess) {
            callback.loadMoreItems()
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        when (newState) {
            RecyclerView.SCROLL_STATE_SETTLING -> onScrolled(recyclerView, 0, 0)
            else -> super.onScrollStateChanged(recyclerView, newState)
        }
    }
}

interface PaginationCallback {
    val isLastPage: Boolean
    val isPaginationInProcess: Boolean

    var itemsPerPage: Int // this is a value for initial pagination part. For the next time - it will be calculated by visible items count.

    fun loadMoreItems()
}