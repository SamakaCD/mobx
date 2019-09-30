package com.mobx.example

import com.ivansadovyi.mobx.*

class Todo(text: String, isCompleted: Boolean) {

	var text by observable { text }
	var isCompleted by observable { isCompleted }

	override fun toString(): String {
		return "Todo(text=$text, isCompleted=$isCompleted)"
	}
}

class TodoStore {

	sealed class Actions {
		data class Add(val text: String)

		data class Complete(val text: String)

		data class Delete(val text: String)
	}

	val todos = ObservableHashMap<String, Todo>()

	val todosCount by computed {
		todos.size
	}

	val completedTodosCount by computed {
		todos.values.filter { it.isCompleted }.size
	}

	fun addTodo(text: String) {
		action(Actions.Add(text)) {
			val todo = Todo(text, isCompleted = false)
			todos[text] = todo
		}
	}

	fun completeTodo(text: String) {
		action(Actions.Complete(text)) {
			val todo = todos[text]
			if (todo != null) {
				todo.isCompleted = true
			}
		}
	}

	fun deleteTodo(text: String) {
		action(Actions.Delete(text)) {
			todos.remove(text)
		}
	}

	fun show() {
		action {
			println("Your todos:")
			todos.forEach {
				println(it.value)
			}
		}
	}
}

const val CMD_ADD = "add"
const val CMD_COMPLETE = "complete"
const val CMD_DELETE = "delete"
const val CMD_SHOW = "show"
const val ARG_PLACEHOLDER = ""

fun main() {
	println("Todos demo ☑️")
	println("Commands:")
	println("   add <text> - ➕ adds new Todo")
	println("   complete <text> - ✔️ completes Todo")
	println("   delete <text> - ➖ deletes todo")
	println("   show - 📜 prints todos")

	val store = TodoStore()

	Action.listen {
		when (it) {
			is TodoStore.Actions.Add -> println("You've added Todo with text: ${it.text}")
			is TodoStore.Actions.Complete -> println("You've complete Todo with text: ${it.text}")
			is TodoStore.Actions.Delete -> println("You've deleted Todo with text: ${it.text}")
			else -> println("You've invoked anonymous action")
		}
	}

	autorun {
		println("Todos count = ${store.todosCount}, of which ${store.completedTodosCount} - completed")
	}

	while (true) {
		val (command, arg) = readLine().orEmpty().split(" ", limit = 2) + ARG_PLACEHOLDER
		when (command) {
			CMD_ADD -> store.addTodo(arg)
			CMD_COMPLETE -> store.completeTodo(arg)
			CMD_DELETE -> store.deleteTodo(arg)
			CMD_SHOW -> store.show()
		}
	}
}
