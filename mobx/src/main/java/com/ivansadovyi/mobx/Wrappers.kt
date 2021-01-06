package com.ivansadovyi.mobx

fun autorun(body: AutorunBody): Autorun {
	return Autorun(body)
}

fun <T> reaction(
	dataCallback: ReactionDataCallback<T>,
	sideEffectCallback: ReactionSideEffectCallback<T>,
	fireImmediately: Boolean = false
): Reaction<T> {
	return Reaction(dataCallback, sideEffectCallback, fireImmediately)
}

fun whenThen(
	predicate: WhenPredicate,
	sideEffectCallback: () -> Unit
): When {
	return When(predicate, sideEffectCallback)
}

fun <T> observable(valueSupplier: () -> T): TrackedObservableProperty<T> {
	return TrackedObservableProperty(valueSupplier())
}

fun <T> observable(value: T): TrackedObservableProperty<T> {
	return TrackedObservableProperty(value)
}

fun <T> computed(body: ComputedBody<T>): ComputedProperty<T> {
	return ComputedProperty(body)
}

fun <T> computedFunction(body: ComputedBody<T>): () -> T {
	val computed = Computed(body)
	return {
		computed.value
	}
}

fun <T> observableListOf(vararg elements: T): ObservableList<T> {
	return ObservableList(*elements)
}
