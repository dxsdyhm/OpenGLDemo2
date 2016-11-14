package Demo2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import Utils.MatrixState;

/**
 * Created by dxs on 2016/11/10.
 */

public class StarSurface extends GLSurfaceView{
    private final float TOUCH_SCALE_FACTOR=180.f/320;
    private SceneRender mRenderer;
    private float mPreviousY;
    private float mPreviousX;
    public StarSurface(Context context) {
        super(context);
        this.setEGLContextClientVersion(2);
        mRenderer=new SceneRender();
        this.setRenderer(mRenderer);
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    public StarSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y= event.getY();
        float x= event.getX();
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                float dy=y-mPreviousY;
                float dx=x-mPreviousX;
                for(SixPointedStar h:mRenderer.ha){
                    h.yAngle+=dx*TOUCH_SCALE_FACTOR;
                    h.xAngle+=dy*TOUCH_SCALE_FACTOR;
                }
        }
        mPreviousX=x;
        mPreviousY=y;
        return true;
    }

    private class SceneRender implements GLSurfaceView.Renderer{
        SixPointedStar[] ha=new SixPointedStar[6];
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.5f,0.5f,0.5f,1.0f);
            for(int i=0;i<ha.length;i++){
                ha[i]=new SixPointedStar(StarSurface.this,0.2f,0.5f,-1.0f*i);
            }
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0,0,width,height);
            float ratio=(float) width/height;
//            MatrixState.setProjectOrtho(-ratio,ratio,-1,1,1,10);
            MatrixState.setProjectFrustum(-ratio,ratio,-1,1,1,10);
            MatrixState.setCamera(0,0,3f,0,0,0f,0f,1.0f,0.0f);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT|GLES20.GL_COLOR_BUFFER_BIT);
            for(SixPointedStar h:ha){
                h.drawSelf();
            }
        }
    }
}
