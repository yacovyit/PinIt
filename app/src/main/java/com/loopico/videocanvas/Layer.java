package com.loopico.videocanvas;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by yacovyitzhak on 16/03/2016.
 */
public class Layer<T extends Cursor> implements ILayer<T>,ICursor<T>{


    private Map<Origin,List<T>> innerlayersMap;

    public Layer() {
        innerlayersMap = new Hashtable<Origin,List<T>>();
        //user inner layer
        innerlayersMap.put(Origin.USER, new LinkedList<T>());
        //wizzard inner layer
        innerlayersMap.put(Origin.WIZZARD, new LinkedList<T>());
    }

    public List<T> getLayer(Origin origin){
        return innerlayersMap.get(origin);
    }
    @Override
    public void add(T item){
        if (item!=null) {
            innerlayersMap.get(item.getOrigin()).add(item);
        }
    }
    @Override
    public boolean remove(T item){
        return innerlayersMap.get(item.getOrigin()).remove(item);
    }

    @Override
    public void clear(Origin origin) {
        innerlayersMap.get(origin).clear();
    }
    @Override
    public void clear(){
        for (Map.Entry<Origin, List<T>> entry : innerlayersMap.entrySet())
        {
           entry.getValue().clear();
        }
    }

}
