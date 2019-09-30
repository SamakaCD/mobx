package com.ivansadovyi.mobx;

import javax.annotation.Nonnull;
import java.util.*;

public class ObservableHashMap<K, V> extends HashMap<K, V> implements Observable {

	private Set<Observer> observers = new HashSet<>();

	@Override
	public void clear() {
		super.clear();
		notifyObservers();
	}

	@Override
	public boolean containsKey(Object o) {
		triggerTracker();
		return super.containsKey(o);
	}

	@Nonnull
	@Override
	public Set<Entry<K, V>> entrySet() {
		triggerTracker();
		return super.entrySet();
	}

	@Override
	public boolean containsValue(Object o) {
		triggerTracker();
		return super.containsValue(o);
	}

	@Override
	public V get(Object o) {
		triggerTracker();
		return super.get(o);
	}

	@Override
	public boolean isEmpty() {
		triggerTracker();
		return super.isEmpty();
	}

	@Nonnull
	@Override
	public Set<K> keySet() {
		triggerTracker();
		return super.keySet();
	}

	@Override
	public V put(K k, V v) {
		V result = super.put(k, v);
		notifyObservers();
		return result;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		super.putAll(map);
		notifyObservers();
	}

	@Override
	public V remove(Object o) {
		V result = super.remove(o);
		notifyObservers();
		return result;
	}

	@Override
	public int size() {
		triggerTracker();
		return super.size();
	}

	@Nonnull
	@Override
	public Collection<V> values() {
		triggerTracker();
		return super.values();
	}

	public void triggerTracker() {
		ObservableTracker tracker = ObservableTrackerHolder.get();
		if (tracker != null) {
			tracker.track(this);
		}
	}

	private void notifyObservers() {
		Set<Observer> observers = new HashSet<>(this.observers);
		for (Observer observer : observers) {
			observer.onChange();
		}
	}

	@Override
	public Disposable subscribe(final Observer observer) {
		observers.add(observer);
		return new Disposable() {
			@Override
			public void dispose() {
				observers.remove(observer);
			}
		};
	}
}
