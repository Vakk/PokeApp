package com.vakk.starter.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference


/**
 * This class used for reduce boilerplate code with adapters, like saving items, set click listeners etc...
 * For use it - You should override method onBind.
 * @see BaseListenerRecyclerViewAdapter
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseViewHolder<T>(view: View, listener: AdapterClickListener<T>? = null) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    companion object {
        fun initView(layoutId: Int, parent: ViewGroup): View {
            return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        }
    }

    protected var item: T
        get() = itemView.tag as T
        set(value) {
            itemView.tag = value
        }

    protected var optItem: T?
        get() = itemView.tag as? T?
        set(value) {
            itemView.tag = value
        }

    val context: Context
        get() = itemView.context

    private var listenerWR = WeakReference(listener)
    protected val listener: AdapterClickListener<T>?
        get() = listenerWR.get()

    fun bind(item: T) {
        this.item = item
        onBind(item)
    }

    fun bind(item: T, payloads: Bundle?) {
        this.item = item
        if (payloads == null || !onBind(item, payloads)) {
            onBind(item)
        }
    }

    override fun onClick(v: View?) {
        v?.let { view -> listener?.onClick(item, view) }
    }

    protected fun setClickListeners(listener: View.OnClickListener, vararg views: View) {
        views.forEach { view -> view.setOnClickListener(listener) }
    }

    protected fun setClickListeners(vararg views: View) {
        views.forEach { view -> view.setOnClickListener(this) }
    }

    protected abstract fun onBind(item: T)

    /**
     * Override this and return true if you want to call this when something was changed with payload.
     * If you will return false - you will receive onBind(item: T) notification.
     */
    open fun onBind(item: T, payloads: Bundle): Boolean {
        // You should override this for bind with payloads.
        return false
    }
}