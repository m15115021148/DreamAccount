package com.sensology.framelib.mvp.loader;

import com.sensology.framelib.mvp.present.IPresent;

public interface PresenterFactory<P extends IPresent> {
    P create();//create presenter
}
