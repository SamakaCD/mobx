package com.ivansadovyi.mobx;

import java.util.HashSet;
import java.util.Set;

public class Autorun implements Disposable, ObservableTracker, Observer {

	private Runnable body;
	private Set<Disposable> childObservableDisposables = new HashSet<>();
	private Disposable actionNestingListenerDisposable;
	private boolean hasPendingBodyInvocation = false;

	public Autorun(Runnable body) {
		this.body = body;
		actionNestingListenerDisposable = ActionManager.listenNestingChanges(actionNestingListener);
		runBody();
	}

	@Override
	public void dispose() {
		disposeChildObservables();
		actionNestingListenerDisposable.dispose();
	}

	@Override
	public void track(Observable observable) {
		Disposable disposable = observable.subscribe(this);
		childObservableDisposables.add(disposable);
	}

	@Override
	public void onChange() {
		if (ActionManager.getCurrentNesting() == 0) {
			runBody();
		} else {
			hasPendingBodyInvocation = true;
		}
	}

	private void disposeChildObservables() {
		for (Disposable disposable : childObservableDisposables) {
			disposable.dispose();
		}
	}

	private void runBody() {
		ObservableTrackerHolder.replaceTo(this, new Runnable() {
			@Override
			public void run() {
				disposeChildObservables();
				body.run();
			}
		});
	}

	private ActionManager.Listener actionNestingListener = new ActionManager.Listener() {
		@Override
		public void onNestingChange(int nesting) {
			if (nesting == 0 && hasPendingBodyInvocation) {
				runBody();
			}
		}
	};
}
