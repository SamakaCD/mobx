package com.ivansadovyi.mobx

class ObservableList<T>(vararg elements: T) : ObservableCollection(), MutableList<T> {

	private val list = mutableListOf(*elements)

	override val size: Int
		get() {
			notifyAccess()
			return list.size
		}

	override fun contains(element: T): Boolean {
		notifyAccess()
		return list.contains(element)
	}

	override fun containsAll(elements: Collection<T>): Boolean {
		notifyAccess()
		return list.containsAll(elements)
	}

	override fun get(index: Int): T {
		notifyAccess()
		return list[index]
	}

	override fun indexOf(element: T): Int {
		notifyAccess()
		return list.indexOf(element)
	}

	override fun isEmpty(): Boolean {
		notifyAccess()
		return list.isEmpty()
	}

	override fun iterator(): MutableIterator<T> {
		notifyAccess()
		return list.iterator()
	}

	override fun lastIndexOf(element: T): Int {
		notifyAccess()
		return list.lastIndexOf(element)
	}

	override fun add(element: T): Boolean {
		val result = list.add(element)
		notifyMutation()
		return result
	}

	override fun add(index: Int, element: T) {
		val result = list.add(index, element)
		notifyMutation()
		return result
	}

	override fun addAll(index: Int, elements: Collection<T>): Boolean {
		val result = list.addAll(index, elements)
		notifyMutation()
		return result
	}

	override fun addAll(elements: Collection<T>): Boolean {
		val result = list.addAll(elements)
		notifyMutation()
		return result
	}

	override fun clear() {
		list.clear()
		notifyMutation()
	}

	override fun listIterator(): MutableListIterator<T> {
		notifyAccess()
		return list.listIterator()
	}

	override fun listIterator(index: Int): MutableListIterator<T> {
		notifyAccess()
		return list.listIterator(index)
	}

	override fun remove(element: T): Boolean {
		val result = list.remove(element)
		notifyMutation()
		return result
	}

	override fun removeAll(elements: Collection<T>): Boolean {
		val result = list.removeAll(elements)
		notifyMutation()
		return result
	}

	override fun removeAt(index: Int): T {
		val result = list.removeAt(index)
		notifyMutation()
		return result
	}

	override fun retainAll(elements: Collection<T>): Boolean {
		val result = list.retainAll(elements)
		notifyMutation()
		return result
	}

	override fun set(index: Int, element: T): T {
		val result = list.set(index, element)
		notifyMutation()
		return result
	}

	override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
		val result = list.subList(fromIndex, toIndex)
		notifyAccess()
		return result
	}
}
