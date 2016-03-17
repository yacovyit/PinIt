package com.example.yacovyitzhak.myapplication.backend;

/** The object model for the data we are sending through endpoints */
public class CursorBean {

    private Integer x;
    private Integer y;
    private Integer xWizzard;
    private Integer yWizzard;

    public Integer getxWizzard() {
        return xWizzard;
    }

    public void setxWizzard(Integer xWizzard) {
        this.xWizzard = xWizzard;
    }

    public Integer getyWizzard() {
        return yWizzard;
    }

    public void setyWizzard(Integer yWizzard) {
        this.yWizzard = yWizzard;
    }


    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}