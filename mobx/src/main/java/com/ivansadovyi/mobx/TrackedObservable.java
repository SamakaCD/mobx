package com.ivansadovyi.mobx;

import java.util.HashSet;
import java.util.Set;

public class TrackedObservable<T> implements Observable {

	private T value;
	private Set<Observer> observers = new HashSet<>();

	public TrackedObservable(T value) {
		this.value = value;
	}

	public T get() {
		ObservableTracker tracker = ObservableTrackerHolder.get();
		if (tracker != null) {
			tracker.track(this);
		}

		return value;
	}

	public void set(T value) {
		if (this.value != value) {
			this.value = value;
			notifyObservers();
		}
	}

	private void notifyObservers() {
		Set<Observer> observers = new HashSet<>(this.observers);
		for (Observer observer : observers) {
			observer.onChange();
		}
	}

	@Override
	public Disposable subscribe(final Observer observer) {
		observers.add(observer);
		return new Disposable() {
			@Override
			public void dispose() {
				observers.remove(observer);
			}
		};
	}
}
