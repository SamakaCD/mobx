package com.ivansadovyi.mobx;

class ObservableTrackerHolder {

	private static ObservableTracker current = null;

	static ObservableTracker get() {
		return current;
	}

	static void replaceTo(ObservableTracker target, Runnable transaction) {
		ObservableTracker prevTracker = current;
		current = target;
		transaction.run();
		current = prevTracker;
	}
}
