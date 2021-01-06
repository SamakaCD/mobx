package com.ivansadovyi.mobx

class ObservableSet<T>(vararg elements: T) : ObservableCollection(), MutableSet<T> {

	private val set = mutableSetOf(*elements)

	override fun add(element: T): Boolean {
		val result = this.set.add(element)
		notifyMutation()
		return result
	}

	override fun addAll(elements: Collection<T>): Boolean {
		val result = this.set.addAll(elements)
		notifyMutation()
		return result
	}

	override fun clear() {
		this.set.clear()
		notifyMutation()
	}

	override fun iterator(): MutableIterator<T> {
		notifyAccess()
		return this.set.iterator()
	}

	override fun remove(element: T): Boolean {
		val result = this.set.remove(element)
		notifyMutation()
		return result
	}

	override fun removeAll(elements: Collection<T>): Boolean {
		val result = this.set.removeAll(elements)
		notifyMutation()
		return result
	}

	override fun retainAll(elements: Collection<T>): Boolean {
		val result = this.set.retainAll(elements)
		notifyMutation()
		return result
	}

	override val size: Int
		get() {
			notifyAccess()
			return this.set.size
		}

	override fun contains(element: T): Boolean {
		notifyAccess()
		return this.set.contains(element)
	}

	override fun containsAll(elements: Collection<T>): Boolean {
		notifyAccess()
		return this.set.containsAll(elements)
	}

	override fun isEmpty(): Boolean {
		notifyAccess()
		return this.set.isEmpty()
	}
}
