package es.bhavishchandnani.kcmadridguide.interactor;

import android.content.Context;

import java.util.List;

import es.bhavishchandnani.kcmadridguide.manager.db.MadridActivityDAO;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.utils.MainThread;

public class LoadAllActivitiesFromLocalCacheInteractor {
    public interface LoadAllActivitiesFromLocalCacheInteractorResponse {
        void response(MadridActivities activities);
    }

    public void execute(final Context context, final LoadAllActivitiesFromLocalCacheInteractorResponse response) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                MadridActivityDAO dao = new MadridActivityDAO(context);
                List<MadridActivity> activityList = dao.query();
                final MadridActivities activities = MadridActivities.build(activityList);

                if (response != null){
                    MainThread.run(new Runnable() {
                        @Override
                        public void run() {
                            response.response(activities);
                        }
                    });
                }
            }
        }).start();
    }
}
