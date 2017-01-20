package es.bhavishchandnani.kcmadridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import es.bhavishchandnani.kcmadridguide.interactor.CacheAllActivitiesInteractor;
import es.bhavishchandnani.kcmadridguide.interactor.CacheAllShopsInteractor;
import es.bhavishchandnani.kcmadridguide.interactor.GetAllActivitiesInteractor;
import es.bhavishchandnani.kcmadridguide.interactor.GetAllShopsInteractor;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;
import es.bhavishchandnani.kcmadridguide.model.Shops;


public class MadridGuideApp extends Application {

    private static WeakReference<Context> appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        // App Initialization
        appContext = new WeakReference(getApplicationContext());

        //insertTestData();

        new GetAllShopsInteractor().execute(getApplicationContext(),
                new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
                    @Override
                    public void response(Shops shops) {
                        CacheShops(shops);
                    }
                });
        new GetAllActivitiesInteractor().execute(getApplicationContext(),
                new GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse() {
                    @Override
                    public void response(MadridActivities madridActivities) {
                        CacheActivities(madridActivities);
                    }
                });

        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
    }

    private void CacheActivities(MadridActivities madridActivities) {
        new CacheAllActivitiesInteractor().execute(getApplicationContext(), madridActivities, new CacheAllActivitiesInteractor.CacheAllActivitiesInteractorResponse() {
            @Override
            public void response(boolean success) {
                // Por ejemplo: Actualizar fecha del ultimo cacheo
                // Ocultar el progressBar
            }
        });
    }

    private void CacheShops(Shops shops) {
        new CacheAllShopsInteractor().execute(getApplicationContext(), shops, new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {
            @Override
            public void response(boolean success) {
                // Por ejemplo: Actualizar fecha del ultimo cacheo
                // Ocultar el progressBar
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
}
