package com.ivansadovyi.mobx.example.todos.databinding;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.ivansadovyi.mobx.Disposable;
import com.ivansadovyi.mobx.MobxOperators;

public class MobxDataBindingUtil {

	public static Disposable observe(final ViewDataBinding binding) {
		return MobxOperators.autorun(new Runnable() {
			@Override
			public void run() {
				binding.invalidateAll();
				binding.executePendingBindings();
			}
		});
	}

	public static Disposable observe(ViewDataBinding binding, LifecycleOwner lifecycleOwner) {
		final Disposable disposable = observe(binding);
		lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
			@Override
			public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
				if (event == Lifecycle.Event.ON_DESTROY) {
					disposable.dispose();
				}
			}
		});

		return disposable;
	}
}
