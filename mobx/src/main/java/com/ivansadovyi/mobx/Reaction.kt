package com.ivansadovyi.mobx

typealias ReactionDataCallback<T> = () -> T
typealias ReactionSideEffectCallback<T> = (T) -> Unit

class Reaction<T>(
	dataCallback: ReactionDataCallback<T>,
	sideEffectCallback: ReactionSideEffectCallback<T>,
	fireImmediately: Boolean = false
) : Disposable {

	private val autorunDisposable: Disposable
	private var wasFired = false
	private var previousData: T? = null

	init {
		this.autorunDisposable = autorun {
			val data = dataCallback()
			val wasDataChanged = data != previousData

			if (!wasFired && fireImmediately) {
				sideEffectCallback(data)
			} else if (wasFired && wasDataChanged) {
				sideEffectCallback(data)
			}

			wasFired = true
			previousData = data
		}
	}

	override fun dispose() {
		this.autorunDisposable.dispose()
	}
}
