package es.bhavishchandnani.kcmadridguide.interactor;

import android.content.Context;

import java.util.List;

import es.bhavishchandnani.kcmadridguide.manager.db.ShopDAO;
import es.bhavishchandnani.kcmadridguide.model.Shop;
import es.bhavishchandnani.kcmadridguide.model.Shops;
import es.bhavishchandnani.kcmadridguide.utils.MainThread;

public class LoadAllShopsFromLocalCacheInteractor {
    public interface LoadAllShopsFromLocalCacheInteractorResponse {
        void response( Shops shops);
    }

    public void execute(final Context context, final LoadAllShopsFromLocalCacheInteractorResponse response) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);
                List<Shop> shopList = dao.query();
                final Shops shops = Shops.build(shopList);

                if (response != null){
                    MainThread.run(new Runnable() {
                        @Override
                        public void run() {
                            response.response(shops);
                        }
                    });
                }
            }
        }).start();
    }
}
