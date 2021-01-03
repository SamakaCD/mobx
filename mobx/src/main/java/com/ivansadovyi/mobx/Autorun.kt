package com.ivansadovyi.mobx

typealias AutorunBody = () -> Unit

class Autorun(private val body: AutorunBody) : Disposable, ObservableTracker, Observer {

	private val childObservableDisposables = mutableSetOf<Disposable>()

	init {
		ActiveObservableTrackerHolder.runWithObservableTracker(this, this.body)
	}

	override fun dispose() {
		this.childObservableDisposables.forEach(Disposable::dispose)
	}

	override fun track(observable: TrackedObservable<*>) {
		val disposable = observable.observe(this)
		this.childObservableDisposables.add(disposable)
	}

	override fun onChange() {
		ActiveObservableTrackerHolder.runWithObservableTracker(this, this.body)
	}
}
