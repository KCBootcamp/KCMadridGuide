package es.bhavishchandnani.kcmadridguide.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import es.bhavishchandnani.kcmadridguide.R;

public class ShopsActivity extends AppCompatActivity {

    private Fragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopsFragment = getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);
    }
}
