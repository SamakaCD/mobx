package com.ivansadovyi.mobx

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class TrackedObservableProperty<T>(value: T) : ReadWriteProperty<Any, T> {

	private val observable = TrackedObservable(value)

	override fun getValue(thisRef: Any, property: KProperty<*>): T {
		return observable.value
	}

	override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
		observable.value = value
	}
}
