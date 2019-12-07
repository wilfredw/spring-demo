package com.wei.spring.app.cache;

/**
 * Created by viruser on 2019/12/5.
 */
public abstract class BaseCache<T> {
    public abstract T getData();

    private void reload() {
        ;
    }

    protected abstract void load();
}
