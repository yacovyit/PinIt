package com.loopico.videocanvas.pinitclasses;

import android.content.Context;
import android.content.SharedPreferences;

import com.loopico.videocanvas.app.Globals;
import com.loopico.videocanvas.enums.LayerType;
import com.loopico.videocanvas.enums.ScreenName;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by yacovyitzhak on 17/03/2016.
 */
public class Manager {

    //private long cursorID ;

    private Map<ScreenName,Screen> screensMap;
    private ScreenName activeScreen;
    private static Manager manager;
    private Manager(){
        screensMap = new HashMap<>();
        screensMap.put(ScreenName.STAR_WARS,new VideoScreen(Globals.starWarsUrl));
        screensMap.put(ScreenName.LARRY_BIRD,new VideoScreen(Globals.larryBirdUrl));

        activeScreen = ScreenName.STAR_WARS;

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
    public void setCurrentScreen(ScreenName activeScreen){
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
        for (Map.Entry<ScreenName,Screen> entry : screensMap.entrySet())
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
    public long getCursorID(Context ctx) {

        SharedPreferences prefs = ctx.getSharedPreferences("MY_PREFS_NAME", ctx.MODE_PRIVATE);
        Integer id = prefs.getInt("id", 0);
        return id;
    }

    public void addCursorID(Context ctx) {
        //cursorID++;

        SharedPreferences prefs = ctx.getSharedPreferences("MY_PREFS_NAME", ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Integer id = prefs.getInt("id", 0);
        id++;
        editor.putInt("id", id);
        editor.commit();

    }
    public <T extends Cursor> void add (T item) {

        getCurrentScreen().add(item);
    }
    public <T extends Cursor> void remove (T item) {

        getCurrentScreen().remove(item);
    }
    public ScreenName getActiveScreen() {
        return activeScreen;
    }

    public void setActiveScreen(ScreenName activeScreen) {
        this.activeScreen = activeScreen;
    }
}
