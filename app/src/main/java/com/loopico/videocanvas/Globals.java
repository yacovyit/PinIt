package com.loopico.videocanvas;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by yacovyitzhak on 15/03/2016.
 */
public class Globals {

    //url's
    public static final String larryBirdUrl = "https://storage.googleapis.com/yapss/Larry%20Bird%20Ultimate%20Highlight%20Reel.mp4";
    public static final String starWarsUrl = "https://storage.googleapis.com/yapss/Star%20Wars-%20The%20Force%20Awakens%20Trailer%20(Official).mp4";
    public static final String wizzardApiUrl = "https://wizzardservices-1252.appspot.com/_ah/api/";

    //UI
    public static Bitmap targetIcon;
    public static Bitmap wizzardIcon;

    private static long cursorID ;
    private static LayerType currentLayerColor;
    private static int currentColor;
    private static Cursor lastAddedCursor;

    //public static ArrayList<Cursor> redLayerCursorList,greenLayerCursorList,blueLayerCursorList;
    //public static ArrayList<Cursor> redWizzardLayerCursorList,greenWizzardLayerCursorList,blueWizzardLayerCursorList;
    static{
        //redLayerCursorList = new ArrayList<>();
        //greenLayerCursorList = new ArrayList<>();
        //blueLayerCursorList = new ArrayList<>();

        //
        //redWizzardLayerCursorList = new ArrayList<>();
        //greenWizzardLayerCursorList = new ArrayList<>();
        //blueWizzardLayerCursorList = new ArrayList<>();
        //currentLayerColor = LayerType.RED;
        //cursorID = 0;
        //systemState = SystemState.Instance();
        //manager = Manager.Instance();
    }
    //System state
    //public static SystemState systemState ;
    //public static Manager manager;
//    public static long getCursorID() {
//        return Manager.Instance().getSystemState().getCursorID();
//    }
//    public static void addCursorID() {
//        Manager.Instance().getSystemState().addCursorID();
//    }

//    public static LayerType getCurrentLayerColor() {
//        return Manager.Instance().getSystemState().getCurrentLayerColor();
//    }
//
//    public static void setCurrentLayerColor(LayerType currentLayerColor) {
//        Manager.Instance().getSystemState().setCurrentLayerColor(currentLayerColor);
//    }

//    public static int getCurrentColor() {
//        return Manager.Instance().getSystemState().getCurrentColor();
//    }
//
//    public static void setCurrentColor(int currentColor) {
//        Manager.Instance().getSystemState().setCurrentColor(currentColor);
//    }

//    private static Cursor getLastAddedCursor() {
//        return Manager.Instance().getSystemState().getLastAddedCursor();
//    }

//    public static void setLastAddedCursor(Cursor lastAddedCursor) {
//        Manager.Instance().getSystemState().setLastAddedCursor(lastAddedCursor);
//    }






}
