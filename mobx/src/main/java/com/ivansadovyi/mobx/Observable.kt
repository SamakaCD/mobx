package com.ivansadovyi.mobx

interface Observable {

	fun observe(observer: Observer): Disposable
}
