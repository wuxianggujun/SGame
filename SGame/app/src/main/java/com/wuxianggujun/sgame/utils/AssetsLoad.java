package com.wuxianggujun.sgame.utils;
import android.content.Context;
import java.util.HashMap;
import java.util.Set;
import android.util.Log;
import java.util.Map;
import java.io.InputStream;
import java.io.IOException;

/**
 * @作者: 无相孤君
 * @QQ: 3344207732
 * @描述:    
 */
public class AssetsLoad {
    
    private Context mContext;

    private HashMap<String,String> map;// = new HashMap<>();
    public AssetsLoad(Context mContext) {
        this.mContext = mContext;
        map = new HashMap<String,String>();
    
    }
    

    public void init(){
        
        Set<Map.Entry<String,String>> entrySet = map.entrySet();  //map.entrySet()返回<key,value>键值对的集合
        for (Map.Entry<String,String> entry:entrySet) {
            
            try {
                InputStream in = mContext.getResources().getAssets().open(entry.getValue());
                
            } catch (IOException e) {}

           
            
            //System.out.println(entry.getKey()+":"+entry.getValue());  //entry.getKey()返回key,entry.getValue()返回value
        }
        
        
        
    }

    public void load(String str,Object obj){
        
    }
    
    
    
    public void add(String name,String value){
        map.put(name,value);      
    }
    
    public int size(){
       return map.size();
    }
    
    public boolean isEmpty(){
      //判断是否为空，若不包含键-值映射关系则返回true
      if(map.isEmpty()){
          return true;
      }
      return false;
    }

    
    
}
