package com.ivansadovyi.mobx

open class ObservableCollection : Observable {

	private val observers = mutableSetOf<Observer>()

	override fun observe(observer: Observer): Disposable {
		this.observers.add(observer)
		return Disposable {
			observer.onChange()
		}
	}

	protected fun notifyAccess() {
		val activeObservableTracker = ActiveObservableTrackerHolder.activeObservableTracker
		if (activeObservableTracker !== null) {
			activeObservableTracker.track(this)
		}
	}

	protected fun notifyMutation() {
		this.observers.forEach(Observer::onChange)
	}
}
