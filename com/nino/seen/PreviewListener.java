package com.nino.seen;
import android.view.TextureView;
import android.graphics.SurfaceTexture;
public class PreviewListener implements TextureView.SurfaceTextureListener {
    MainActivity act;
    public PreviewListener(MainActivity a){ act=a; }
    public void onSurfaceTextureAvailable(SurfaceTexture st, int w, int h){ act.startCamera(st); }
    public void onSurfaceTextureSizeChanged(SurfaceTexture st, int w, int h){}
    public boolean onSurfaceTextureDestroyed(SurfaceTexture st){ return true; }
    public void onSurfaceTextureUpdated(SurfaceTexture st){}
}
