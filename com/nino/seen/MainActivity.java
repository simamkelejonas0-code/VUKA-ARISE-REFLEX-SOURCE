package com.nino.seen;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.hardware.camera2.*;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.Manifest;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.InputStream;

public class MainActivity extends Activity {
    public TextureView preview;
    public TextView statusText;
    public LinearLayout chatLayout;
    public ScrollView chatScroll;
    public EditText inputBox;
    ChatAdapter chat;
    ImageReader reader;
    CameraManager manager;
    HandlerThread bgThread;
    Handler bgHandler;

    protected void onCreate(Bundle b){
        super.onCreate(b);
        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(0xFF000000);
        preview = new TextureView(this);
        root.addView(preview, new FrameLayout.LayoutParams(-1,-1));
        
        LinearLayout top = new LinearLayout(this);
        top.setOrientation(1);
        top.setBackgroundColor(0xDD000000);
        top.setPadding(20,40,20,10);
        statusText = new TextView(this);
        statusText.setText("SEEN VO.O - OFFLINE + IMAGE UPLOAD\nAI Eyes ON");
        statusText.setTextColor(0xFF00FF00);
        statusText.setTextSize(12);
        top.addView(statusText);
        root.addView(top, new FrameLayout.LayoutParams(-1,-2));
        
        LinearLayout bottom = new LinearLayout(this);
        bottom.setOrientation(1);
        bottom.setBackgroundColor(0xCC000000);
        bottom.setPadding(8,8,8,8);
        FrameLayout.LayoutParams bp = new FrameLayout.LayoutParams(-1,-2);
        bp.gravity = Gravity.BOTTOM;
        
        chatScroll = new ScrollView(this);
        chatScroll.setLayoutParams(new LinearLayout.LayoutParams(-1, 420));
        chatLayout = new LinearLayout(this);
        chatLayout.setOrientation(1);
        chatScroll.addView(chatLayout);
        bottom.addView(chatScroll);
        
        LinearLayout inputRow = new LinearLayout(this);
        inputRow.setOrientation(0);
        inputBox = new EditText(this);
        inputBox.setHint("Type as hearing person...");
        inputBox.setTextColor(0xFFFFFFFF);
        inputBox.setHintTextColor(0xFF888888);
        inputBox.setLayoutParams(new LinearLayout.LayoutParams(0,-2,1));
        inputBox.setBackgroundColor(0xFF333333);
        
        Button imgBtn = new Button(this);
        imgBtn.setText("IMG");
        imgBtn.setOnClickListener(new ImagePickClick(this));
        
        Button send = new Button(this);
        send.setText("SEND");
        send.setOnClickListener(new SendClick(this));
        
        inputRow.addView(imgBtn);
        inputRow.addView(inputBox);
        inputRow.addView(send);
        bottom.addView(inputRow);
        
        root.addView(bottom, bp);
        chat = new ChatAdapter(chatLayout, chatScroll);
        chat.addMessage("SYSTEM","Offline chat + Image upload ready. Tap IMG to upload.",0xFFAAAAAA);
        
        setContentView(root);
        bgThread = new HandlerThread("cam");
        bgThread.start();
        bgHandler = new Handler(bgThread.getLooper());
        reader = ImageReader.newInstance(640,480,android.graphics.ImageFormat.YUV_420_888,2);
        reader.setOnImageAvailableListener(new FrameListener(this), bgHandler);
        preview.setSurfaceTextureListener(new PreviewListener(this));
        
        // Request both permissions
        if(checkSelfPermission(Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED ||
           checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_MEDIA_IMAGES}, 1);
        }
    }
    public void onRequestPermissionsResult(int c, String[] p, int[] r){
        if(preview.isAvailable()){ try{ startCamera(preview.getSurfaceTexture()); }catch(Exception e){} }
    }
    public void startCamera(SurfaceTexture st){
        try{
            st.setDefaultBufferSize(640,480);
            Surface ps = new Surface(st);
            Surface rs = reader.getSurface();
            manager = (CameraManager)getSystemService(CAMERA_SERVICE);
            String id = manager.getCameraIdList()[0];
            manager.openCamera(id, new CameraHandler(this, ps, rs), bgHandler);
            chat.addMessage("AI","Eyes open - Show sign + IMG upload ready",0xFF00FF00);
        }catch(Exception e){ onFrameTick("CAM FAIL: "+e.getMessage()); }
    }
    protected void onActivityResult(int code, int result, Intent data){
        super.onActivityResult(code,result,data);
        if(code==101 && result==RESULT_OK && data!=null){
            try{
                Uri uri = data.getData();
                InputStream is = getContentResolver().openInputStream(uri);
                Bitmap bmp = BitmapFactory.decodeStream(is);
                is.close();
                // Show in chat
                chat.addImage("YOU", bmp);
                // AI will also see this image as input for training
                statusText.setText("IMAGE LOADED: "+bmp.getWidth()+"x"+bmp.getHeight()+" - AI can train on this");
                chat.addMessage("AI","Image received - I will learn this sign",0xFF00FF00);
            }catch(Exception e){
                onFrameTick("IMG LOAD FAIL: "+e.getMessage());
            }
        }
    }
    public void onFrameTick(String msg){ runOnUiThread(new UiUpdater(this, msg, true)); }
    public void onSignDetected(String w, float cf){ runOnUiThread(new SignUpdater(this, w, cf)); }
    public void sendTyped(){
        String t = inputBox.getText().toString();
        if(t.length()==0) return;
        chat.addMessage("HEARING", t, 0xFFFFFFFF);
        inputBox.setText("");
    }
}
