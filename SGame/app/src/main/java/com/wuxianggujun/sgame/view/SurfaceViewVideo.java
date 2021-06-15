package com.wuxianggujun.sgame.view;

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
public class SurfaceViewVideo extends SurfaceView implements Runnable,SurfaceHolder.Callback {

    private MediaPlayer mediaPlay;
    private String filePath;
    private Canvas canvas;
    private SurfaceHolder holder;
    
    //子线程标记
    private volatile boolean isDrawing;
    
    public SurfaceViewVideo(Context context){
        super(context);    
        init();   
    }

    
    private void init(){
        mediaPlay = new MediaPlayer(); 
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //显示的分辨率,不设置为视频默认
        // mHolder.setFixedSize(320, 220);
        setFocusable(true);     
        
        
    }

    @Override
    public void run() {
        
        while(isDrawing){
            draw();
        }
        
        
    }
    
 

  

    private void draw()
    {
        try{
        canvas = holder.lockCanvas();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(canvas!=null){
                holder.unlockCanvasAndPost(canvas);
            }
        }
        
        
    }
    
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
     
    @Override
    public void surfaceCreated(SurfaceHolder p1) {
        isDrawing = true;
        try{
        mediaPlay.setDisplay(holder);
       mediaPlay.setDataSource(getDataSource());
          //异步准备
        //mediaPlay.prepareAsync();
        mediaPlay.prepare();
        mediaPlay.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }        
        
    }

    @Override
    public void surfaceChanged(SurfaceHolder p1, int p2, int p3, int p4) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder p1) {
          isDrawing = false;
          holder.removeCallback(this);
        if(mediaPlay != null && mediaPlay.isPlaying()){
            mediaPlay.stop();
            mediaPlay.release();
            mediaPlay = null;
        }
        
        
        
    }
    

    public void setDataSource(String filePath){
        this.filePath = filePath;
    }

    public String getDataSource(){
        if(filePath!=null){      
            return filePath;
        }
        return "空";
    }
    

}
