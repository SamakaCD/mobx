package com.ivansadovyi.mobx

typealias WhenPredicate = () -> Boolean

class When(predicate: WhenPredicate, sideEffectCallback: () -> Unit) : Disposable {

	private var reactionDisposable: Disposable? = null

	init {
		this.reactionDisposable = reaction(
			dataCallback = predicate,
			sideEffectCallback = {
				sideEffectCallback()
				this.reactionDisposable?.dispose()
			}
		)
	}

	override fun dispose() {
		this.reactionDisposable?.dispose()
	}
}
