package com.ivansadovyi.mobx;

public class Action {

    public Action(Runnable body) {
        ActionManager.runAsAction(body);
    }
}
