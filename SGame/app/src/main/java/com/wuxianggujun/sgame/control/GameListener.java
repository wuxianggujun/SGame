package com.wuxianggujun.sgame.control;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/*
@作者: 无相孤君
@QQ: 3344207732
@描述: 
*/
public interface GameListener{
    
    void onStart(SurfaceHolder holder);
    
    void onDraw(Canvas canvas);  
    
    void onChanged();
    
    void onDestroyed();
    
}
