package com.nino.seen;
import android.view.View;
import android.content.Intent;
import android.provider.MediaStore;
public class ImagePickClick implements View.OnClickListener {
    MainActivity act;
    public ImagePickClick(MainActivity a){ act=a; }
    public void onClick(View v){
        try{
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            i.setType("image/*");
            act.startActivityForResult(i, 101);
        }catch(Exception e){
            act.onFrameTick("PICK FAIL: "+e.getMessage());
        }
    }
}
