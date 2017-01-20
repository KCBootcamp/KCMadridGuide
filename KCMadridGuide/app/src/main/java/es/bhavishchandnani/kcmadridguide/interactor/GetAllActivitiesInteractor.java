package es.bhavishchandnani.kcmadridguide.interactor;


import android.content.Context;

import java.util.List;

import es.bhavishchandnani.kcmadridguide.manager.net.MadridActivityEntity;
import es.bhavishchandnani.kcmadridguide.manager.net.NetworkManager;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.model.mappers.MadridActivityEntityMadridActivityMapper;
import es.bhavishchandnani.kcmadridguide.utils.MainThread;


public class GetAllActivitiesInteractor {

    private List<MadridActivity> madridActivities;

    public interface GetAllActivitiesInteractorResponse {
        public void response(MadridActivities madridActivities);
    }

    public void execute(final Context context, final GetAllActivitiesInteractorResponse response) {

        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getActivitiesFromServer(new NetworkManager.GetActivitiesListener() {
            @Override
            public void getActivityEntitiesSuccess(List<MadridActivityEntity> result) {
                madridActivities = new MadridActivityEntityMadridActivityMapper().map(result);
                if (response != null){
                    MainThread.run(new Runnable() {
                        @Override
                        public void run() {
                            response.response(MadridActivities.build(madridActivities));
                        }
                    });
                }
            }

            @Override
            public void getActivityEntitiesDidFail() {
                if (response != null){
                    MainThread.run(new Runnable() {
                        @Override
                        public void run() {
                            response.response(MadridActivities.build(null));
                        }
                    });
                }
            }

        });
    }
}
