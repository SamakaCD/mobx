package com.ivansadovyi.mobx;

import java.util.HashSet;
import java.util.Set;

public class Action {

	public interface Listener {

		void onAction(Object payload);
	}

	private static Set<Listener> listeners = new HashSet<>();

	public static Disposable listen(final Listener listener) {
		listeners.add(listener);
		return new Disposable() {
			@Override
			public void dispose() {
				listeners.remove(listener);
			}
		};
	}

	public Action(Runnable body) {
		this(null, body);
	}

	public Action(Object payload, Runnable body) {
		ActionManager.runAsAction(body);
		notifyListeners(payload);
	}

	private static void notifyListeners(Object payload) {
		for (Listener listener : listeners) {
			listener.onAction(payload);
		}
	}
}
