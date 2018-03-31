package me.dcatcher.demonology.util;

import java.util.concurrent.Callable;

public class FactorySoulHandler implements Callable<ISoulHandler> {

    @Override
    public ISoulHandler call() throws Exception {
        return new DefaultSoulHandler();
    }
}
