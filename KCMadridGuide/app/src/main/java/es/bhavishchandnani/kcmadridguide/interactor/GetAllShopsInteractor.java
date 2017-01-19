package es.bhavishchandnani.kcmadridguide.interactor;


import android.content.Context;

import java.util.List;

import es.bhavishchandnani.kcmadridguide.model.Shop;
import es.bhavishchandnani.kcmadridguide.model.Shops;
import es.bhavishchandnani.kcmadridguide.model.mappers.ShopEntityShopMapper;
import es.bhavishchandnani.kcmadridguide.manager.net.NetworkManager;
import es.bhavishchandnani.kcmadridguide.manager.net.ShopEntity;
import es.bhavishchandnani.kcmadridguide.utils.MainThread;


public class GetAllShopsInteractor {

    private List<Shop> shops;

    public interface  GetAllShopsInteractorResponse{
        public void response(Shops shops);
    }

    public void execute(final Context context, final GetAllShopsInteractorResponse response) {

        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getShopsFromServer(new NetworkManager.GetShopsListener() {
            @Override
            public void getShopEntitiesSuccess(List<ShopEntity> result) {
                shops = new ShopEntityShopMapper().map(result);
                if (response != null){
                    MainThread.run(new Runnable() {
                        @Override
                        public void run() {
                            response.response(Shops.build(shops));
                        }
                    });
                }
            }

            @Override
            public void getShopEntitiesDidFail() {
                if (response != null){
                    MainThread.run(new Runnable() {
                        @Override
                        public void run() {
                            response.response(Shops.build(null));
                        }
                    });
                }
            }

        });
    }
}
