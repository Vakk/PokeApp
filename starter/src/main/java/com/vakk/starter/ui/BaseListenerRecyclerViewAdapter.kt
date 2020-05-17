package com.vakk.starter.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import java.lang.ref.WeakReference

/**
 * This adapter used for implement item clicks and reduce boilerplate code.
 * @see BaseViewHolder
 */
abstract class BaseListenerRecyclerViewAdapter<T, VH : BaseViewHolder<T>>(
    items: List<T> = mutableListOf(),
    listener: AdapterClickListener<T>? = null
) : BaseRecyclerViewAdapter<T, VH>(items), AdapterClickListener<T> {

    protected var listenerWR = listener?.let { WeakReference(it) }

    override fun onClick(item: T, view: View) {
        listenerWR?.get()?.onClick(item, view)
    }

    protected fun initView(layoutId: Int, parent: ViewGroup): View {
        return BaseViewHolder.initView(layoutId, parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        val payload = payloads.firstOrNull() as? Bundle
        if (payload == null) {
            onBindViewHolder(holder, position)
        } else {
            holder.bind(getItem(position), payload)
        }
    }

}