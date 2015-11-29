package com.killer.ImageTest;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by killer on 2015/11/29.
 */
public class MySurfaceView extends GLSurfaceView{
    private SceneRenderer renderer;
    public MySurfaceView(Context context) {
        super(context);
        this.setEGLContextClientVersion(2);
        renderer = new SceneRenderer();
        this.setRenderer(renderer);
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染
    }
    @Override
    public boolean onTouchEvent(MotionEvent e)
    {

    }
    private class SceneRenderer implements GLSurfaceView.Renderer
    {

        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int i, int i1) {

        }

        @Override
        public void onDrawFrame(GL10 gl10) {

        }
    }
}
