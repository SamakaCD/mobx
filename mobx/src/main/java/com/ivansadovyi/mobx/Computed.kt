package com.ivansadovyi.mobx

typealias ComputedBody<T> = () -> T

class Computed<T>(private val body: ComputedBody<T>) : ObservableTracker, Observer {

	val value: T
		get() {
			if (this.needsRecalculation || this.memoizedResult == null) {
				ActiveObservableTrackerHolder.runWithObservableTracker(this) {
					this.memoizedResult = body()
					this.needsRecalculation = false
				}
			}

			val activeObservableTracker = ActiveObservableTrackerHolder.activeObservableTracker
			if (activeObservableTracker != null) {
				this.childObservables.forEach(activeObservableTracker::track)
			}

			return memoizedResult!!
		}

	private var memoizedResult: T? = null
	private var needsRecalculation = true
	private val childObservables = mutableSetOf<Observable>()

	override fun track(observable: Observable) {
		observable.observe(this)
		this.childObservables.add(observable)
	}

	override fun onChange() {
		this.needsRecalculation = true
	}
}
