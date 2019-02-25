package com.test.starwarsapp.presentation.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> (
        protected val itens: MutableList<T>
) : RecyclerView.Adapter<VH>() {

    var onItemClickListener: OnItemClickListener<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
                .inflate(getLayout(), parent, false)

        return getHolderView(view).also { holder ->
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(itens[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        bind(holder, itens[position])
    }

    abstract fun getLayout(): Int
    abstract fun getHolderView(view: View): VH
    abstract fun bind(holder: VH, item: T)

    fun setItems(itens: List<T>) {
        this.itens.clear()
        this.itens.addAll(itens)
        notifyDataSetChanged()
    }

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }
}