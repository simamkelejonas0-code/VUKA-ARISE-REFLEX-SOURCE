package com.nino.seen;
import android.widget.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import java.io.InputStream;
public class ChatAdapter {
    LinearLayout layout;
    ScrollView scroll;
    public ChatAdapter(LinearLayout l, ScrollView s){ layout=l; scroll=s; }
    public void addMessage(String who, String msg, int color){
        TextView tv = new TextView(layout.getContext());
        tv.setText(who+": "+msg);
        tv.setTextColor(color);
        tv.setTextSize(15);
        tv.setPadding(20,8,20,8);
        layout.addView(tv);
        scroll.post(new ScrollRunnable(scroll));
    }
    public void addImage(String who, Bitmap bmp){
        LinearLayout row = new LinearLayout(layout.getContext());
        row.setOrientation(1);
        TextView tv = new TextView(layout.getContext());
        tv.setText(who+": [IMAGE]");
        tv.setTextColor(0xFF00FFFF);
        tv.setTextSize(12);
        row.addView(tv);
        ImageView iv = new ImageView(layout.getContext());
        iv.setImageBitmap(bmp);
        iv.setAdjustViewBounds(true);
        iv.setMaxHeight(500);
        iv.setLayoutParams(new LinearLayout.LayoutParams(-1, ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setPadding(20,5,20,10);
        row.addView(iv);
        layout.addView(row);
        scroll.post(new ScrollRunnable(scroll));
    }
}
