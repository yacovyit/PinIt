package com.loopico.videocanvas.pinitclasses;

import com.loopico.videocanvas.enums.LayerType;
import com.loopico.videocanvas.enums.Origin;
import com.loopico.videocanvas.enums.ScreenName;
import com.loopico.videocanvas.web.FireBasePushIdGenerator;

import java.util.Date;

/**
 * Created by yacovyitzhak on 15/03/2016.
 */
public class Cursor {



    private long id;
    private String fireBaseClientId;



    private String fireBaseSrcClientId;
    private int x;
    private int y;

    private long createDateLong;
    private ScreenName screenName;
    private LayerType layerType;
    private Origin origin;


    public Cursor(int x, int y, LayerType layerType,Origin origin,long id,ScreenName screenName) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.origin = origin;
        this.layerType = layerType;
        this.screenName = screenName;
        this.fireBaseClientId = FireBasePushIdGenerator.generatePushId();
        createDateLong = new Date().getTime();
    }
    public Cursor(){

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
    public String getFireBaseClientId() {
        return fireBaseClientId;
    }

    public void setFireBaseClientId(String fireBaseId) {
        this.fireBaseClientId = fireBaseId;
    }
    public LayerType getLayerType() {
        return layerType;
    }

    public void setLayerType(LayerType layerType) {
        this.layerType = layerType;
    }

    public ScreenName getScreenName() {
        return screenName;
    }

    public void setScreenName(ScreenName screenName) {
        this.screenName = screenName;
    }
    public Date getCreateDate(){
        return new Date(createDateLong);
    }
    public String getFireBaseSrcClientId() {
        return fireBaseSrcClientId;
    }

    public void setFireBaseSrcClientId(String fireBaseSrcClientId) {
        this.fireBaseSrcClientId = fireBaseSrcClientId;
    }
    public String getAction(){
        return screenName.name() + "/" + layerType.name() + "/" +  origin.name();
    }

}
