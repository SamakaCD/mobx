package com.ivansadovyi.mobx

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import org.junit.Test

class WhenTest {

	@Test
	fun testSideEffectCallbackShouldBeCalledImmediately() {
		val sideEffectCallback = mock<WhenSideEffectCallback>()
		whenThen(
			predicate = { true },
			sideEffectCallback = sideEffectCallback
		)

		verify(sideEffectCallback).invoke()
	}

	@Test
	fun testSideEffectCallbackShouldBeNeverCalled() {
		val sideEffectCallback = mock<WhenSideEffectCallback>()
		whenThen(
			predicate = { false },
			sideEffectCallback = sideEffectCallback
		)

		verifyZeroInteractions(sideEffectCallback)
	}

	@Test
	fun testSideEffectCallbackShouldBeCalledWhenPredicateBecomesTrue() {
		val observableA = TrackedObservable(0)
		val observableB = TrackedObservable(2)
		val sideEffectCallback = mock<WhenSideEffectCallback>()
		whenThen(
			predicate = { observableA.value + observableB.value == 3 },
			sideEffectCallback = sideEffectCallback
		)

		verifyZeroInteractions(sideEffectCallback)

		observableA.value = 2
		verifyZeroInteractions(sideEffectCallback)

		observableA.value = 1
		verify(sideEffectCallback).invoke()
	}

	@Test
	fun testSideEffectCallbackShouldNotBeCalledAfterDispose() {
		val observable = TrackedObservable(false)
		val sideEffectCallback = mock<WhenSideEffectCallback>()
		val whenThen = whenThen(
			predicate = { observable.value },
			sideEffectCallback = sideEffectCallback
		)

		verifyZeroInteractions(sideEffectCallback)

		whenThen.dispose()
		observable.value = true
		verifyZeroInteractions(sideEffectCallback)
	}
}
