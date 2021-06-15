package com.wuxianggujun.sgame.activity;
import android.os.Bundle;
import com.wuxianggujun.sgame.dialog.joinQQ;

/**
 * @作者: 无相孤君
 * @QQ: 3344207732
 * @描述:    
 */
public class BaseActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        joinQQ.TipDialog(BaseActivity.this);
    }
   
    
    
    
    
}
