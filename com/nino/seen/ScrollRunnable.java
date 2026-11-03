package com.nino.seen;
import android.widget.ScrollView;
public class ScrollRunnable implements Runnable {
    ScrollView s;
    public ScrollRunnable(ScrollView sv){ s=sv; }
    public void run(){ s.fullScroll(ScrollView.FOCUS_DOWN); }
}
