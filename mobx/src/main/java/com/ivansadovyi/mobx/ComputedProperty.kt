package com.ivansadovyi.mobx

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ComputedProperty<T>(body: ComputedBody<T>): ReadOnlyProperty<Any, T> {

	private val computed = Computed(body)

	override fun getValue(thisRef: Any, property: KProperty<*>): T {
		return computed.value
	}
}
