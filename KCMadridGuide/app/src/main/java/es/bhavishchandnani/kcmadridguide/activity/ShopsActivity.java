package es.bhavishchandnani.kcmadridguide.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.fragment.ShopsFragment;
import es.bhavishchandnani.kcmadridguide.manager.db.DBConstants;
import es.bhavishchandnani.kcmadridguide.manager.db.ShopDAO;
import es.bhavishchandnani.kcmadridguide.manager.db.provider.MadridGuideProvider;
import es.bhavishchandnani.kcmadridguide.model.Shop;
import es.bhavishchandnani.kcmadridguide.model.Shops;
import es.bhavishchandnani.kcmadridguide.navigator.Navigator;
import es.bhavishchandnani.kcmadridguide.view.OnElementClick;

public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(0, null, this);
        //new LoadAllShopsFromLocalCacheInteractor().execute(this, getLoadAllCacheShopsInteractorResponse());

    }
    //TODO use it for activities
/*
    @NonNull
    private LoadAllShopsFromLocalCacheInteractor.LoadAllShopsFromLocalCacheInteractorResponse getLoadAllCacheShopsInteractorResponse() {
        return new LoadAllShopsFromLocalCacheInteractor.LoadAllShopsFromLocalCacheInteractorResponse() {
            @Override
            public void response(Shops shops) {
                shopsFragment.setShops(shops);

                shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
                    @Override
                    public void elementClicked(Shop shop, int position) {
                        Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
                    }
                });
            }
        };
    }*/

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

}
