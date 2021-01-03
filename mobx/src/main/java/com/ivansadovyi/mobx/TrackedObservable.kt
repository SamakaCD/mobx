package com.ivansadovyi.mobx

class TrackedObservable<T>(value: T) : Observable {

	var value: T = value
		get() {
			ActiveObservableTrackerHolder.activeObservableTracker?.track(this)
			return field
		}
		set(value) {
			field = value
			notifyObservers()
		}


	private val observers = mutableSetOf<Observer>()

	override fun observe(observer: Observer): Disposable {
		this.observers.add(observer)
		return Disposable {
			this.observers.remove(observer)
		}
	}

	private fun notifyObservers() {
		this.observers.forEach(Observer::onChange)
	}
}
