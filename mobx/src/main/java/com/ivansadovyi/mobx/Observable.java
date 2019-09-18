package com.ivansadovyi.mobx;

public interface Observable {

	Disposable subscribe(Observer observer);
}
