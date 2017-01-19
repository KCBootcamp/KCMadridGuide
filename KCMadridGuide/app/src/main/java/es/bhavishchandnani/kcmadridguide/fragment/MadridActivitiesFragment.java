package es.bhavishchandnani.kcmadridguide.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.adapter.ActivitiesAdapter;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.view.GridAutofitLayoutManager;
import es.bhavishchandnani.kcmadridguide.view.OnElementClick;

public class MadridActivitiesFragment extends Fragment {

    private RecyclerView activitiesRecyclerView;
    private ActivitiesAdapter adapter;
    private MadridActivities activities;
    private OnElementClick<MadridActivity> listener;

    public MadridActivitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        activitiesRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        activitiesRecyclerView.setLayoutManager(new GridAutofitLayoutManager(getActivity(), Math.round(getResources().getDimension(R.dimen.row_width))));

        return view;
    }

    private void updateUI() {
        adapter = new ActivitiesAdapter(activities, getActivity());
        activitiesRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new OnElementClick<MadridActivity>() {
            @Override
            public void elementClicked(MadridActivity activity, int position) {
                if (listener != null){
                    listener.elementClicked(activity, position);
                }
            }
        });
    }

    public MadridActivities getActivities() {
        return activities;
    }

    public void setActivities(MadridActivities activities) {
        this.activities = activities;
        updateUI();
    }

    public OnElementClick<MadridActivity> getListener() {
        return listener;
    }

    public void setOnElementClickListener(OnElementClick<MadridActivity> listener) {
        this.listener = listener;
    }
}
