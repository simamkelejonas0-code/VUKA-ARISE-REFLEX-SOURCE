package com.nino.seen;
import android.view.View;
public class SendClick implements View.OnClickListener {
    MainActivity act;
    public SendClick(MainActivity a){ act=a; }
    public void onClick(View v){ act.sendTyped(); }
}
