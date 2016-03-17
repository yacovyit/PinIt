package com.loopico.videocanvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.loopico.videocanvas.web.EndpointsAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class SurfaceViewActivity extends Activity {

    //view's

    private CursorSurfaceView cursorSurfaceView;
    private RelativeLayout buttonsContainerRelativeLayout;

    private Button redLayerButton,greenLayerButton,blueLayerButton,clearLayerButton;
    private Button screen1,screen2;

    private VideoView videoView;
    private int x, y;

    /*
    videoView.stopPlayback();
    videoView.setVideoPath(newVideoPath);
    videoView.start();
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //set default layer to RED
        //Globals.setCurrentLayerColor(LayerType.RED);

        //init view's
        FrameLayout frameLayoutContainer = (FrameLayout) findViewById(R.id.frameLayoutContainer);
        buttonsContainerRelativeLayout = (RelativeLayout) findViewById(R.id.buttonsContainerRelativeLayout);


        redLayerButton = (Button) findViewById(R.id.redLayerButton);
        greenLayerButton = (Button) findViewById(R.id.greenLayerButton);
        blueLayerButton = (Button) findViewById(R.id.blueLayerButton);
        clearLayerButton = (Button) findViewById(R.id.clearLayerButton);

        screen1 = (Button) findViewById(R.id.screen1);
        screen2 = (Button) findViewById(R.id.screen2);

        //set setOnClickListener event to all layer buttons
        redLayerButton.setOnClickListener(layerButtonsListener);
        greenLayerButton.setOnClickListener(layerButtonsListener);
        blueLayerButton.setOnClickListener(layerButtonsListener);
        clearLayerButton.setOnClickListener(layerButtonsListener);

        screen1.setOnClickListener(screenButtonsListener);
        screen2.setOnClickListener(screenButtonsListener);

        //set background
        Manager.Instance().initColor(redLayerButton.getCurrentTextColor());
        Manager.Instance().setCurrentColor(redLayerButton.getCurrentTextColor());

        buttonsContainerRelativeLayout.setBackgroundColor(Manager.Instance().getCurrentColor());
        buttonsContainerRelativeLayout.refreshDrawableState();

        updateBackground();

        cursorSurfaceView = new CursorSurfaceView(this);
        cursorSurfaceView.setZOrderOnTop(true);

        cursorSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        Globals.targetIcon = BitmapFactory.decodeResource(getResources(), R.drawable.target_lg);
        Globals.wizzardIcon = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
        //upper left corner
        x = 0;
        y = 0;


        frameLayoutContainer.addView(cursorSurfaceView);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath(Globals.starWarsUrl);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                    }
                });

        videoView.start();



    }

    //-------------------------------------------------
    // Activity life cycle
    //-------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    //Data
    private View.OnClickListener screenButtonsListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //String url = null;
            switch (v.getId()) {
                case R.id.screen1:
                    //url = Globals.starWarsUrl;
                    Manager.Instance().setCurrentScreen(Manager.ScreenNames.STAR_WARS);
                    break;
                case R.id.screen2:
                    //url = Globals.larryBirdUrl;
                    Manager.Instance().setCurrentScreen(Manager.ScreenNames.LARRY_BIRD);
                    break;
            }

            videoView.stopPlayback();
            videoView.setVideoPath(((VideoScreen)Manager.Instance().getCurrentScreen()).getUrl());
            videoView.start();
        }

    };
    private View.OnClickListener layerButtonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.redLayerButton:
                    //red cursor
                    Manager.Instance().setCurrentLayerType(LayerType.RED);
                    Manager.Instance().setCurrentColor(((Button) v).getCurrentTextColor());
                    updateBackground();

                    break;
                case R.id.greenLayerButton:
                    //green cursor
                    Manager.Instance().setCurrentLayerType(LayerType.GREEN);
                    Manager.Instance().setCurrentColor(((Button) v).getCurrentTextColor());
                    updateBackground();
                    break;
                case R.id.blueLayerButton:
                    //blue cursor
                    Manager.Instance().setCurrentLayerType(LayerType.BLUE);
                    Manager.Instance().setCurrentColor(((Button) v).getCurrentTextColor());
                    updateBackground();
                    break;
                case R.id.clearLayerButton:
                    clearCursor();
                    break;
                default:
                    break;
            }

        }
    };

    private void clearCursor(){
//        Globals.redLayerCursorList.clear();
//        Globals.greenLayerCursorList.clear();
//        Globals.blueLayerCursorList.clear();
//        Globals.redWizzardLayerCursorList.clear();
//        Globals.greenWizzardLayerCursorList.clear();
//        Globals.blueWizzardLayerCursorList.clear();
        Manager.Instance().clear();
    }
    private void updateBackground(){
        buttonsContainerRelativeLayout.setBackgroundColor(Manager.Instance().getCurrentColor());
    }

    //create GestureDetector for long press on canvas
    final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        public void onLongPress(MotionEvent e) {
            Log.i("LONG-PRESS", "Longpress detected");
            x = (int)e.getX();
            y = (int)e.getY();

            //TODO add cursor to database
            Cursor c = new Cursor(x, y,Manager.Instance().getCurrentLayerType(),Origin.USER, Manager.Instance().getCursorID());
            Manager.Instance().addCursorID();
            Manager.Instance().add(c);
            List<Cursor> srcList = Manager.Instance().getCurrentLayer().getLayer(Origin.USER);
            List<Cursor>  dstList =  Manager.Instance().getCurrentLayer().getLayer(Origin.WIZZARD);;
            new EndpointsAsyncTask(srcList,dstList).execute(new Pair<Context, Cursor>(SurfaceViewActivity.this,c));

//            if (Globals.getCurrentLayerColor().equals(LayerType.RED)) {
//                Globals.redLayerCursorList.add(Globals.getLastAddedCursor());
//                srcList = Globals.redLayerCursorList;
//                dstList =  Globals.redWizzardLayerCursorList;
//            } else if (Globals.getCurrentLayerColor().equals(LayerType.GREEN)) {
//                Globals.greenLayerCursorList.add(Globals.getLastAddedCursor());
//                srcList = Globals.greenLayerCursorList;
//                dstList =  Globals.greenWizzardLayerCursorList;
//            } else if (Globals.getCurrentLayerColor().equals(LayerType.BLUE)) {
//                Globals.blueLayerCursorList.add(Globals.getLastAddedCursor());
//                srcList = Globals.blueLayerCursorList;
//                dstList =  Globals.blueWizzardLayerCursorList;
//            }
          }
    });

}