package com.nino.seen;
public class UiUpdater implements Runnable {
    MainActivity act; String msg; boolean isTick;
    public UiUpdater(MainActivity a, String m, boolean tick){ act=a; msg=m; isTick=tick; }
    public void run(){ act.statusText.setText("SEEN VO.O - OFFLINE\n"+msg); }
}
