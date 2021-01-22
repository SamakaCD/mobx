package com.ivansadovyi.mobx

typealias WhenPredicate = () -> Boolean
typealias WhenSideEffectCallback = () -> Unit

class When(predicate: WhenPredicate, sideEffectCallback: WhenSideEffectCallback) : Disposable {

	private var reactionDisposable: Disposable? = null

	init {
		this.reactionDisposable = reaction(
			dataCallback = predicate,
			sideEffectCallback = {
				if (it) {
					sideEffectCallback()
					this.reactionDisposable?.dispose()
				}
			},
			fireImmediately = true
		)
	}

	override fun dispose() {
		this.reactionDisposable?.dispose()
	}
}
