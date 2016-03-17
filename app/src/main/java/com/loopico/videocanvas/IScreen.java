package com.loopico.videocanvas;

/**
 * Created by yacovyitzhak on 16/03/2016.
 */
public interface IScreen<T extends Cursor>  {
    //void showScreen();
    //void hideScreen();
    void clear();
    Layer<T> getActiveLayer();
    void setActiveLayer(LayerType type);
}
