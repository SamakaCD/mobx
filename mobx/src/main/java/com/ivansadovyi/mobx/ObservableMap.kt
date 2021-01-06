package com.ivansadovyi.mobx

class ObservableMap<K, V>(vararg pairs: Pair<K, V>): ObservableCollection(), MutableMap<K, V> {

	private val map = mutableMapOf(*pairs)

	override val size: Int
		get() {
			notifyAccess()
			return this.map.size
		}

	override fun containsKey(key: K): Boolean {
		notifyAccess()
		return this.map.containsKey(key)
	}

	override fun containsValue(value: V): Boolean {
		notifyAccess()
		return this.map.containsValue(value)
	}

	override fun get(key: K): V? {
		notifyAccess()
		return this.map[key]
	}

	override fun isEmpty(): Boolean {
		notifyAccess()
		return this.map.isEmpty()
	}

	override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
		get() {
			notifyAccess()
			return this.map.entries
		}

	override val keys: MutableSet<K>
		get() {
			notifyAccess()
			return this.map.keys
		}

	override val values: MutableCollection<V>
		get() {
			notifyAccess()
			return this.map.values
		}

	override fun clear() {
		this.map.clear()
		notifyMutation()
	}

	override fun put(key: K, value: V): V? {
		val result = this.map.put(key, value)
		notifyMutation()
		return result
	}

	override fun putAll(from: Map<out K, V>) {
		this.map.putAll(from)
		notifyMutation()
	}

	override fun remove(key: K): V? {
		val result = this.map.remove(key)
		notifyMutation()
		return result
	}

}
