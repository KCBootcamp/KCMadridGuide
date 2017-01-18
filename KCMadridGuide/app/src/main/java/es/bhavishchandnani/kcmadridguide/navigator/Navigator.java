package es.bhavishchandnani.kcmadridguide.navigator;

import android.content.Intent;

import es.bhavishchandnani.kcmadridguide.activity.MainActivity;
import es.bhavishchandnani.kcmadridguide.activity.ShopsActivity;

public class Navigator {
    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {
        final Intent intent = new Intent(mainActivity, ShopsActivity.class);
        mainActivity.startActivity(intent);
        return intent;
    }
}
