package com.ivansadovyi.mobx;

public class Reaction<R> implements Disposable {

	private Disposable disposable;
	private boolean wasFirstAutorunOccurred = false;

	public Reaction(final ReactionDataCallback<R> dataCallback, final ReactionSideEffectCallback<R> sideEffectCallback) {
		disposable = new Autorun(new Runnable() {
			@Override
			public void run() {
				R data = dataCallback.getData();

				if (wasFirstAutorunOccurred) {
					sideEffectCallback.onReaction(data);
				}

				wasFirstAutorunOccurred = true;
			}
		});
	}

	@Override
	public void dispose() {
		disposable.dispose();
	}
}
