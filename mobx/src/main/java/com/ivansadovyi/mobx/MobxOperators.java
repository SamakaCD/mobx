package com.ivansadovyi.mobx;

public class MobxOperators {

	public static Disposable autorun(Runnable body) {
		return new Autorun(body);
	}

	public static <R> Disposable reaction(ReactionDataCallback<R> dataCallback, ReactionSideEffectCallback<R> sideEffectCallback) {
		return new Reaction<>(dataCallback, sideEffectCallback);
	}

	public static Disposable when(WhenPredicate predicate, Runnable sideEffect) {
		return new When(predicate, sideEffect);
	}

	public static <T> Computed<T> computed(ComputedBody<T> body) {
		return new Computed<>(body);
	}
}
