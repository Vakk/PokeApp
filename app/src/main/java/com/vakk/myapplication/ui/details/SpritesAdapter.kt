package com.vakk.myapplication.ui.details

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vakk.myapplication.R
import com.vakk.myapplication.ui.utils.loadImage
import com.vakk.starter.ui.BaseListenerRecyclerViewAdapter
import com.vakk.starter.ui.BaseViewHolder

class SpritesAdapter : BaseListenerRecyclerViewAdapter<SpriteItem, SpritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(initView(R.layout.item_alternative_sprite, parent))
    }

    class ViewHolder(
        itemView: View
    ) : BaseViewHolder<SpriteItem>(
        view = itemView
    ) {

        val ivIcon = itemView.findViewById<ImageView>(R.id.ivIcon)
        val tvName = itemView.findViewById<TextView>(R.id.tvName)

        override fun onBind(item: SpriteItem) {
            ivIcon.loadImage(item.url)
            tvName.text = item.title
        }

    }
}