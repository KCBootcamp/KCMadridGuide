package es.bhavishchandnani.kcmadridguide.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.navigator.Navigator;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_shops_btn)
    Button shopsBtn;
    @BindView(R.id.activity_main_activities_btn)
    Button activitiesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setupShopsBtn();
        setupActivitiesBtn();

    }

    private void setupActivitiesBtn() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateFromMainActivityToMAdridACtivitiesActivity(MainActivity.this);
            }
        };
        activitiesBtn.setOnClickListener(listener);
    }

    private void setupShopsBtn() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
            }
        };
        shopsBtn.setOnClickListener(listener);
    }
}
