package com.wuxianggujun.sgame.control;

import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.Context;
import android.media.MediaPlayer;
import java.io.IOException;
import android.graphics.Canvas;

/**
 * @作者: 无相孤君
 * @QQ: 3344207732
 * @描述:    
 */
public class GameObject extends SurfaceView implements Runnable,SurfaceHolder.Callback {

    private Canvas canvas;
    private SurfaceHolder holder;

    //子线程标记
    private volatile boolean isDrawing;

    private GameListener listener;


    public GameObject(Context context) {
        super(context);    
        init();   
    }

    public void setListener(GameListener listener) {
        this.listener = listener;
    }

    public GameListener getListener() {
        return listener;
    }


    private void init() {
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //显示的分辨率,不设置为视频默认
        // mHolder.setFixedSize(320, 220);
        setFocusable(true);     
    }

    @Override
    public void run() {

        while (isDrawing) {

            try {
                canvas = holder.lockCanvas();
                if (listener != null) {
                    listener.onDraw(canvas); 
                } 
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }          
            }
        }

    }
    
    @Override
    public void surfaceCreated(SurfaceHolder p1) {
        isDrawing = true;    
        if(listener!= null){
            listener.onStart(holder);
        }
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder p1, int p2, int p3, int p4) {
        if(listener != null){
            listener.onChanged();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder p1) {
        isDrawing = false;
        if(listener!=null){
            listener.onDestroyed();
        }
        try {
            new Thread(this).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.removeCallback(this);
        
    }





}
