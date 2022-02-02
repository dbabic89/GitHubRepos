package com.dariobabic.githubrepos.core.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageFromURL")
fun loadImageFromURL(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}