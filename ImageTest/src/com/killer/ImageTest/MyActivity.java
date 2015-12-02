package com.killer.ImageTest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MyActivity extends Activity {
    private MySurfaceView mySurfaceView;
    static boolean threadFlag;//纹理矩形绕X轴旋转工作标志位
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("create");
//        setContentView(R.layout.main);
        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置为竖屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //切换到主界面
        mySurfaceView = new MySurfaceView(this);
        setContentView(mySurfaceView);
        mySurfaceView.requestFocus();//获取焦点
        mySurfaceView.setFocusableInTouchMode(true);//设置可以触控
    }
    @Override
    protected void onResume() {
        super.onResume();
        threadFlag=true;
        mySurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        threadFlag=false;
        mySurfaceView.onPause();
    }
}
