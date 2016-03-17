package com.loopico.videocanvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        SelectScreenButton(screen1);
        UnSelectScreenButton(screen2);
        Manager.Instance().initColor(redLayerButton.getCurrentTextColor());
        Manager.Instance().setCurrentColor(redLayerButton.getCurrentTextColor());

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
                    Manager.Instance().setCurrentScreen(Manager.ScreenNames.STAR_WARS);
                    SelectScreenButton(screen1);
                    UnSelectScreenButton(screen2);
                    break;
                case R.id.screen2:
                    SelectScreenButton(screen2);
                    UnSelectScreenButton(screen1);
                    Manager.Instance().setCurrentScreen(Manager.ScreenNames.LARRY_BIRD);
                    break;
            }
            updateBackground();
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
    private void SelectScreenButton(Button btn){
        btn.setBackgroundColor(Color.BLACK);
        btn.setTextColor(Color.WHITE);
    }
    private void UnSelectScreenButton(Button btn){
        btn.setBackgroundColor(Color.WHITE);
        btn.setTextColor(Color.BLACK);
    }
    private void clearCursor(){
        Manager.Instance().clear();
    }
    private void updateBackground(){
        buttonsContainerRelativeLayout.setBackgroundColor(Manager.Instance().getCurrentColor());
        buttonsContainerRelativeLayout.refreshDrawableState();
    }

    //create GestureDetector for long press on canvas
    final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        public void onLongPress(MotionEvent e) {
            //Log.i("LONG-PRESS", "Longpress detected");
            x = (int)e.getX();
            y = (int)e.getY();

            //TODO add cursor to database
            Manager.Instance().addCursorID();
            Cursor c = new Cursor(x, y,Manager.Instance().getCurrentLayerType(),Origin.USER, Manager.Instance().getCursorID());

            Manager.Instance().add(c);
            List<Cursor> srcList = Manager.Instance().getCurrentLayer().getLayer(Origin.USER);
            List<Cursor>  dstList =  Manager.Instance().getCurrentLayer().getLayer(Origin.WIZZARD);;
            new EndpointsAsyncTask(srcList,dstList).execute(new Pair<Context, Cursor>(SurfaceViewActivity.this,c));

          }
    });

}