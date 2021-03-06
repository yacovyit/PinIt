package com.loopico.videocanvas.pinitclasses;

import com.loopico.videocanvas.enums.LayerType;
import com.loopico.videocanvas.interfaces.ICursor;
import com.loopico.videocanvas.interfaces.IScreen;
import com.loopico.videocanvas.pinitclasses.Cursor;
import com.loopico.videocanvas.pinitclasses.Layer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yacovyitzhak on 16/03/2016.
 */
public class Screen<T extends Cursor> implements IScreen<T>,ICursor<T> {


    private Map<LayerType,Layer<T>> layers;
    private LayerType activeLayerType;
    private int currentColor;

    public Screen() {
        this.layers = new HashMap<>();
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

    @Override
    public void add(T item) {
        if (item!=null) {
            layers.get(item.getLayerColor()).add(item);
        }
    }
    @Override
    public boolean remove(T item) {
        if (item!=null) {
           return  layers.get(item.getLayerColor()).remove(item);
        }
        return false;
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
