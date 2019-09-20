package com.mobx.example

import com.ivansadovyi.mobx.action
import com.ivansadovyi.mobx.autorun
import com.ivansadovyi.mobx.observable

class CounterStore {

	var counter by observable { 0 }

	fun increment() = action {
		counter++
	}

	fun decrement() = action {
		counter--
	}
}

fun main() {
	val store = CounterStore()

	autorun {
		println("Counter value is ${store.counter}. Enter + to increment, - to decrement.")
	}

	while (true) {
		when (readLine()) {
			"+" -> store.increment()
			"-" -> store.decrement()
		}
	}
}