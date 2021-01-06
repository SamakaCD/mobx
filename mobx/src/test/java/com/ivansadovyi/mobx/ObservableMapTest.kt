package com.ivansadovyi.mobx

import org.junit.Test

class ObservableMapTest {

	@Test
	fun testObservableMap() {
		val map = observableMapOf(
			1 to "one"
		)

		autorun {
			println("Map size: ${map.size}")
		}

		map[2] = "two"
		map[3] = "three"
		map[1] = "uno"
	}
}
