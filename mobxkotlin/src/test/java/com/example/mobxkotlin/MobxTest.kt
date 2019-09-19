package com.example.mobxkotlin

import com.ivansadovyi.mobx.Action
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

		sealed class Actions {
			data class AddTodoAndComplete(val todo: Todo)
		}

		val todos = ObservableHashMap<String, Todo>()

		val todosCount by computed {
			todos.size
		}

		val completedTodosCount by computed {
			todos.values.filter { it.isCompleted }.size
		}

		fun addTodoAndComplete(todo: Todo) {
			action(Actions.AddTodoAndComplete(todo)) {
				todos[todo.text] = todo
				todo.isCompleted = true
			}
		}
	}

	@Test
	fun test() {
		val store = TodoStore()

		Action.listen {
			println("Action was invoked: $it")
		}

		autorun {
			println("Todos count = ${store.todosCount}")
			println("Completed todos count = ${store.completedTodosCount}")
		}

		val todo = Todo("Todo 1", false)
		store.addTodoAndComplete(todo)
	}
}