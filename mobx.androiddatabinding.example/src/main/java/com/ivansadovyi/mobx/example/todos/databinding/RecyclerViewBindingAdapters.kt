package com.ivansadovyi.mobx.example.todos.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBindingAdapters {

	@JvmStatic
	@BindingAdapter("items", "itemLayout")
	@Suppress("UNCHECKED_CAST")
	fun <T> setItems(recyclerView: RecyclerView, items: List<T>, itemLayout: Int) {
		if (recyclerView.adapter == null) {
			recyclerView.adapter = GenericRecyclerViewDataBindingAdapter<T>(itemLayout)
		}

		val adapter = recyclerView.adapter as GenericRecyclerViewDataBindingAdapter<T>
		adapter.setItems(items)
	}
}
