package com.ivansadovyi.mobx.example.todos

import com.ivansadovyi.mobx.observable

class Todo(
	text: String,
	isCompleted: Boolean,
	private val todoStore: TodoStore
) {

	var text by observable { text }

	var isCompleted by observable { isCompleted }

	fun remove() {
		todoStore.removeTodo(text)
	}

	override fun hashCode(): Int {
		var hash = 7
		hash = 31 * hash + text.hashCode()
		hash = 31 * hash + isCompleted.hashCode()
		return hash
	}

	override fun equals(other: Any?): Boolean {
		if (other !is Todo) {
			return false
		}

		return text == other.text && isCompleted == other.isCompleted
	}
}
