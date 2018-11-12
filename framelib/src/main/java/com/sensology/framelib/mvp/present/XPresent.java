package com.sensology.framelib.mvp.present;

import com.sensology.framelib.mvp.view.IView;

import java.lang.ref.WeakReference;

public class XPresent<V extends IView> implements IPresent<V> {
    private WeakReference<V> v;

    @Override
    public void attachV(V view) {
        v = new WeakReference<V>(view);
    }

    @Override
    public void detachV() {
        if (v.get() != null) {
            v.clear();
        }
        v = null;
    }

    @Override
    public boolean hasV() {
        return v != null && v.get() != null;
    }

    protected V getV() {
        if (v == null || v.get() == null) {
            throw new MvpViewNotAttachedException();
        }
        return v.get();
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        private MvpViewNotAttachedException() {
            super("not binding view");
        }
    }

}
