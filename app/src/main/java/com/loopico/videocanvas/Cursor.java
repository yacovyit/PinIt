package com.loopico.videocanvas;

/**
 * Created by yacovyitzhak on 15/03/2016.
 */
public class Cursor {



    private long id;
    private int x;
    private int y;
    private LayerType layerType;
    private Origin origin;


    public Cursor(int x, int y, LayerType layerType,Origin origin,long id) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.origin = origin;
        this.layerType = layerType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public LayerType getLayerColor() {
        return layerType;
    }

    public void setLayerColor(LayerType layerColor) {
        this.layerType = layerColor;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }
}
