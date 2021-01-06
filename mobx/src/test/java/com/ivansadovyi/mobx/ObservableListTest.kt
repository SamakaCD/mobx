package com.ivansadovyi.mobx

import org.junit.Test

class ObservableListTest {

	@Test
	fun testObservableList() {
		val list = observableListOf<Int>()

		autorun {
			println("List size it ${list.size}")
		}

		list.add(1)
		list.add(2)
		list.add(3)
	}
}
