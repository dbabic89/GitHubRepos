package com.dariobabic.githubrepos.core.components.adapters

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.OnRebindCallback
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class CoreDataBoundAdapter<T : ViewDataBinding?> :
    RecyclerView.Adapter<CoreDataBoundViewHolder<T>>() {

    private var recyclerView: RecyclerView? = null

    private val mOnRebindCallback: OnRebindCallback<*> =
        object : OnRebindCallback<ViewDataBinding?>() {
            override fun onPreBind(binding: ViewDataBinding?): Boolean {
                if (recyclerView == null || recyclerView?.isComputingLayout == true) {
                    return true
                }

                val childAdapterPosition = binding?.root?.let {
                    recyclerView?.getChildAdapterPosition(it)
                } ?: 0

                if (childAdapterPosition == RecyclerView.NO_POSITION) return true

                recyclerView?.post {
                    notifyItemChanged(
                        childAdapterPosition,
                        DB_PAYLOAD
                    )
                }
                return false
            }
        }

    @CallSuper
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoreDataBoundViewHolder<T> {
        val vh: CoreDataBoundViewHolder<T> = CoreDataBoundViewHolder.create(parent, viewType)
        vh.binding?.addOnRebindCallback(mOnRebindCallback)
        return vh
    }

    override fun onBindViewHolder(
        holder: CoreDataBoundViewHolder<T>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty() || hasNonDataBindingInvalidate(payloads)) {
            bindItem(holder, position, payloads)
        }
        holder.binding?.executePendingBindings()
    }

    private fun hasNonDataBindingInvalidate(payloads: List<Any>): Boolean {
        payloads.forEach {
            if (it != DB_PAYLOAD) return true
        }
        return false
    }

    protected abstract fun bindItem(
        holder: CoreDataBoundViewHolder<T>?,
        position: Int,
        payloads: List<Any?>?
    )

    override fun onBindViewHolder(
        holder: CoreDataBoundViewHolder<T>,
        position: Int
    ) {
        throw IllegalArgumentException("just overridden to make final.")
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = null
    }

    override fun getItemViewType(position: Int) = getItemLayoutId(position)

    @LayoutRes
    abstract fun getItemLayoutId(position: Int): Int

    companion object {
        private val DB_PAYLOAD = Any()
    }
}
