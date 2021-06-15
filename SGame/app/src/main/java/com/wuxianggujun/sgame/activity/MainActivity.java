package com.wuxianggujun.sgame.activity;


import androidx.appcompat.app.AppCompatActivity;
import com.wuxianggujun.sgame.R;
import android.os.Bundle;
import com.wuxianggujun.sgame.view.Game;
import android.content.res.AssetManager;
import com.wuxianggujun.sgame.view.SurfaceViewVideo;
import com.wuxianggujun.sgame.control.GameObject;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import com.wuxianggujun.sgame.dialog.joinQQ;

public class MainActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//setContentView(R.layout.activity_main);
        /*
         Game game = new Game(MainActivity.this);
         setContentView(game);
         */
        /*
         SurfaceViewVideo vv = new SurfaceViewVideo(MainActivity.this);
         vv.setDataSource("/storage/emulated/0/BaiduNetdisk/我的资源/Android逆向/android1125/第二章：Android Hook 插件开发/课时1  Android 结构基础讲解[xd0.com].mp4");
         setContentView(vv);
         */
        GameObject game = new GameObject(MainActivity.this);
        TestDraw testDraw = new TestDraw(game, MainActivity.this);
        setContentView(game);
		super.onCreate(savedInstanceState);
        //调用静态方法
        joinQQ.TipDialog(MainActivity.this);
        //joinQQ.diaoyongDialog(MainActivity.this);

	}

    

}
