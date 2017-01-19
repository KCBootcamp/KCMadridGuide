package es.bhavishchandnani.kcmadridguide.navigator;

import android.content.Intent;

import es.bhavishchandnani.kcmadridguide.activity.MadridActivitiesActivity;
import es.bhavishchandnani.kcmadridguide.activity.MainActivity;
import es.bhavishchandnani.kcmadridguide.activity.ShopDetailActivity;
import es.bhavishchandnani.kcmadridguide.activity.ShopsActivity;
import es.bhavishchandnani.kcmadridguide.model.Shop;

import static es.bhavishchandnani.kcmadridguide.utils.Constants.INTENT_KEY_SHOP_DETAIL;

public class Navigator {

    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {
        final Intent intent = new Intent(mainActivity, ShopsActivity.class);
        mainActivity.startActivity(intent);
        return intent;
    }

    public static Intent navigateFromShopsActivityToShopDetailActivity(final ShopsActivity shopsActivity, Shop shop) {
        final Intent intent = new Intent(shopsActivity, ShopDetailActivity.class);
        intent.putExtra(INTENT_KEY_SHOP_DETAIL, shop);
        shopsActivity.startActivity(intent);
        return intent;
    }

    public static Intent navigateFromMainActivityToMAdridACtivitiesActivity(MainActivity mainActivity) {
        final Intent intent = new Intent(mainActivity, MadridActivitiesActivity.class);
        mainActivity.startActivity(intent);
        return intent;
    }
}
