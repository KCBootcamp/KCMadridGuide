package es.bhavishchandnani.kcmadridguide.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CacheManager {


    public static boolean cacheHasExpired(Context context, Date date){
        SharedPreferences preferences = context.getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE);
        long lastSyncDate = preferences.getLong(Constants.MY_PREFERENCES_LAST_SYNC_DATE, 0);
        long sevenDays = TimeUnit.DAYS.toMillis(7);
        if (lastSyncDate == 0 || ((date.getTime() - lastSyncDate) > sevenDays)){
            return true;
        } else {
            return false;
        }

    }

    public static void updateSyncDate(Context context, Date date){
        SharedPreferences preferences = context.getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(Constants.MY_PREFERENCES_LAST_SYNC_DATE, date.getTime());
        editor.commit();
    }
}
