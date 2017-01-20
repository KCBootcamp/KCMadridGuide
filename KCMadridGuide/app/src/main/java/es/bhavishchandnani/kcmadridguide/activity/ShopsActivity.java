package es.bhavishchandnani.kcmadridguide.activity;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.fragment.ShopsFragment;
import es.bhavishchandnani.kcmadridguide.manager.db.DBConstants;
import es.bhavishchandnani.kcmadridguide.manager.db.ShopDAO;
import es.bhavishchandnani.kcmadridguide.manager.db.provider.MadridGuideProvider;
import es.bhavishchandnani.kcmadridguide.model.Shop;
import es.bhavishchandnani.kcmadridguide.model.Shops;
import es.bhavishchandnani.kcmadridguide.navigator.Navigator;
import es.bhavishchandnani.kcmadridguide.view.OnElementClick;

public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ShopsFragment shopsFragment;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this,
                MadridGuideProvider.SHOPS_URI,
                DBConstants.ALL_COLUMNS,        // projection
                null,                           // Where
                null,                           // Campos del Where
                null                            // Order
        );
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        final Shops shops = ShopDAO.getShopsFromCursor(data);
        shopsFragment.setShops(shops);
        initilizeMap(shops);
        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void elementClicked(Shop shop, int position) {
                Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void initilizeMap(final Shops shops) {
        if (googleMap == null) {
            ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    ShopsActivity.this.googleMap = googleMap;
                    setMarkers(shops);
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

    public void setMarkers(Shops shops) {
        for (Shop shop: shops.allItems()) {
            MarkerOptions marker = new MarkerOptions().position(new LatLng(shop.getLatitude(),shop.getLongitude())).title(shop.getName());

                //marker.icon(BitmapDescriptorFactory.fromPath(shop.getLogoImgUrl()));

            googleMap.addMarker(marker);
        }
    }
}
