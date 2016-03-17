package com.loopico.videocanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class CursorSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    private SurfaceHolder holder;
    private WorkerThread workerThread;
    private Paint paint;

    public CursorSurfaceView(Context context)
    {
        super(context);
        holder = getHolder();

        holder.addCallback(this);
        //holder.setFormat(PixelFormat.TRANSPARENT);
        holder.setFormat(PixelFormat.TRANSLUCENT);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        final float testTextSize = 40f;
        paint.setTextSize(testTextSize);
        //paint.setTextAlign(Paint.Align.CENTER);

        //initialize paint object parameters

        setWillNotDraw(false); //this line is very important!
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
    }

    @Override
    // This is always called at least once, after surfaceCreated
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        if (workerThread == null)
        {
            workerThread = new WorkerThread(holder);
            workerThread.setRunning(true);
            workerThread.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        workerThread.setRunning(false);
        while (retry)
        {
            try
            {
                workerThread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {
            }
        }
        workerThread = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        System.out.println(event.getX() + " " + event.getY());
        return super.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //canvas.drawColor(Color.TRANSPARENT);
        List<Cursor> list = null,wizzardList = null;
//        if(Globals.getCurrentLayerColor().equals(LayerType.RED)){
//            list = Globals.redLayerCursorList;
//            wizzardList =  Globals.redWizzardLayerCursorList;
//        }else if(Globals.getCurrentLayerColor().equals(LayerType.GREEN)){
//            list = Globals.greenLayerCursorList;
//            wizzardList =  Globals.greenWizzardLayerCursorList;
//        }else if(Globals.getCurrentLayerColor().equals(LayerType.BLUE)){
//            list = Globals.blueLayerCursorList;
//            wizzardList =  Globals.blueWizzardLayerCursorList;
//        }
        Layer<Cursor> currentLayer = Manager.Instance().getCurrentLayer();
        list = currentLayer.getLayer(Origin.USER);
        wizzardList = currentLayer.getLayer(Origin.WIZZARD);
        for (Cursor cursor :list) {

            canvas.drawBitmap(Utils.changeImageColor(Globals.targetIcon, Manager.Instance().getCurrentColor()), cursor.getX() - (Globals.targetIcon.getWidth() / 2), cursor.getY() - (Globals.targetIcon.getHeight() / 2), null);
            canvas.drawText(cursor.getId()+"", cursor.getX(),cursor.getY(),paint);
        }
        for (Cursor cursor :wizzardList) {
            canvas.drawBitmap(Utils.changeImageColor(Globals.wizzardIcon, Manager.Instance().getCurrentColor()), cursor.getX() - (Globals.targetIcon.getWidth() / 2), cursor.getY() - (Globals.targetIcon.getHeight() / 2), null);
            canvas.drawText(cursor.getId()+"", cursor.getX(),cursor.getY(),paint);

        }

    }

    public class WorkerThread extends Thread
    {
        private SurfaceHolder holder;
        private boolean running = false;

        public WorkerThread(SurfaceHolder holder)
        {
            this.holder = holder;
        }

        @Override
        public void run()
        {
            Canvas canvas = null;
            while (running)
            {
                try
                {
                    canvas = holder.lockCanvas(null);
                    synchronized (holder)
                    {
                        postInvalidate();
                    }
                }
                finally
                {
                    if (canvas != null)
                    {
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            }

        }
        public void setRunning(boolean b)
        {
            running = b;
        }
    }
}
