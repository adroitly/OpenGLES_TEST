package com.killer.ImageTest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MyActivity extends Activity {
    private MySurfaceView mySurfaceView;
    static boolean threadFlag;//���������X����ת������־λ
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //����Ϊȫ��
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //����Ϊ����ģʽ
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //�л���������
        mySurfaceView = new MySurfaceView(this);
        setContentView(mySurfaceView);
        mySurfaceView.requestFocus();//��ȡ����
        mySurfaceView.setFocusableInTouchMode(true);//���ÿ��Դ���
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
