package com.ivansadovyi.mobx

import org.junit.Test

class ComputedFunctionTest {

	@Test
	fun testComputed() {
		val a = TrackedObservable(0)
		val b = TrackedObservable(0)
		val calculateSum = computedFunction {
			println("Summing ${a.value} + ${b.value}...")
			a.value + b.value
		}

		println("sum = ${calculateSum()}")
		println("sum = ${calculateSum()}")
		println("sum = ${calculateSum()}")

		b.value = 1
		println("sum = ${calculateSum()}")
	}
}
