package com.nino.seen;
import android.hardware.camera2.*;
public class SessionHandler extends CameraCaptureSession.StateCallback {
    CaptureRequest.Builder builder;
    public SessionHandler(CaptureRequest.Builder b){ builder=b; }
    public void onConfigured(CameraCaptureSession s){
        try{ s.setRepeatingRequest(builder.build(), null, null); }catch(Exception e){}
    }
    public void onConfigureFailed(CameraCaptureSession s){}
}
