package com.vakk.myapplication.ui.pokemons

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vakk.common.cast
import com.vakk.domain.entity.Pokemon
import com.vakk.myapplication.R
import com.vakk.myapplication.ui.utils.loadImage
import com.vakk.starter.ui.AdapterClickListener
import com.vakk.starter.ui.BaseListenerRecyclerViewAdapter
import com.vakk.starter.ui.BaseViewHolder

class PokemonsAdapter(
    adapterClickListener: AdapterClickListener<Pokemon>
) : BaseListenerRecyclerViewAdapter<Pokemon, BaseViewHolder<Pokemon>>(
    listener = adapterClickListener
) {

    companion object {
        const val OTHER_ITEM = 0
        const val PROGRESS_ITEM = 1
    }

    var isProgress: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        val itemsCount = super.getItemCount()
        return if (isProgress) {
            itemsCount + 1 // last item should show the progress.
        } else {
            itemsCount
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Pokemon>, position: Int) {
        if (position in all.indices) {
            super.onBindViewHolder(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == all.size) {
            return PROGRESS_ITEM
        } else {
            OTHER_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Pokemon> {
        return when (viewType) {
            OTHER_ITEM -> ViewHolder(initView(R.layout.item_pokemon, parent), this)
            else -> ProgressViewHolder(initView(R.layout.item_progress, parent))
        }.cast()
    }

    class ViewHolder(itemView: View, adapterClickListener: AdapterClickListener<Pokemon>) :
        BaseViewHolder<Pokemon>(itemView, adapterClickListener) {

        val ivIcon = itemView.findViewById<ImageView>(R.id.ivIcon)
        val tvName = itemView.findViewById<TextView>(R.id.tvName)

        init {
            setClickListeners(itemView)
        }

        override fun onBind(item: Pokemon) {
            tvName.transitionName = "title_${item.id}"
            ivIcon.transitionName = "icon_${item.id}"
            tvName.text = item.name
            ivIcon.loadImage(item.iconUrl)
        }
    }

    class ProgressViewHolder(
        itemView: View
    ) : BaseViewHolder<Pokemon>(
        itemView
    ) {
        override fun onBind(item: Pokemon) {

        }
    }
}