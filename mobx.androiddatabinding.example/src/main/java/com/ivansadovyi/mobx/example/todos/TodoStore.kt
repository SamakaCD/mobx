package com.ivansadovyi.mobx.example.todos

import com.ivansadovyi.mobx.ObservableArrayList

class TodoStore {

	val todos = ObservableArrayList<Todo>()

	fun addTodo(text: String, isCompleted: Boolean) {
		todos += Todo(text, isCompleted, todoStore = this)
	}

	fun removeTodo(text: String) {
		val index = todos.indexOfFirst { it.text == text }
		if (index >= 0) {
			todos.removeAt(index)
		}
	}
}
