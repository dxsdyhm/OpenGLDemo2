package Demo1;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dxs on 2016/10/17.
 */

public class SceneRender<T extends GLSurfaceView> implements GLSurfaceView.Renderer{
    private static final float ANGLE_SPAN=0.5f;
    private T sv;
    private Triangle tle;
    private RotateThread rThread;

    public SceneRender(T sv) {
        this.sv = sv;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0,0,0,1.0f);
        tle=new Triangle(sv);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        rThread=new RotateThread();
        rThread.start();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
       GLES20.glViewport(0,0,width,height);
        float ratio=(float) width/height;
        Matrix.frustumM(Triangle.mProjMatrix,0,-ratio,ratio,-1,1,1,10);
        Matrix.setLookAtM(Triangle.mVMatrix,0,0,0,3,0f,0f,0f,0f,1.0f,0.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT|GLES20.GL_COLOR_BUFFER_BIT);
        tle.drawSelf();
    }

    public Triangle getTle() {
        return tle;
    }

    public class RotateThread extends Thread{
        public boolean flag=true;

        @Override
        public void run() {
            while (flag){
                tle.xAngle=tle.xAngle+ANGLE_SPAN;
                try {
                    Thread.sleep(20);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
