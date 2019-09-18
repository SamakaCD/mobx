package com.example.mobxkotlin

import com.ivansadovyi.mobx.ObservableHashMap
import org.junit.Test

class MobxTest {

	class Todo(
		text: String,
		isCompleted: Boolean
	) {
		var text by observable { text }
		var isCompleted by observable { isCompleted }
	}

	class TodoStore {
		val todos = ObservableHashMap<String, Todo>()

		val todosCount by computed {
			todos.size
		}

		val completedTodosCount by computed {
			todos.values.filter { it.isCompleted }.size
		}

		fun addTodo(todo: Todo) {
			todos.put(todo.text, todo)
		}
	}

	@Test
	fun test() {
		val store = TodoStore()

		autorun {
			println("Todos count = ${store.todosCount}")
			println("Completed todos count = ${store.completedTodosCount}")
		}

		val todo = Todo(text = "Todo 1", isCompleted = false)
		store.addTodo(todo)
		store.todos.values.first().isCompleted = true
	}
}