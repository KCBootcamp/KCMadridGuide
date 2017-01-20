package es.bhavishchandnani.kcmadridguide.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Connectivity {
    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
