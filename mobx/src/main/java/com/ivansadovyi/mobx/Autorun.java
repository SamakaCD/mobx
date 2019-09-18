package com.ivansadovyi.mobx;

import java.util.HashSet;
import java.util.Set;

public class Autorun implements Disposable, ObservableTracker, Observer {

	private Runnable body;
	private Set<Disposable> disposables = new HashSet<>();

	public Autorun(Runnable body) {
		this.body = body;
		runBody();
	}

	@Override
	public void dispose() {
		for (Disposable disposable : disposables) {
			disposable.dispose();
		}
	}

	@Override
	public void track(Observable observable) {
		Disposable disposable = observable.subscribe(this);
		disposables.add(disposable);
	}

	@Override
	public void onChange() {
		runBody();
	}

	private void runBody() {
		ObservableTrackerHolder.replaceTo(this, new Runnable() {
			@Override
			public void run() {
				dispose();
				body.run();
			}
		});
	}
}
