package es.bhavishchandnani.kcmadridguide.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import es.bhavishchandnani.kcmadridguide.R;

public class MadridActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madrid_activities);

        //madridActivitiesFragment = (MadridActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);

        //new LoadAllActivitiesFromLocalCacheInteractor().execute(this, getLoadAllCacheActivitiesInteractorResponse());

    }
    //TODO use it for activities
/*
    @NonNull
    private LoadAllActivitiesFromLocalCacheInteractor.LoadAllActivitiesFromLocalCacheInteractorResponse getLoadAllCacheActivitiesInteractorResponse() {
        return new LoadAllActivitiesFromLocalCacheInteractor.LoadAllActivitiesFromLocalCacheInteractorResponse() {
            @Override
            public void response(MadridActivities activities) {
                madridActivitiesFragment.setActivities(activities);

                shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
                    @Override
                    public void elementClicked(Shop shop, int position) {
                        Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
                    }
                });
            }
        };
    }*/
}
