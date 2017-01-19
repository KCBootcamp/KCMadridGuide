package es.bhavishchandnani.kcmadridguide.utils;

import android.os.Handler;
import android.os.Looper;

public class MainThread {
    public static void run(final Runnable runnable){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });
    }
}
