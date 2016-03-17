package com.loopico.videocanvas;

/**
 * Created by yacovyitzhak on 16/03/2016.
 */
public class SystemState {

    private long cursorID ;
    private LayerType currentLayerColor;
    private int currentColor;
    private Cursor lastAddedCursor;


    private static SystemState systemState;
    public SystemState(){
        currentLayerColor = LayerType.RED;
        cursorID = 0;
    }
    //Singleton
    public  SystemState Instance(){
        return systemState;
    }
    public long getCursorID() {
        return cursorID;
    }

    public long addCursorID() {
        return cursorID++;
    }

    public LayerType getCurrentLayerColor() {
        return currentLayerColor;
    }

    public void setCurrentLayerColor(LayerType currentLayerColor) {
        this.currentLayerColor = currentLayerColor;
    }

    public int getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public Cursor getLastAddedCursor() {
        return lastAddedCursor;
    }

    public void setLastAddedCursor(Cursor lastAddedCursor) {
        this.lastAddedCursor = lastAddedCursor;
    }
}
