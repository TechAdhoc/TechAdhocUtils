package com.techadhoc.techadhocutils.features.utils;


class TaskCallback implements Runnable {

    private final Runnable task;

    private final TaskThreadCallback callback;
    private final GenericObject genericObject;

    TaskCallback(Runnable task, TaskThreadCallback callback, GenericObject test ) {
        this.task = task;
        this.callback = callback;
        this.genericObject = test;
    }

    public void run() {
        task.run();
        callback.onComplete(genericObject);
    }

}
 class GenericObject<T> {

    private T t;

    public T get() {
        return this.t;
    }

    public void set(T t1) {
        this.t = t1;
    }
}