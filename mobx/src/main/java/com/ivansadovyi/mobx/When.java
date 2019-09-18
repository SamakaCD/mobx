package com.ivansadovyi.mobx;

public class When implements Disposable {

	private Disposable disposable;

	public When(final WhenPredicate predicate, final Runnable sideEffect) {
		disposable = new Reaction<>(
				new ReactionDataCallback<Boolean>() {
					@Override
					public Boolean getData() {
						return predicate.test();
					}
				},
				new ReactionSideEffectCallback<Boolean>() {
					@Override
					public void onReaction(Boolean data) {
						if (data) {
							dispose();
							sideEffect.run();
						}
					}
				}
		);
	}

	@Override
	public void dispose() {
		disposable.dispose();
	}
}
