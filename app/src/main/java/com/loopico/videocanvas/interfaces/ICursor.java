package com.loopico.videocanvas.interfaces;

/**
 * Created by yacovyitzhak on 17/03/2016.
 */
public interface ICursor<E> {
    void add(E item);
    boolean remove(E item);
}
