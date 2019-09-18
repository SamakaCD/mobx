package com.ivansadovyi.mobx;

import org.junit.Test;

import static com.ivansadovyi.mobx.MobxOperators.autorun;
import static org.mockito.Mockito.*;

public class AutorunTest {

	@Test
	public void testSingleAutorun() {
		final TrackedObservable<Integer> observableValue = new TrackedObservable<>(0);
		Runnable autorunCallback = spy(new Runnable() {
			@Override
			public void run() {
				observableValue.get();
			}
		});
		autorun(autorunCallback);

		verify(autorunCallback, times(1)).run();
		clearInvocations(autorunCallback);

		observableValue.set(1);

		verify(autorunCallback, times(1)).run();
	}

	@Test
	public void testMultipleAutoruns() {
		final TrackedObservable<Integer> observableValue = new TrackedObservable<>(0);
		Runnable autorunCallback1 = spy(new Runnable() {
			@Override
			public void run() {
				observableValue.get();
			}
		});
		Runnable autorunCallback2 = spy(new Runnable() {
			@Override
			public void run() {
				observableValue.get();
			}
		});
		autorun(autorunCallback1);
		autorun(autorunCallback2);

		verify(autorunCallback1, times(1)).run();
		verify(autorunCallback2, times(1)).run();
		clearInvocations(autorunCallback1, autorunCallback2);

		observableValue.set(1);

		verify(autorunCallback1, times(1)).run();
		verify(autorunCallback2, times(1)).run();
	}

	@Test
	public void testDispose() {
		final TrackedObservable<Integer> observableValue = new TrackedObservable<>(0);
		Runnable autorunCallback = spy(new Runnable() {
			@Override
			public void run() {
				observableValue.get();
			}
		});
		Disposable disposable = autorun(autorunCallback);
		clearInvocations(autorunCallback);

		disposable.dispose();
		observableValue.set(1);

		verify(autorunCallback, times(0)).run();
	}
}