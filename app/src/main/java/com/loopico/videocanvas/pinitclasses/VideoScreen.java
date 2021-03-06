package com.loopico.videocanvas.pinitclasses;

import com.loopico.videocanvas.pinitclasses.Cursor;
import com.loopico.videocanvas.pinitclasses.Screen;

/**
 * Created by yacovyitzhak on 17/03/2016.
 */
public class VideoScreen<T extends Cursor> extends Screen<T> {


    private String url;
    public VideoScreen(String url){
        super();
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
