package com.ivansadovyi.mobx;

import java.util.HashSet;
import java.util.Set;

public class Computed<R> implements ObservableTracker, Observer {

	private ComputedBody<R> body;
	private R memoizedResult;
	private boolean needRecalculation = true;
	private Set<Observable> children = new HashSet<>();
	private boolean isStoringTrackedObservable = false;

	public Computed(ComputedBody<R> body) {
		this.body = body;
	}

	public R get() {
		if (needRecalculation) {
			ObservableTrackerHolder.replaceTo(this, new Runnable() {
				@Override
				public void run() {
					memoizedResult = body.call();
				}
			});
		}

		ObservableTracker tracker = ObservableTrackerHolder.get();
		if (tracker != null) {
			for (Observable observable : children) {
				tracker.track(observable);
			}
		}


		return memoizedResult;
	}

	@Override
	public void track(Observable observable) {
		if (!isStoringTrackedObservable) {
			isStoringTrackedObservable = true;
			observable.subscribe(this);
			children.add(observable);
			isStoringTrackedObservable = false;
		}
	}

	@Override
	public void onChange() {
		needRecalculation = true;
	}
}
