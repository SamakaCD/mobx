package com.ivansadovyi.mobx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ObservableArrayList<E> extends ArrayList<E> implements Observable {

	private Set<Observer> observers = new HashSet<>();

	@Override
	public boolean add(E element) {
		boolean result = super.add(element);
		notifyObservers();
		return result;
	}

	@Override
	public void add(int index, E element) {
		super.add(index, element);
		notifyObservers();
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean result = super.addAll(collection);
		notifyObservers();
		return result;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		return super.addAll(index, collection);
	}

	@Override
	public void clear() {
		super.clear();
		notifyObservers();
	}

	@Override
	public boolean contains(Object o) {
		triggerTracker();
		return super.contains(o);
	}

	@Override
	public E get(int i) {
		triggerTracker();
		return super.get(i);
	}

	@Override
	public boolean isEmpty() {
		triggerTracker();
		return super.isEmpty();
	}

	@Override
	public E remove(int i) {
		E result = super.remove(i);
		notifyObservers();
		return result;
	}

	@Override
	public boolean remove(Object o) {
		boolean result = super.remove(o);
		notifyObservers();
		return result;
	}

	@Override
	public E set(int index, E element) {
		E result = super.set(index, element);
		notifyObservers();
		return result;
	}

	@Override
	public int size() {
		triggerTracker();
		return super.size();
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
