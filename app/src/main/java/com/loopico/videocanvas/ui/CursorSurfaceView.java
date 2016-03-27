package com.loopico.videocanvas.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.loopico.videocanvas.app.AppSingleton;
import com.loopico.videocanvas.app.Utils;
import com.loopico.videocanvas.enums.Origin;
import com.loopico.videocanvas.pinitclasses.Cursor;
import com.loopico.videocanvas.pinitclasses.Layer;
import com.loopico.videocanvas.pinitclasses.Manager;

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
        List<Cursor> list = null,wizardList = null;

        Layer<Cursor> currentLayer = Manager.Instance().getCurrentLayer();
        list = currentLayer.getLayer(Origin.USER);
        wizardList = currentLayer.getLayer(Origin.WIZARD);
        synchronized (list) {
            for (Cursor cursor : list) {

                canvas.drawBitmap(Utils.changeImageColor(AppSingleton.getInstance().getTargetIcon(), Manager.Instance().getCurrentColor()), cursor.getX() - (AppSingleton.getInstance().getTargetIcon().getWidth() / 2), cursor.getY() - (AppSingleton.getInstance().getTargetIcon().getHeight() / 2), null);
                canvas.drawText(cursor.getId() + "", cursor.getX(), cursor.getY(), paint);
            }
        }
        synchronized (wizardList){
            for (Cursor cursor :wizardList) {
                canvas.drawBitmap(Utils.changeImageColor(AppSingleton.getInstance().getWizardIcon(), Manager.Instance().getCurrentColor()), cursor.getX() - (AppSingleton.getInstance().getTargetIcon().getWidth() / 2), cursor.getY() - (AppSingleton.getInstance().getTargetIcon().getHeight() / 2), null);
                canvas.drawText(cursor.getId()+"", cursor.getX(),cursor.getY(),paint);

            }
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
