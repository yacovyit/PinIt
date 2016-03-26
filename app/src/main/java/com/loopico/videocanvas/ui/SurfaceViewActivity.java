package com.loopico.videocanvas.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.loopico.videocanvas.R;
import com.loopico.videocanvas.app.AppSingleton;
import com.loopico.videocanvas.app.Globals;
import com.loopico.videocanvas.enums.LayerType;
import com.loopico.videocanvas.enums.Origin;
import com.loopico.videocanvas.enums.ScreenName;
import com.loopico.videocanvas.pinitclasses.Cursor;
import com.loopico.videocanvas.pinitclasses.Manager;
import com.loopico.videocanvas.pinitclasses.VideoScreen;
import com.loopico.videocanvas.web.EndpointsAsyncTask;

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
        AppSingleton.getInstance().setTargetIcon(BitmapFactory.decodeResource(getResources(), R.drawable.target_lg));
        AppSingleton.getInstance().setWizardIcon(BitmapFactory.decodeResource(getResources(), R.drawable.pin));
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
        if (videoView!=null){
            videoView.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (videoView!=null){
            videoView.pause();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (videoView!=null){
            videoView.stopPlayback();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (videoView!=null){
            videoView.resume();
        }

    }
    //Data
    private View.OnClickListener screenButtonsListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.screen1:
                    Manager.Instance().setCurrentScreen(ScreenName.STAR_WARS);
                    SelectScreenButton(screen1);
                    UnSelectScreenButton(screen2);
                    break;
                case R.id.screen2:
                    SelectScreenButton(screen2);
                    UnSelectScreenButton(screen1);
                    Manager.Instance().setCurrentScreen(ScreenName.LARRY_BIRD);
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

    final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        public void onLongPress(MotionEvent e) {
            //Log.i("LONG-PRESS", "Longpress detected");
            x = (int)e.getX();
            y = (int)e.getY();

            //TODO add cursor to database
            Manager.Instance().addCursorID(SurfaceViewActivity.this);
            Cursor c = new Cursor(x, y,Manager.Instance().getCurrentLayerType(), Origin.USER, Manager.Instance().getCursorID(SurfaceViewActivity.this),Manager.Instance().getActiveScreen());

            Manager.Instance().add(c);
            new EndpointsAsyncTask().execute(new Pair<Context, Cursor>(SurfaceViewActivity.this,c));
          }
    });
}
