package com.ivansadovyi.mobx

import org.junit.Test

class WhenTest {

	@Test
	fun testWhen() {
		val a = TrackedObservable(0)

		whenThen(
			predicate = { a.value == 3 },
			sideEffectCallback = {
				println("a is ${a.value}!!!")
			}
		)

		a.value = a.value + 1
		a.value = a.value + 1
		a.value = a.value + 1
		a.value = a.value + 1
		a.value = a.value + 1
	}
}
