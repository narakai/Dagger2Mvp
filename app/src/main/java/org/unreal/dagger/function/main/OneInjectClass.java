package org.unreal.dagger.function.main;

import javax.inject.Inject;
import javax.inject.Named;

public class OneInjectClass {
    public String one;

    @Inject
    public OneInjectClass(@Named("t") String one) {
        this.one = one;
    }
}
