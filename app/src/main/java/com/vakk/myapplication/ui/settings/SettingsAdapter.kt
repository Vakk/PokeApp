package com.vakk.myapplication.ui.settings

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vakk.domain.entity.Language
import com.vakk.myapplication.R
import com.vakk.starter.ui.AdapterClickListener
import com.vakk.starter.ui.BaseListenerRecyclerViewAdapter
import com.vakk.starter.ui.BaseViewHolder

class SettingsAdapter(
    listener: AdapterClickListener<Language>
) : BaseListenerRecyclerViewAdapter<Language, SettingsAdapter.ViewHolder>(
    listener = listener
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(initView(R.layout.item_language, parent), this)
    }

    class ViewHolder(
        view: View,
        adapterClickListener: AdapterClickListener<Language>
    ) : BaseViewHolder<Language>(
        view,
        adapterClickListener
    ) {

        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        init {
            setClickListeners(itemView)
        }

        override fun onBind(item: Language) {
            tvTitle.text = item.name
        }
    }
}