package Demo2;

import android.opengl.Matrix;

/**
 * Created by dxs on 2016/11/10.
 */
public class MatrixState {
    private static  float[] mProjMatrix=new float[16];
    private static  float[] mVMatrix=new float[16];
    private static  float[] mMVPMatrix;
    public static void setCamera(float cx,float cy,float cz,float tx,float ty,float tz,float upx,float upy,float upz) {
        Matrix.setLookAtM(mVMatrix, 0, cx, cy, cz, tx, ty, tz, upx, upy, upz);
    }
    public static void setProjectOrtho(float left,float right,float bottom,float top,float near,float far){
        Matrix.orthoM(mProjMatrix,0,left,right,bottom,top,near,far);
    }
    public static float[] getFinalMatrix(){

    }
}
