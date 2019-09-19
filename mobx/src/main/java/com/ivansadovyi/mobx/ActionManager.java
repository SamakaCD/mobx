package com.ivansadovyi.mobx;

import java.util.HashSet;

class ActionManager {

    interface Listener {

        void onNestingChange(int nesting);
    }

    private static int currentNesting = 0;
    private static HashSet<Listener> listeners = new HashSet<>();

    static int getCurrentNesting() {
        return currentNesting;
    }

    static Disposable listenNestingChanges(final Listener listener) {
        listeners.add(listener);
        return new Disposable() {
            @Override
            public void dispose() {
                listeners.remove(listener);
            }
        };
    }

    static void runAsAction(Runnable runnable) {
        currentNesting++;
        notifyNestingListeners();

        runnable.run();

        currentNesting--;
        notifyNestingListeners();
    }

    private static void notifyNestingListeners() {
        for (Listener listener : listeners) {
            listener.onNestingChange(currentNesting);
        }
    }
}
