package com.killer.Light;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by killer on 2015/11/10.
 */
public class MySurfaceView extends GLSurfaceView {
    private float lightOffset = -4;
    private final float TOUCH_SCALE_FACTOR = 180.0f/320;//角度缩放比例
    private SceneRenderer mRenderer;
    private Ball ball;//求
    private float mPreviousY;//上次的触控位置Y坐标
    private float mPreviousX;//上次的触控位置X坐标
    public MySurfaceView(Context context) {
        super(context);
        // 设置openGLES版本2
        this.setEGLContextClientVersion(2);
        mRenderer = new SceneRenderer();//创建场景渲染器
        this.setRenderer(mRenderer);//设置renderer设置渲染器
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {
                float dy = y - mPreviousY;
                float dx = x - mPreviousX;
                ball.yAngle += dx * TOUCH_SCALE_FACTOR;//设置填充椭圆绕y轴旋转的角度
                ball.xAngle += dy * TOUCH_SCALE_FACTOR;//设置填充椭圆绕x轴旋转的角度
            }
            mPreviousY = y;//记录触控笔位置
            mPreviousX = x;//记录触控笔位置
        }
        return  true;
    }

    public void setLightOffset(float lightOffset) {
        this.lightOffset = lightOffset;
    }
    private class SceneRenderer implements GLSurfaceView.Renderer
    {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
            //设置屏幕背景颜色RGBA
            GLES20.glClearColor(0f, 0f, 0f, 1.0f);
            //创建球对象
            ball = new Ball(MySurfaceView.this);
            //打开深度检测
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            //打开背面裁剪
            GLES20.glEnable(GLES20.GL_CULL_FACE);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //设置视图和窗口大小
            GLES20.glViewport(0, 0, width, height);
            //计算GLSubFaceView宽高比
            Constant.ratio = (float)width / height;
            // 调用此方法计算产生透视投影矩阵
            MatrixState.setProjectFrustum(-Constant.ratio, Constant.ratio, -1, 1, 20, 100);
            // 调用此方法产生摄像机9参数位置矩阵
            MatrixState.setCamera(0, 0f, 30, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
            //初始化变换矩阵
            MatrixState.setInitStack();
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            //清除深度缓冲与颜色缓冲
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            //初始化光源方向
            MatrixState.setLightDirection(lightOffset, 0, 1.5f);
            //保护现场
            MatrixState.pushMatrix();
            //绘制球
            MatrixState.pushMatrix();
            //旋转
            MatrixState.translate(-1.2f, 0, 0);
            ball.drawSelf();
            MatrixState.popMatrix();

            //绘制球
            MatrixState.pushMatrix();
            MatrixState.translate(1.2f, 0, 0);
            ball.drawSelf();
            MatrixState.popMatrix();
            //恢复现场
            MatrixState.popMatrix();
        }
    }
}
