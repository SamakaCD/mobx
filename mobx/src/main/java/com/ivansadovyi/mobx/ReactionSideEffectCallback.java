package com.ivansadovyi.mobx;

public interface ReactionSideEffectCallback<R> {

	void onReaction(R data);
}
