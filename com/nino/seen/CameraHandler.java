package com.nino.seen;
import android.hardware.camera2.*;
import android.view.Surface;
import java.util.Arrays;
public class CameraHandler extends CameraDevice.StateCallback {
    MainActivity act;
    Surface previewSurface;
    Surface readerSurface;
    public CameraHandler(MainActivity a, Surface ps, Surface rs){ act=a; previewSurface=ps; readerSurface=rs; }
    public void onOpened(CameraDevice cam){
        try{
            CaptureRequest.Builder b = cam.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            b.addTarget(previewSurface);
            b.addTarget(readerSurface);
            cam.createCaptureSession(Arrays.asList(previewSurface, readerSurface), new SessionHandler(b), null);
        }catch(Exception e){ act.onSignDetected("CAM ERR: "+e.getMessage(),0); }
    }
    public void onDisconnected(CameraDevice cam){ cam.close(); }
    public void onError(CameraDevice cam, int err){ cam.close(); }
}
