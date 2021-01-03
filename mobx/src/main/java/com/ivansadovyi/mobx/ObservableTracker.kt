package com.ivansadovyi.mobx

interface ObservableTracker {

	fun track(observable: TrackedObservable<*>)
}
