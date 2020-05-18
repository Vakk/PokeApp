package com.vakk.myapplication.ui.details

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vakk.domain.entity.Ability
import com.vakk.myapplication.R
import com.vakk.starter.ui.BaseListenerRecyclerViewAdapter
import com.vakk.starter.ui.BaseViewHolder

class AbilityAdapter : BaseListenerRecyclerViewAdapter<Ability, AbilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(initView(R.layout.item_ability, parent))
    }

    class ViewHolder(
        itemView: View
    ) : BaseViewHolder<Ability>(
        view = itemView
    ) {

        val tvName = itemView.findViewById<TextView>(R.id.tvName)

        override fun onBind(item: Ability) {
            tvName.text = item.name
        }

    }
}