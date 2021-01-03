package com.ivansadovyi.mobx

import org.junit.Test

class CounterStore {
	var value by observable { 0 }

	fun increment() {
		value++
	}
}

class CounterTest {

	@Test
	fun test() {
		val store = CounterStore()

		autorun {
			println("Counter value: ${store.value}")
		}

		whenThen(
			predicate = { store.value > 3 },
			sideEffectCallback = {
				println("Store value is greater than 3")
			}
		)

		store.increment()
		store.increment()
		store.increment()
		store.increment()
		store.increment()
		store.increment()
		store.increment()
	}
}
