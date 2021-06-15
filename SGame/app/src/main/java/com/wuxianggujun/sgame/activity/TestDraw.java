package com.wuxianggujun.sgame.activity;
import com.wuxianggujun.sgame.control.GameListener;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import com.wuxianggujun.sgame.control.GameObject;
import android.content.Context;
import android.util.Log;
import android.graphics.Rect;
import android.graphics.Paint;
import android.graphics.Color;

/**
 * @作者: 无相孤君
 * @QQ: 3344207732
 * @描述:    
 */
public class TestDraw implements GameListener {
    GameObject gameObject;
    Context context;
    int width,height;
    

    public TestDraw(GameObject gameObject, Context context) {
        this.gameObject = gameObject;
        this.context = context;
        gameObject.setListener(this);
    }
    
    
    @Override
    public void onStart(SurfaceHolder holder) {
        Log.i("马腾","我刚刚回来了");
        Rect surfaceFrame = holder.getSurfaceFrame();
        width = surfaceFrame.width();//获取屏幕宽高
        height = surfaceFrame.height(); 
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.i("马腾","画画");
        Paint paint = new Paint();               
        paint.setColor(Color.GREEN);
        canvas.drawRect((width/2)-20,(height/4)+10, (height/4)+20,(height/4) -10, paint); //画一个绿色的方块
        
    }

    @Override
    public void onChanged() {
        Log.i("马腾","改变");
    }

    @Override
    public void onDestroyed() {
        Log.i("马腾","销毁");
    }
    
    
    
}
