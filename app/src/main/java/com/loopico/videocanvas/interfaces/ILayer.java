package com.loopico.videocanvas.interfaces;

import com.loopico.videocanvas.enums.Origin;

/**
 * Created by yacovyitzhak on 16/03/2016.
 */
public interface ILayer<E>  {
    void clear(Origin origin);
    void clear();
    //TODO add layers
}
