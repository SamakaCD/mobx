package com.ivansadovyi.mobx.example.todos.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ivansadovyi.mobx.ObservableArrayList

class GenericRecyclerViewDataBindingAdapter<T>(
	private val itemLayoutId: Int
) : RecyclerView.Adapter<GenericRecyclerViewDataBindingAdapter.ViewHolder>() {

	private var items = emptyList<T>()

	init {
		setHasStableIds(true)
	}

	fun setItems(items: List<T>) {
		this.items = items
		notifyDataSetChanged()

		if (items is ObservableArrayList) {
			items.triggerTracker()
		}
	}

	override fun getItemCount(): Int {
		return items.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, itemLayoutId, parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = items[position]
		holder.binding.setVariable(BR.item, item)
	}

	override fun getItemId(position: Int): Long {
		return items[position].hashCode().toLong()
	}

	class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}
