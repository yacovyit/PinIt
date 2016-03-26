package com.loopico.videocanvas.interfaces;

import com.loopico.videocanvas.pinitclasses.Cursor;
import com.loopico.videocanvas.pinitclasses.Layer;
import com.loopico.videocanvas.enums.LayerType;

/**
 * Created by yacovyitzhak on 16/03/2016.
 */
public interface IScreen<T extends Cursor>  {

    void clear();
    Layer<T> getActiveLayer();
    void setActiveLayer(LayerType type);
}
