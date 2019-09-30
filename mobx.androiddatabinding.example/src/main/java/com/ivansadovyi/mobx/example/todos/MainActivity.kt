package com.ivansadovyi.mobx.example.todos

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ivansadovyi.mobx.example.todos.databinding.ActivityMainBinding
import com.ivansadovyi.mobx.example.todos.databinding.MobxDataBindingUtil

class MainActivity : AppCompatActivity() {

	private val todoStore = TodoStore()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
		binding.todoStore = todoStore
		MobxDataBindingUtil.observe(binding, this)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.addTodo -> showAddTodoDialog()
		}

		return true
	}

	private fun showAddTodoDialog() {
		AlertDialog.Builder(this)
			.setTitle(R.string.add_todo)
			.setView(R.layout.dialog_add_todo)
			.setPositiveButton(android.R.string.ok) { dialogInterface, _ ->
				val dialog = dialogInterface as AlertDialog
				val editText = dialog.findViewById<EditText>(R.id.todoText)
				val todoText = editText?.text?.toString()
				if (!todoText.isNullOrBlank()) {
					todoStore.addTodo(todoText, isCompleted = false)
				}
			}
			.setNegativeButton(android.R.string.cancel, null)
			.show()
	}
}
