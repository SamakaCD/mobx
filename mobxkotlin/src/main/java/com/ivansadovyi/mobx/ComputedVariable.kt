package com.ivansadovyi.mobx

import com.ivansadovyi.mobx.MobxOperators
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ComputedVariable<T>(body: () -> T) : ReadOnlyProperty<Any, T> {

	private val computed = MobxOperators.computed(body)

	override fun getValue(thisRef: Any, property: KProperty<*>): T {
		return computed.get()
	}
}