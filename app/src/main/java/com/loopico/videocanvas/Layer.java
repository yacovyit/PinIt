package com.loopico.videocanvas;

import java.util.Collections;
import java.util.HashMap;
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
        innerlayersMap = new HashMap<>();
        //user inner layer
        innerlayersMap.put(Origin.USER, Collections.synchronizedList(new LinkedList<T>()));
        //wizard inner layer
        innerlayersMap.put(Origin.WIZARD, Collections.synchronizedList(new LinkedList<T>()));
    }

    public List<T> getLayer(Origin origin){
        return innerlayersMap.get(origin);
    }
    @Override
    public void add(T item){
        if (item!=null) {
            List<T> list = innerlayersMap.get(item.getOrigin());
            synchronized (list){
                list.add(item);
            }

        }
    }
    @Override
    public boolean remove(T item){
        List<T> list = innerlayersMap.get(item.getOrigin());
        synchronized (list){
            return list.remove(item);
        }
    }

    @Override
    public void clear(Origin origin) {
        innerlayersMap.get(origin).clear();
    }
    @Override
    public void clear(){
        for (Map.Entry<Origin, List<T>> entry : innerlayersMap.entrySet())
        {
            List<T> list = entry.getValue();
            synchronized (list)
            {
                list.clear();
            }

        }
    }

}
