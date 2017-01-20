package es.bhavishchandnani.kcmadridguide.interactor;


import android.content.Context;

import es.bhavishchandnani.kcmadridguide.manager.db.MadridActivityDAO;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.utils.MainThread;

public class CacheAllActivitiesInteractor {
    private boolean success;
    public interface CacheAllActivitiesInteractorResponse{
        public void response(boolean success);
    }

    public void execute(final Context context, final MadridActivities activities, final CacheAllActivitiesInteractorResponse response) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                MadridActivityDAO dao = new MadridActivityDAO(context);
                success = true;
                for (MadridActivity activity: activities.allItems()){
                    success = dao.insert(activity) > 0;
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
