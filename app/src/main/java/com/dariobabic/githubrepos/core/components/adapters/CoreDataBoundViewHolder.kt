package com.dariobabic.githubrepos.core.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class CoreDataBoundViewHolder<T : ViewDataBinding?>(val binding: T) : RecyclerView.ViewHolder(
    binding?.root ?: throw NullPointerException("binding is null")
) {

    companion object {
        fun <T : ViewDataBinding?> create(
            parent: ViewGroup,
            @LayoutRes layoutId: Int
        ): CoreDataBoundViewHolder<T> {
            val binding: T = DataBindingUtil.inflate<T>(
                LayoutInflater.from(parent.context),
                layoutId, parent, false
            )
            return CoreDataBoundViewHolder(binding)
        }
    }
}