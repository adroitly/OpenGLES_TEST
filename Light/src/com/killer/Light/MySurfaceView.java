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
    private final float TOUCH_SCALE_FACTOR = 180.0f/320;//�Ƕ����ű���
    private SceneRenderer mRenderer;
    private Ball ball;//��
    private float mPreviousY;//�ϴεĴ���λ��Y����
    private float mPreviousX;//�ϴεĴ���λ��X����
    public MySurfaceView(Context context) {
        super(context);
        // ����openGLES�汾2
        this.setEGLContextClientVersion(2);
        mRenderer = new SceneRenderer();//����������Ⱦ��
        this.setRenderer(mRenderer);//����renderer������Ⱦ��
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//������ȾģʽΪ������Ⱦ
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
                ball.yAngle += dx * TOUCH_SCALE_FACTOR;//���������Բ��y����ת�ĽǶ�
                ball.xAngle += dy * TOUCH_SCALE_FACTOR;//���������Բ��x����ת�ĽǶ�
            }
            mPreviousY = y;//��¼���ر�λ��
            mPreviousX = x;//��¼���ر�λ��
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
            //������Ļ������ɫRGBA
            GLES20.glClearColor(0f, 0f, 0f, 1.0f);
            //���������
            ball = new Ball(MySurfaceView.this);
            //����ȼ��
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            //�򿪱���ü�
            GLES20.glEnable(GLES20.GL_CULL_FACE);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //������ͼ�ʹ��ڴ�С
            GLES20.glViewport(0, 0, width, height);
            //����GLSubFaceView��߱�
            Constant.ratio = (float)width / height;
            // ���ô˷����������͸��ͶӰ����
            MatrixState.setProjectFrustum(-Constant.ratio, Constant.ratio, -1, 1, 20, 100);
            // ���ô˷������������9����λ�þ���
            MatrixState.setCamera(0, 0f, 30, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
            //��ʼ���任����
            MatrixState.setInitStack();
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            //�����Ȼ�������ɫ����
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            //��ʼ����Դ����
            MatrixState.setLightDirection(lightOffset, 0, 1.5f);
            //�����ֳ�
            MatrixState.pushMatrix();
            //������
            MatrixState.pushMatrix();
            //��ת
            MatrixState.translate(-1.2f, 0, 0);
            ball.drawSelf();
            MatrixState.popMatrix();

            //������
            MatrixState.pushMatrix();
            MatrixState.translate(1.2f, 0, 0);
            ball.drawSelf();
            MatrixState.popMatrix();
            //�ָ��ֳ�
            MatrixState.popMatrix();
        }
    }
}
