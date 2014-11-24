package com.sunguowei.residemenu.test;

import android.app.Activity;
import android.app.ActivityGroup;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.CustomViewBehind;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sunguowei.residemenu.R;

/**
 * Created by fangzhu.du on 2014/10/21.
 */
public class TestActivity extends ActivityGroup {
    String TAG = "TestActivity";


//    based on
//    http://blog.csdn.net/manoel
//    https://github.com/sunguowei


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slidingmenumain);

        final SlidingMenu slidingMenu = (SlidingMenu)findViewById(R.id.slidingmenumain);

        slidingMenu.setMenu(R.layout.layout_menu);
        slidingMenu.setContent(R.layout.layout_content);
        slidingMenu.setSlidingEnabled(true);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//TOUCHMODE_FULLSCREEN

        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeEnabled(false);
        slidingMenu.setBehindScrollScale(0.25f);
        slidingMenu.setFadeDegree(0.25f);


//        slidingMenu.setShadowWidth(700);
//        slidingMenu.setShadowDrawable(R.drawable.focus_top_item_back);
//        slidingMenu.setSecondaryShadowDrawable(R.drawable.focus_top_item_back);
        slidingMenu.setBackgroundImage(R.drawable.img_frame_background);
        slidingMenu.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {

            }
        });
        slidingMenu.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
//                Log.v(TAG, "setBehindCanvasTransformer percentOpen="+percentOpen);
//                final int newAlpha = (int) (ratio * 255);
//                mActionBarBackgroundDrawable.setAlpha(newAlpha);

                float scale = (float) (percentOpen * 0.25 + 0.75);
                canvas.scale(scale, scale, -canvas.getWidth() / 2,
                        canvas.getHeight() / 2);

                percentOpen = 1.0F - percentOpen;
                if (percentOpen < 0.1)
                    percentOpen = 0.1F;
                if (percentOpen > 0.9)
                    percentOpen = 0.9F;
                String colorStr = "#" + Integer.toHexString((int) (percentOpen * 255F)) + "000000";
//                Log.v(TAG, "percentOpen="+percentOpen +"    colorStr="+colorStr);

                try {
//                    slidingMenu.getmViewAbove().bringToFront();//将内容带到最上面
//                    slidingMenu.getmAlphaView().setBackgroundColor(Color.parseColor(colorStr));//自定义一个背景View
//                    slidingMenu.getmViewBackground().setAlpha(percentOpen * 255F);
//                    slidingMenu.getContent().bringToFront();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        slidingMenu.setAboveCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                Log.v(TAG, "setAboveCanvasTransformer percentOpen="+percentOpen);
                float mSize = 0.25F;
                mSize = getResources().getDimension(R.dimen.slidingmenu_offset)/getResources().getDisplayMetrics().widthPixels;
                Log.v(TAG, "mSize ="+mSize);
                float scale = (float) (1 - percentOpen * mSize);
                Log.v(TAG, "scale ="+scale);
                Log.v(TAG, "canvas.getHeight() / 2 ="+canvas.getHeight() / 2);
                canvas.scale(scale, scale, 0, canvas.getHeight() / 2);
//                canvas.scale(scale, scale, 0, getResources().getDimension(R.dimen.slidingmenu_offset));

            }
        });
    }
}
