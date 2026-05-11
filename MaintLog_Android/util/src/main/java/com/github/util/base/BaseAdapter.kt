package com.github.util.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<I : ViewDataBinding, D>(val layoutId: Int) :
    RecyclerView.Adapter<BaseAdapter<I, D>.ViewHolder<I, D>>() {
    abstract var datalist: MutableList<D>

    abstract fun onInitItem(binding: I)
    abstract fun onBindItem(binding: I, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<I, D> {
        return ViewHolder<I, D>(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        ) { binding -> onInitItem(binding) }
    }

    override fun getItemCount() = datalist.size

    override fun onBindViewHolder(holder: ViewHolder<I, D>, position: Int) {
        holder.onBind { binding -> onBindItem(binding, position) }
    }

    inner class ViewHolder<B : ViewDataBinding, D>(
        val binding: B,
        onInit: (binding: B) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            onInit.invoke(binding)
        }

        fun onBind(function: (binding: B) -> Unit) {
            function.invoke(binding)
        }
    }
}