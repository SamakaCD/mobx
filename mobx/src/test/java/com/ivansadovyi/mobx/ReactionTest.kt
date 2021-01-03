package com.ivansadovyi.mobx

import org.junit.Test

class ReactionTest {

	@Test
	fun testReaction() {
		val a = TrackedObservable(0)
		val b = TrackedObservable(0)

		reaction(
			dataCallback = { a.value + b.value },
			sideEffectCallback = {
				println("Sum has been changed to $it")
			},
			fireImmediately = true
		)

		b.value = 1
		b.value = 1
	}
}
