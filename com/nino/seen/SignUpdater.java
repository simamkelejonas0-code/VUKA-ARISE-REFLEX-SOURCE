package com.nino.seen;
public class SignUpdater implements Runnable {
    MainActivity act; String word; float conf;
    public SignUpdater(MainActivity a, String w, float c){ act=a; word=w; conf=c; }
    public void run(){
        act.statusText.setText("DETECTED: "+word+" ("+(int)(conf*100)+"%)");
        act.chat.addMessage("DEAF (AI Voice)", word+" ["+(int)(conf*100)+"%]", 0xFF00FF00);
    }
}
