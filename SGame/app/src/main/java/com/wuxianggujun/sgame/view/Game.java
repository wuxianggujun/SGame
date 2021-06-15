package com.wuxianggujun.sgame.view;

import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.graphics.Path;
import java.util.Random;
import android.view.View;
import android.view.MotionEvent;
import android.util.Log;

/**
 * @作者: 无相孤君
 * @QQ: 3344207732
 * @描述:    
 */
public class Game extends SurfaceView implements Runnable,SurfaceHolder.Callback,View.OnTouchListener {
    Context main;
    SurfaceHolder holder;//主holder
    Canvas canvas;//主画布
    Paint p;
    Thread t;
    Bitmap buffer=null;//二级缓存
    Path mpath;//触控中的轨迹
    float startX;
    float startY;
    Rect window;
    Rect bufferRect;
    boolean run=false;

    public Game(Context context) {
        super(context);
        init(context);
        setOnTouchListener(this);
    }

    private void init(Context context){
        holder=getHolder();
        holder.addCallback(this);//勿忘addcallback
        this.main=context;
        p=new Paint();
        p.setAntiAlias(true);//消除锯齿
        p.setStyle(Paint.Style.STROKE);//设置画笔风格
        p.setAlpha(255);//画笔的不透明度
        p.setStrokeWidth((float)2);//设置笔触宽度
        p.setColor(Color.WHITE);
        mpath=new Path();
        buffer= Bitmap.createBitmap(1440,2256, Bitmap.Config.ARGB_8888);//很重要创建二级缓存的必要方法

        t=new Thread(this);

    }
    
    @Override
    public void run() {
        while(run){
            canvas=holder.lockCanvas();
            window=canvas.getClipBounds();
            Canvas c=new Canvas(buffer);
            c.drawRect(window,p);
            p.setColor(Color.rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
            c.drawPath(mpath,p);
            bufferRect=new Rect(0,0,buffer.getWidth(),buffer.getHeight());
            canvas.drawBitmap(buffer,bufferRect,window,new Paint());
            holder.unlockCanvasAndPost(canvas);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        run=true;

        Log.i("Created", "surfaceCreated: ");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("Changed", "surfaceChanged: ");
        t=new Thread(this);
        t.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        run=false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Destroyed", "surfaceDestroyed: ");
    }
    private void onTouchDown(MotionEvent event){
        startX=event.getX();
        startY=event.getY();
        mpath.reset();
        mpath.moveTo(startX,startY);

    }
    private void onTouchMove(MotionEvent event){
        float touchX=event.getX();
        float touchY=event.getY();

        float dx=Math.abs(touchX-startX);//移动的距离

        float dy =Math.abs(touchY-startX);//移动的距离


        if(dx>3||dy>3){
            float cX=(touchX+startX)/2;
            float cY=(touchY+startY)/2;
            mpath.quadTo(startX, startY, cX, cY);//绘制贝塞尔曲线

            startX=touchX;
            startY=touchY;//改变开始绘制的点
        }//这里巧妙地进行筛选过于短的移动
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.onTouchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                this.onTouchMove(event);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

}
