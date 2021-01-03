package com.ivansadovyi.mobx

object ActiveObservableTrackerHolder {

	private val activeObservableTrackerStack = mutableListOf<ObservableTracker>()

	val activeObservableTracker: ObservableTracker?
		get() {
			return if (activeObservableTrackerStack.isNotEmpty()) {
				this.activeObservableTrackerStack.last()
			} else {
				null
			}
		}

	fun runWithObservableTracker(observableTracker: ObservableTracker, body: () -> Unit) {
		this.activeObservableTrackerStack.add(observableTracker)
		body()
		this.activeObservableTrackerStack.remove(observableTracker)
	}
}
