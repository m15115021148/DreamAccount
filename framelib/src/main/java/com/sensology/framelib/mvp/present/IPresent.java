package com.sensology.framelib.mvp.present;

public interface IPresent<V> {
    void attachV(V view);

    void detachV();

    boolean hasV();
}
