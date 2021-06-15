package com.wuxianggujun.sgame.dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.Activity;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;
import android.text.Html;

/**
 * @作者: 无相孤君
 * @QQ: 3344207732
 * @描述:    
 */
public class joinQQ {


    public static void TipDialog(final Context context) {
        CharSequence titleStr;
        CharSequence messageStr;
        CharSequence neutralText;
        CharSequence negativeText;
        CharSequence positiveText;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            titleStr = Html.fromHtml("<font color='#039BE5'>提示</font>", Html.FROM_HTML_MODE_LEGACY);
            messageStr = Html.fromHtml("<font color='#039BE5'>感谢使用本团队旗下软件，感谢你对我们的支持，置顶本群第一时间获取软件</font>", Html.FROM_HTML_MODE_LEGACY);
            neutralText = Html.fromHtml("<font color='#039BE5'>取消</font>", Html.FROM_HTML_MODE_LEGACY);
            negativeText  = Html.fromHtml("<font color='#039BE5'>加入群聊</font>", Html.FROM_HTML_MODE_LEGACY);
            positiveText = Html.fromHtml("<font color='#039BE5'>联系作者</font>", Html.FROM_HTML_MODE_LEGACY);
        } else {
            titleStr = Html.fromHtml("<font color='#039BE5'>提示</font>");
            messageStr = Html.fromHtml("<font color='#039BE5'>感谢使用本团队旗下软件，感谢你对我们的支持，置顶本群第一时间获取软件</font>");
            neutralText = Html.fromHtml("<font color='#039BE5'>取消</font>");
            negativeText = Html.fromHtml("<font color='#039BE5'>加入群聊</font>");
            positiveText  = Html.fromHtml("<font color='#039BE5'>联系作者</font>");
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(titleStr);
        dialog.setMessage(messageStr);
        dialog.setCancelable(false);
        dialog.setNeutralButton(neutralText, new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface p1, int p2) {
                }
            });
        dialog.setNegativeButton(negativeText, new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dia, int which) {

                    String key = "OhAciVt3oHxqhkH5YWH0to9LyeH9PZHn";

                    if (joinQQGroup(context, key)) {
                        Toast.makeText(context, "QQ调用成功", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        dialog.setPositiveButton(positiveText, new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dia, int which) {

                    try {
                        String url = "mqqwpa://im/chat?chat_type=wpa&uin=1324726978";
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }      
            });  
        /* dialog.create();
         dialog.show();*/            
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }

    /****************
     *
     * 发起添加群流程。群号：高chao党(SsD)(891060800) 的 key 为： KFRq4vOo34cpaymxKAoBBlVHmvIBzgxF
     * 调用 joinQQGroup(KFRq4vOo34cpaymxKAoBBlVHmvIBzgxF) 即可发起手Q客户端申请加群 高chao党(SsD)(891060800)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回false表示呼起失败
     ******************/
    public static boolean joinQQGroup(Context context, String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26jump_from%3Dwebapi%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }



}
