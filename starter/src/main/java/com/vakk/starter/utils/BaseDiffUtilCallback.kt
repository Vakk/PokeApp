package com.vakk.starter.utils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil


abstract class BaseDiffUtilCallback<TData>(
        open var oldList: List<TData> = listOf(),
        open var newList: List<TData> = listOf()
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return getChangePayload(oldList[oldItemPosition], newList[newItemPosition])
    }

    open fun areItemsTheSame(oldItem: TData, newItem: TData): Boolean {
        return oldItem == newItem ?: false
    }

    open fun areContentsTheSame(oldItem: TData, newItem: TData): Boolean {
        return oldItem == newItem ?: false
    }

    open fun getChangePayload(oldItem: TData, newItem: TData): Bundle? {
        return Bundle()
    }


}