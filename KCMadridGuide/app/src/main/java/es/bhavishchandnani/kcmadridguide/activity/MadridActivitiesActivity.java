package es.bhavishchandnani.kcmadridguide.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.fragment.MadridActivitiesFragment;
import es.bhavishchandnani.kcmadridguide.interactor.LoadAllActivitiesFromLocalCacheInteractor;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.navigator.Navigator;
import es.bhavishchandnani.kcmadridguide.view.OnElementClick;

public class MadridActivitiesActivity extends AppCompatActivity {

    private MadridActivitiesFragment madridActivitiesFragment;

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

                madridActivitiesFragment.setOnElementClickListener(new OnElementClick<MadridActivity>() {
                    @Override
                    public void elementClicked(MadridActivity activity, int position) {
                        Navigator.navigateFromMadridActivitiesActivityActivityToMadriActivityDetailActivity(MadridActivitiesActivity.this, activity);
                    }
                });
            }
        };
    }
}
