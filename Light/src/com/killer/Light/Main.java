package com.killer.Light;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * Created by killer on 2015/11/10.
 */
public class Main extends Activity {
    private MySurfaceView mGLSurfaceView;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // ����Ϊȫ��
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//����Ϊ����
        // ��ʼ��GLSurfaceView
        mGLSurfaceView = new MySurfaceView(this);
        this.setContentView(R.layout.main);
        LinearLayout ll = (LinearLayout) findViewById(R.id.main_liner);
        ll.addView(mGLSurfaceView);
        //��ͨ�������������Ĵ������
        SeekBar sb=(SeekBar)this.findViewById(R.id.SeekBar01);
        sb.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        mGLSurfaceView.setLightOffset((seekBar.getMax() / 2.0f - progress) / (seekBar.getMax() / 2.0f) * -4);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
    }
    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}
