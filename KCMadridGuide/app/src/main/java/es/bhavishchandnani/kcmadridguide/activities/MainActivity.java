package es.bhavishchandnani.kcmadridguide.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.bhavishchandnani.kcmadridguide.R;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_shops_btn)
    private Button shopsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setupShopsBtn();

    }

    private void setupShopsBtn() {
        shopsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateFromMainActivityToShopsActivity();
            }
        });
    }
}
