package es.bhavishchandnani.kcmadridguide.interactor;


import android.content.Context;

import com.squareup.picasso.Picasso;

import es.bhavishchandnani.kcmadridguide.manager.db.ShopDAO;
import es.bhavishchandnani.kcmadridguide.model.Shop;
import es.bhavishchandnani.kcmadridguide.model.Shops;
import es.bhavishchandnani.kcmadridguide.utils.MainThread;

public class CacheAllShopsInteractor {
    private boolean success;
    public interface  CacheAllShopsInteractorResponse{
        public void response(boolean success);
    }

    public void execute(final Context context, final Shops shops, final CacheAllShopsInteractorResponse response) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);
                success = true;
                for (Shop shop: shops.allItems()){
                    success = dao.insert(shop) > 0;
                    Picasso.with(context).load(shop.getLogoImgUrl()).fetch();
                    Picasso.with(context).load(shop.getImageUrl()).fetch();
                    if(!success){
                        break;
                    }
                }

                if (response != null){
                    MainThread.run(new Runnable() {
                        @Override
                        public void run() {
                            response.response(success);
                        }
                    });
                }
            }
        }).start();
    }

}
