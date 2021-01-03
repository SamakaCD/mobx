package com.ivansadovyi.mobx

import org.junit.Test

class AutorunTest {

	@Test
	fun testAutorun() {
		val observable = TrackedObservable(0)

		val disposable = autorun {
			println("New observable value: ${observable.value}")
		}

		observable.value = observable.value + 1
		observable.value = 3

		disposable.dispose()

		observable.value = 100500
	}
}
