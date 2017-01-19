package es.bhavishchandnani.kcmadridguide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.view.ActivityRowViewHolder;
import es.bhavishchandnani.kcmadridguide.view.OnElementClick;
import es.bhavishchandnani.kcmadridguide.viewmodel.MadridActivityViewModel;

import static android.content.ContentValues.TAG;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivityRowViewHolder>{

    private final LayoutInflater layoutInflater;
    private final MadridActivities activities;

    private List<OnElementClick<MadridActivity>> listeners;

    public ActivitiesAdapter(MadridActivities activities, Context context) {
        this.activities = activities;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_activity, parent, false);
        return new ActivityRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ActivityRowViewHolder row, final int position) {

        final MadridActivity activity = activities.get(position);

        row.setActivity(new MadridActivityViewModel(activity.getName(), activity.getLogoImgUrl()));

        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked row shop in position " + position);
                for (OnElementClick<MadridActivity> listener: getListeners()) {
                    listener.elementClicked(activity, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (int) activities.size();
    }

    public List<OnElementClick<MadridActivity>> getListeners(){
        if (listeners == null){
            listeners = new LinkedList<>();
        }
        return listeners;
    }

    public void setOnElementClickListener(@NonNull final OnElementClick listener){
        if (listener != null) {
            getListeners().add(listener);
        }
    }

}
