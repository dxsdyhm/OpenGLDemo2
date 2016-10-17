package Demo1;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by dxs on 2016/10/17.
 */

public class TDView extends GLSurfaceView {

    private SceneRender mRenderer;
    public TDView(Context context) {
        this(context,null);
    }

    public TDView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setEGLContextClientVersion(2);
        mRenderer=new SceneRender(this);
        this.setRenderer(mRenderer);
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
