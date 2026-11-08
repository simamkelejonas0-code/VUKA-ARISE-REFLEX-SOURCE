# VUKA-ARISE REFLEX - SOURCE

created by SIIMAMKELE NGXONONO from KIRKWOOD

Built 100% on phone in Termux. No PC. No Android Studio. No WiFi.

## What Is It

Live camera reflex arc. Eye sees -> Brain measures -> Motor speaks.

- B Sensory: spark-1.3 FRAME LIVE - EYE OPEN
- C Inter: spark-1.2 MEASURE
- D Motor: wonder-1.0 FINAL-FUSION English + isiXhosa
- SAM 3.1 DOT FRAME
- 6 Muse models total

## Files

- MainActivity.java - UI + eye
- CameraHandler.java - live camera, no blink
- ChatAdapter.java - chat
- FrameListener.java - frames
- SessionHandler.java - session
- SendClick.java, ImagePickClick.java, PreviewListener.java, ScrollRunnable.java, SignUpdater.java, UiUpdater.java

## How To Build (Termux)

1. Need android.jar 26M, aapt2, javac --release 8, d8 --min-api 21, zipalign, apksigner
2. Put key in /sdcard/Download/mtm_key.txt - NEVER commit it
3. Build with javac + d8 + aapt2, install via termux-open - NEVER pm install

## How To Use

VIDEO ON, eye live, ask What do you see? Hold steady = Identify + Measure.

MIT - keep credit: created by SIIMAMKELE NGXONONO from KIRKWOOD
