package es.bhavishchandnani.kcmadridguide;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.Date;

import es.bhavishchandnani.kcmadridguide.interactor.CacheAllActivitiesInteractor;
import es.bhavishchandnani.kcmadridguide.interactor.CacheAllShopsInteractor;
import es.bhavishchandnani.kcmadridguide.interactor.GetAllActivitiesInteractor;
import es.bhavishchandnani.kcmadridguide.interactor.GetAllShopsInteractor;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;
import es.bhavishchandnani.kcmadridguide.model.Shops;
import es.bhavishchandnani.kcmadridguide.utils.CacheManager;
import es.bhavishchandnani.kcmadridguide.utils.Connectivity;
import es.bhavishchandnani.kcmadridguide.view.ActivityInterface;


public class MadridGuideApp extends Application {

    private static WeakReference<Context> appContext;
    private ActivityInterface listener;

    @Override
    public void onCreate() {
        super.onCreate();

        // App Initialization
        appContext = new WeakReference(getApplicationContext());

        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
    }

    public void init() {
        boolean isConectionAvilable = Connectivity.isNetworkAvailable(getAppContext());
        boolean cacheHasExpired = CacheManager.cacheHasExpired(getAppContext(),new Date());

        if (cacheHasExpired) {
            if (isConectionAvilable) {
                callShopsInteractor();
            } else  {

                Log.d("TAG", "onCreate: No connection");
                listener.showMessage();
                listener.loadFinished(false);
            }
        }else{
            listener.loadFinished(true);
        }
    }

    private void callShopsInteractor() {
        new GetAllShopsInteractor().execute(getApplicationContext(),
                new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
                    @Override
                    public void response(Shops shops) {
                        CacheShops(shops);
                    }
                });
    }

    private void callActivitiesInteractor() {
        new GetAllActivitiesInteractor().execute(getApplicationContext(),
                new GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse() {
                    @Override
                    public void response(MadridActivities madridActivities) {
                        CacheActivities(madridActivities);
                    }
                });
    }


    private void CacheShops(Shops shops) {
        new CacheAllShopsInteractor().execute(getApplicationContext(), shops, new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {
            @Override
            public void response(boolean success) {
                if(success){
                    callActivitiesInteractor();
                }
            }
        });
    }


    private void CacheActivities(MadridActivities madridActivities) {
        new CacheAllActivitiesInteractor().execute(getApplicationContext(), madridActivities, new CacheAllActivitiesInteractor.CacheAllActivitiesInteractorResponse() {
            @Override
            public void response(boolean success) {
                listener.loadFinished(success);
                if (success){
                    CacheManager.updateSyncDate(getAppContext(),new Date());
                }
            }
        });
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    public static Context getAppContext() {
        return appContext.get();
    }


    public void setActivityInterface(ActivityInterface listener){
        this.listener= listener;
    }
}
