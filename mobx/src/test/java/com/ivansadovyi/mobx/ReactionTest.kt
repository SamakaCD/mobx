package com.ivansadovyi.mobx

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import org.junit.Test

class ReactionTest {

	@Test
	fun testReactionSideEffectShouldBeCalledOnDataChange() {
		val observable = TrackedObservable(0)
		val sideEffectCallback = mock<ReactionSideEffectCallback<Int>>()
		reaction(
			dataCallback = { observable.value },
			sideEffectCallback = sideEffectCallback
		)

		verifyZeroInteractions(sideEffectCallback)

		val desiredObservableValue = 1
		observable.value = desiredObservableValue
		verify(sideEffectCallback).invoke(desiredObservableValue)
	}

	@Test
	fun testReactionSideEffectShouldBeCalledOnMultipleDataChange() {
		val observableA = TrackedObservable(0)
		val observableB = TrackedObservable(2)
		val sideEffectCallback = mock<ReactionSideEffectCallback<Int>>()
		reaction(
			dataCallback = { observableA.value + observableB.value },
			sideEffectCallback = sideEffectCallback
		)

		verifyZeroInteractions(sideEffectCallback)

		observableA.value = 1
		verify(sideEffectCallback).invoke(observableA.value + observableB.value)
	}

	@Test
	fun testReactionSideEffectShouldBeFiredImmediately() {
		val observable = TrackedObservable(0)
		val sideEffectCallback = mock<ReactionSideEffectCallback<Int>>()
		reaction(
			dataCallback = { observable.value },
			sideEffectCallback = sideEffectCallback,
			fireImmediately = true
		)

		verify(sideEffectCallback).invoke(observable.value)
	}

	@Test
	fun testReactionSideEffectShouldNotBeCalledWhenDataIsNotChanged() {
		val observable = TrackedObservable(0)
		val sideEffectCallback = mock<ReactionSideEffectCallback<Int>>()
		reaction(
			dataCallback = { observable.value },
			sideEffectCallback = sideEffectCallback
		)

		verifyZeroInteractions(sideEffectCallback)

		observable.value = 0
		verifyZeroInteractions(sideEffectCallback)
	}

	@Test
	fun testReactionSideEffectShouldNotBeCalledAfterDispose() {
		val observable = TrackedObservable(0)
		val sideEffectCallback = mock<ReactionSideEffectCallback<Int>>()
		val reaction = reaction(
			dataCallback = { observable.value },
			sideEffectCallback = sideEffectCallback
		)

		verifyZeroInteractions(sideEffectCallback)

		val desiredValue1 = 1
		observable.value = desiredValue1
		verify(sideEffectCallback).invoke(desiredValue1)

		reaction.dispose()
		observable.value++
		verifyNoMoreInteractions(sideEffectCallback)
	}
}
