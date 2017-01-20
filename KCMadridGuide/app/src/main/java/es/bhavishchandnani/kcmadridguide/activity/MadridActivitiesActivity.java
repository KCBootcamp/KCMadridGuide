package es.bhavishchandnani.kcmadridguide.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.fragment.MadridActivitiesFragment;
import es.bhavishchandnani.kcmadridguide.interactor.LoadAllActivitiesFromLocalCacheInteractor;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.navigator.Navigator;
import es.bhavishchandnani.kcmadridguide.view.OnElementClick;

public class MadridActivitiesActivity extends AppCompatActivity {

    private MadridActivitiesFragment madridActivitiesFragment;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madrid_activities);

        madridActivitiesFragment = (MadridActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_madrid_activities_fragment_activities);
        new LoadAllActivitiesFromLocalCacheInteractor().execute(this, getLoadAllCacheActivitiesInteractorResponse());


    }

    @NonNull
    private LoadAllActivitiesFromLocalCacheInteractor.LoadAllActivitiesFromLocalCacheInteractorResponse getLoadAllCacheActivitiesInteractorResponse() {
        return new LoadAllActivitiesFromLocalCacheInteractor.LoadAllActivitiesFromLocalCacheInteractorResponse() {
            @Override
            public void response(MadridActivities activities) {
                madridActivitiesFragment.setActivities(activities);
                initilizeMap(activities);
                madridActivitiesFragment.setOnElementClickListener(new OnElementClick<MadridActivity>() {
                    @Override
                    public void elementClicked(MadridActivity activity, int position) {
                        Navigator.navigateFromMadridActivitiesActivityActivityToMadriActivityDetailActivity(MadridActivitiesActivity.this, activity);
                    }
                });
            }
        };
    }


    private void initilizeMap(final MadridActivities activities) {
        if (googleMap == null) {
            ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    MadridActivitiesActivity.this.googleMap = googleMap;

                    setMarkers(activities);
                }
            });
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            googleMap.setMyLocationEnabled(true);
        }

    }

    public void setMarkers(MadridActivities activities) {
        for (MadridActivity activity: activities.allItems()) {
            MarkerOptions marker = new MarkerOptions().position(new LatLng(activity.getLatitude(),activity.getLongitude())).title(activity.getName());

            //marker.icon(BitmapDescriptorFactory.fromPath(shop.getLogoImgUrl()));

            googleMap.addMarker(marker);
        }
    }
}
