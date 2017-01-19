package es.bhavishchandnani.kcmadridguide.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.viewmodel.MadridActivityViewModel;

public class ActivityRowViewHolder extends RecyclerView.ViewHolder {

    private TextView activityNameTextView;
    private ImageView activityLogoImageView;
    private WeakReference<Context> context;

    public ActivityRowViewHolder(View rowActivity) {
        super(rowActivity);

        context = new WeakReference<Context>(rowActivity.getContext());
        bindViews(rowActivity);
    }

    public void setActivity(final @NonNull MadridActivityViewModel activityViewModel){
        if (activityViewModel == null){
            return;
        }
        activityNameTextView.setText(activityViewModel.getName());
        Picasso.with(context.get())
                .load(activityViewModel.getLogoImgUrl())
                .placeholder(android.R.drawable.ic_menu_report_image)
                .into(activityLogoImageView);
    }

    private void bindViews(View rowShop) {
        activityNameTextView = (TextView) rowShop.findViewById(R.id.row_activity_name);
        activityLogoImageView = (ImageView) rowShop.findViewById(R.id.row_activity_logo);
    }

}
