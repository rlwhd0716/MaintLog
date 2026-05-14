package com.github.util.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<B : ViewDataBinding, DATA>(val layoutId: Int) :
    RecyclerView.Adapter<BaseRecyclerViewHolder<B, DATA>>() {

    open var datas = mutableListOf<DATA>()
        set(value) {
            datas.clear()
            datas.addAll(value)
        }

    private var onClickEvent: OnClickEvent<DATA>? = null

    fun getOnClickEvent(): OnClickEvent<DATA>? = onClickEvent

    @JvmName("RecyclerItemClickListener")
    fun setOnClickEvent(listener: OnClickEvent<DATA>) {
        onClickEvent = listener
    }

    abstract fun onBindItem(binding: B?, data: DATA, position: Int)

    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseRecyclerViewHolder<B, DATA>(
            LayoutInflater.from(parent.context).inflate(
                layoutId,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<B, DATA>, position: Int) {
        holder.bind(datas[position]) { binding, data, layoutPosition ->
            onBindItem(binding, data, layoutPosition)
        }
    }

    interface OnClickEvent<T> {
        fun itemClick(item: T)
    }
}

open class BaseRecyclerViewHolder<B : ViewDataBinding?, DATA>(val item: View) :
    RecyclerView.ViewHolder(item) {
    private val binding: B? = DataBindingUtil.bind(item)

    fun bind(data: DATA, block: (B?, DATA, Int) -> Unit) {
        block.invoke(binding, data, layoutPosition)
    }
}
