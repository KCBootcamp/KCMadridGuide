package es.bhavishchandnani.kcmadridguide.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.bhavishchandnani.kcmadridguide.MadridGuideApp;
import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.navigator.Navigator;
import es.bhavishchandnani.kcmadridguide.view.ActivityInterface;
import es.bhavishchandnani.kcmadridguide.view.CustomViews;
import es.bhavishchandnani.kcmadridguide.view.DialogListener;

import static es.bhavishchandnani.kcmadridguide.MadridGuideApp.getAppContext;

public class MainActivity extends AppCompatActivity{
    @BindView(R.id.activity_main_shops_btn)
    Button shopsBtn;
    @BindView(R.id.activity_main_activities_btn)
    Button activitiesBtn;
    @BindView(R.id.activity_main_loading_view_switcher)
    ViewSwitcher viewSwitcher;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((MadridGuideApp)getAppContext()).setActivityInterface(getInterface());
        ((MadridGuideApp)getAppContext()).init();
        setupShopsBtn();
        setupActivitiesBtn();

    }

    @NonNull
    private ActivityInterface getInterface() {
        return new ActivityInterface() {

            @Override
            public void loadFinished(boolean success) {
                hideProgressBar();
                if (success) {
                    shopsBtn.setEnabled(true);
                    activitiesBtn.setEnabled(true);
                }else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    dialog = CustomViews.buildGenericAlertDailog(builder, R.string.error_loading_dialog_title, R.string.error_loading_dialog_message, R.drawable.logo, android.R.string.ok, 0, new DialogListener() {
                        @Override
                        public void OnPositiveClick() {
                            dialog.dismiss();
                        }

                        @Override
                        public void OnNegativeClick() {

                        }
                    });
                    dialog.show();
                }
            }

            @Override
            public void showMessage() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                dialog = CustomViews.buildGenericAlertDailog(builder, R.string.check_conection_dialog_title, R.string.check_conection_dialog_message, R.drawable.logo, android.R.string.ok, 0, new DialogListener() {
                    @Override
                    public void OnPositiveClick() {
                        dialog.dismiss();
                    }

                    @Override
                    public void OnNegativeClick() {

                    }
                });
                dialog.show();
            }
        };
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

    public void hideProgressBar(){
        Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in);
        viewSwitcher.setAnimation(fadeIn);
        viewSwitcher.showNext();
    }

}
