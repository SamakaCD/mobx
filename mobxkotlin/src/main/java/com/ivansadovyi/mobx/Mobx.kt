package com.ivansadovyi.mobx

import com.ivansadovyi.mobx.Disposable
import com.ivansadovyi.mobx.MobxOperators

fun autorun(body: () -> Unit): Disposable {
	return MobxOperators.autorun(body)
}

fun <R> reaction(data: () -> R, sideEffect: (R) -> Unit): Disposable {
	return MobxOperators.reaction(data, sideEffect)
}

fun whenThen(predicate: () -> Boolean, sideEffect: () -> Unit): Disposable {
	return MobxOperators.`when`(predicate, sideEffect)
}

fun <T> observable(valueSupplier: () -> T): TrackedObservableVariable<T> {
	return TrackedObservableVariable(valueSupplier())
}

fun <T> observable(value: T): TrackedObservableVariable<T> {
	return TrackedObservableVariable(value)
}

fun <T> computed(body: () -> T): ComputedVariable<T> {
	return ComputedVariable(body)
}

fun action(body: () -> Unit) {
	MobxOperators.action(body)
}

fun action(payload: Any?, body: () -> Unit) {
	MobxOperators.action(payload, body)
}