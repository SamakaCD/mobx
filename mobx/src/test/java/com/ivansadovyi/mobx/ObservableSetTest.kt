package com.ivansadovyi.mobx

import org.junit.Test

class ObservableSetTest {

	@Test
	fun testObservableSet() {
		val set = observableSetOf(1)

		autorun {
			println("Set size is ${set.size}")
		}

		set.add(2)
		set.add(3)
		set.add(1)
	}
}
