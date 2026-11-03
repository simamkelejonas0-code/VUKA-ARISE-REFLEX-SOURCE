package com.nino.seen;
import android.media.ImageReader;
import android.media.Image;
public class FrameListener implements ImageReader.OnImageAvailableListener {
    MainActivity act;
    long last=0;
    int frameCount=0;
    // SASL basic signs - you will train this array
    String[] SIGNS = {"HELLO","THANK YOU","YES","NO","HELP","WATER","FOOD","PLEASE","SORRY","I AM DEAF"};
    public FrameListener(MainActivity a){ act=a; }
    public void onImageAvailable(ImageReader r){
        long now = System.currentTimeMillis();
        if(now-last < 500) { try{ Image im=r.acquireLatestImage(); if(im!=null) im.close(); }catch(Exception e){} return; }
        last=now;
        Image img=null;
        try{
            img=r.acquireLatestImage();
            if(img==null) return;
            frameCount++;
            // OFFLINE AI EYES: This is where your manual model will run
            // For now simulate detection - every 5 sec produce a sign
            // Replace with: Bitmap -> tflite interpreter -> label
            if(frameCount % 6 == 0){
                String detected = SIGNS[frameCount % SIGNS.length];
                float conf = 0.85f + (frameCount%15)/100f;
                act.onSignDetected(detected, conf);
            } else {
                act.onFrameTick("EYES: "+img.getWidth()+"x"+img.getHeight()+" | Frame "+frameCount);
            }
        }catch(Exception e){
        }finally{ if(img!=null) img.close(); }
    }
}
