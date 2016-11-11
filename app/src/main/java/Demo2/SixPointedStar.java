package Demo2;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import Utils.ShaderUtil;

/**
 * Created by dxs on 2016/11/10.
 */
public class SixPointedStar {
    public static float[] mProjMatrix=new float[16];
    public static float[] mVMatrix=new float[16];
    public static float[] mMVPMatrix;
    private int mProgram;
    private int muMVPMatrixHandle;
    private int maPositionHandle;
    private int maColorHandler;
    private String mVertexShader;
    private String mFragmentShader;
    private static float[] mMMatrix=new float[16];
    private float yAngle=0;
    private float xAngle=0;
    private final float UNIT_SIZE=1;
    private int vCount;
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    public SixPointedStar (StarSurface mv,float r,float R,float z){
        initVertexData(R,r,z);
        initShader(mv);
    }

    private void initVertexData(float R, float r, float z) {
        List<Float> flist=new ArrayList<>();
        float tempAngle=360/6;
        for(float angle=0;angle<360;angle+=tempAngle){
            flist.add(0f);flist.add(0f);flist.add(z);
            flist.add((float) (R*UNIT_SIZE*Math.cos(Math.toRadians(angle))));
            flist.add((float) (R*UNIT_SIZE*Math.sin(Math.toRadians(angle))));
            flist.add(z);
            flist.add((float) (r*UNIT_SIZE*Math.cos(Math.toRadians(angle+tempAngle/2))));
            flist.add((float) (r*UNIT_SIZE*Math.sin(Math.toRadians(angle+tempAngle/2))));
            flist.add(z);
            flist.add(0f);flist.add(0f);flist.add(z);
            flist.add((float) (r*UNIT_SIZE*Math.cos(Math.toRadians(angle+tempAngle/2))));
            flist.add((float) (r*UNIT_SIZE*Math.sin(Math.toRadians(angle+tempAngle))));
            flist.add(z);
            flist.add((float) (R*UNIT_SIZE*Math.sin(Math.toRadians(angle+tempAngle))));
            flist.add(z);
        }
        vCount = flist.size() / 3;
        float[] vertexArray=new float[flist.size()];
        for(int i=0;i<vCount;i++){
            vertexArray[i*3]=flist.get(i*3);
            vertexArray[i*3+1]=flist.get(i*3+1);
            vertexArray[i*3+2]=flist.get(i*3+2);
        }
        ByteBuffer vbb=ByteBuffer.allocateDirect(vertexArray.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer=vbb.asFloatBuffer();
        mVertexBuffer.put(vertexArray);
        mVertexBuffer.position(0);
        float[] colorArray=new float[vCount*4];
        for (int i = 0; i < vCount; i++) {
            if(i%3==0){
                colorArray[i*4]=1;
                colorArray[i*4+1]=1;
                colorArray[i*4+2]=1;
                colorArray[i*4+3]=0;
            }else{
                colorArray[i*4]=0.45f;
                colorArray[i*4+1]=0.75f;
                colorArray[i*4+2]=0.75f;
                colorArray[i*4+3]=0;
            }
        }
        ByteBuffer cbb=ByteBuffer.allocateDirect(colorArray.length*4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer=cbb.asFloatBuffer();
        mColorBuffer.put(colorArray);
        mColorBuffer.position(0);
    }

    private void initShader(StarSurface glSurface) {
        mVertexShader= ShaderUtil.loadFromAssetFile("vertex.sh",glSurface.getResources());
        mFragmentShader=ShaderUtil.loadFromAssetFile("frag.sh",glSurface.getResources());
        mProgram=ShaderUtil.createProgram(mVertexShader,mFragmentShader);
        maPositionHandle= GLES20.glGetAttribLocation(mProgram,"aPosition");
        maColorHandler=GLES20.glGetAttribLocation(mProgram,"aColor");
        muMVPMatrixHandle=GLES20.glGetUniformLocation(mProgram,"uMVPMatrix");
    }

    public void drawSelf(){
        GLES20.glUseProgram(mProgram);
        Matrix.setRotateM(mMMatrix,0,0,0,1,0);
        Matrix.translateM(mMMatrix,0,0,0,1);
        Matrix.rotateM(mMMatrix,0,yAngle,0,1,0);
        Matrix.rotateM(mMMatrix,0,xAngle,1,0,0);
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle,1,false,MatrixState.get);
    }
}
