package com.ivansadovyi.mobx

import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.spy

class AutorunTest {

	@Test
	fun testAutorunShouldBeCalledImmediately() {
		val autorunBody = spy { }
		autorun(autorunBody)

		verify(autorunBody).invoke()
	}

	@Test
	fun testAutorunShouldBeCalledImmediatelyAndOnObservableChange() {
		val observable = TrackedObservable(0)
		val autorunBody = spy<AutorunBody> { observable.value }
		autorun(autorunBody)

		verify(autorunBody).invoke()
		clearInvocations(autorunBody)

		observable.value = 1

		verify(autorunBody).invoke()
	}

	@Test
	fun testAutorunShouldNotBeCalledAfterDispose() {
		val observable = TrackedObservable(0)
		val autorunBody = spy<AutorunBody> { observable.value }
		val autorun = autorun(autorunBody)

		verify(autorunBody).invoke()
		clearInvocations(autorunBody)

		observable.value++

		verify(autorunBody).invoke()
		clearInvocations(autorunBody)

		autorun.dispose()
		observable.value++

		verify(autorunBody, never()).invoke()
	}
}
