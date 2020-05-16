package com.vakk.starter.ui

import android.view.View

/**
 * Adapter click listener used for handle click event.
 */
interface AdapterClickListener<in T> {
    fun onClick(item: T, view: View)
}