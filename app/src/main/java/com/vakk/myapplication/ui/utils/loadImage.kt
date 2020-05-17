package com.vakk.myapplication.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(imageUri: String?) {
    Glide.with(context)
        .load(imageUri)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
        .apply(RequestOptions.centerCropTransform())
        .into(this)
}
