package es.bhavishchandnani.kcmadridguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;

import static es.bhavishchandnani.kcmadridguide.utils.Constants.INTENT_KEY_ACTIVITY_DETAIL;

public class MadridActivityDetailActivity extends AppCompatActivity {


    @BindView(R.id.activity_madrid_activities_activity_name_text)
    TextView activityNameText;
    @BindView(R.id.activity_madrid_activities_activity_logo_image)
    ImageView activityLogoImage;

    MadridActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madrid_detail);

        ButterKnife.bind(this);
        getDetailActivityFromCallingIntent();
        updateUI();

    }

    private void getDetailActivityFromCallingIntent() {
        Intent i = getIntent();
        if (i != null){
            activity = (MadridActivity) i.getSerializableExtra(INTENT_KEY_ACTIVITY_DETAIL);
        }
    }

    private void updateUI() {
        activityNameText.setText(activity.getName());
        Picasso.with(this)
                .load(activity.getLogoImgUrl())
                .into(activityLogoImage);
    }
}
