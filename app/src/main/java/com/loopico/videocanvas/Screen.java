package com.loopico.videocanvas;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by yacovyitzhak on 16/03/2016.
 */
public class Screen<T extends Cursor> implements IScreen{


    private Map<LayerType,Layer<T>> layers;
    private LayerType activeLayerType;
    private int currentColor;

    public Screen() {
        this.layers = new Hashtable<>();
        layers.put(LayerType.RED, new Layer<T>());
        layers.put(LayerType.GREEN, new Layer<T>());
        layers.put(LayerType.BLUE, new Layer<T>());
        activeLayerType = LayerType.RED;

    }


    @Override
    public void clear() {
        for (Map.Entry<LayerType,Layer<T>> entry : layers.entrySet())
        {
            entry.getValue().clear();
        }
    }

    @Override
    public Layer getActiveLayer() {
        return layers.get(activeLayerType);
    }

    @Override
    public void setActiveLayer(LayerType type) {
        activeLayerType = type;
    }

    public void add(T item) {
        if (item!=null) {
            layers.get(item.getLayerColor()).add(item);
        }
    }
    public int getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public LayerType getCurrentLayerType() {
        return activeLayerType;
    }

    public void setCurrentLayerType(LayerType layerType) {
        this.activeLayerType = layerType;
    }

}
