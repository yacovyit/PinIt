package com.loopico.videocanvas;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by yacovyitzhak on 17/03/2016.
 */
public class Manager {
    public enum ScreenNames {
        STAR_WARS,LARRY_BIRD
    }
    private long cursorID ;

    private Map<ScreenNames,Screen> screensMap;
    private ScreenNames activeScreen;
    private static Manager manager;
    private Manager(){
        screensMap = new Hashtable<>();
        screensMap.put(ScreenNames.STAR_WARS,new VideoScreen(Globals.starWarsUrl));
        screensMap.put(ScreenNames.LARRY_BIRD,new VideoScreen(Globals.larryBirdUrl));

        activeScreen = ScreenNames.STAR_WARS;

    }
    public static Manager Instance(){
        if (manager == null){
            manager = new Manager();
        }
        return manager;
    }
    public void clear(){
        screensMap.get(activeScreen).clear();
    }
    public Screen getCurrentScreen(){
        return screensMap.get(activeScreen);
    }
    public void setCurrentScreen(ScreenNames activeScreen){
        this.activeScreen = activeScreen;

    }
    public Layer getCurrentLayer(){
        return screensMap.get(activeScreen).getActiveLayer();
    }

    public int getCurrentColor() {
        return getCurrentScreen().getCurrentColor();
    }
    public void setCurrentColor(int currentColor) {
        getCurrentScreen().setCurrentColor(currentColor);
    }
    public void initColor(int initColor) {
        for (Map.Entry<ScreenNames,Screen> entry : screensMap.entrySet())
        {
            entry.getValue().setCurrentColor(initColor);
        }

    }
    public void setCurrentLayerType(LayerType layerType) {
        getCurrentScreen().setActiveLayer(layerType);
    }
    public LayerType getCurrentLayerType() {
        return getCurrentScreen().getCurrentLayerType();
    }
    public long getCursorID() {
        return cursorID;
    }

    public void addCursorID() {
        cursorID++;
    }
    public <T extends Cursor> void add (T item) {

        getCurrentScreen().add(item);
    }
}
