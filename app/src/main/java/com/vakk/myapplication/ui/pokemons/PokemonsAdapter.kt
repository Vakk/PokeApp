package com.vakk.myapplication.ui.pokemons

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vakk.domain.entity.Pokemon
import com.vakk.myapplication.R
import com.vakk.myapplication.ui.utils.loadImage
import com.vakk.starter.ui.AdapterClickListener
import com.vakk.starter.ui.BaseListenerRecyclerViewAdapter
import com.vakk.starter.ui.BaseViewHolder

class PokemonsAdapter(
    adapterClickListener: AdapterClickListener<Pokemon>
) : BaseListenerRecyclerViewAdapter<Pokemon, PokemonsAdapter.ViewHolder>(
    listener = adapterClickListener
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(initView(R.layout.item_pokemon, parent), this)
    }

    class ViewHolder(itemView: View, adapterClickListener: AdapterClickListener<Pokemon>) :
        BaseViewHolder<Pokemon>(itemView, adapterClickListener) {

        val ivIcon = itemView.findViewById<ImageView>(R.id.ivIcon)
        val tvName = itemView.findViewById<TextView>(R.id.tvName)

        override fun onBind(item: Pokemon) {
            tvName.text = item.name
            ivIcon.loadImage(item.iconUrl)
        }
    }
}