package es.bhavishchandnani.kcmadridguide.manager.net;


import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.util.List;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;


public class NetworkManager {
    WeakReference<Context> context;
    public interface GetShopsListener {
        public void getShopEntitiesSuccess(List<ShopEntity> result);
        public void getShopEntitiesDidFail();
    }
    public interface GetActivitiesListener {
        public void getActivityEntitiesSuccess(List<MadridActivityEntity> result);
        public void getActivityEntitiesDidFail();
    }

    public NetworkManager(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    public void getShopsFromServer(final GetShopsListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context.get());
        String url = context.get().getString(R.string.shops_url);

        StringRequest request = new StringRequest(url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("onResponse: JSON", response);
                        List<ShopEntity> shopResponse = parseShopResponse(response);
                        if (listener != null) {
                            listener.getShopEntitiesSuccess(shopResponse);
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.getShopEntitiesDidFail();
                        }
                    }
                }
        );

        requestQueue.add(request);
    }

    private List<ShopEntity> parseShopResponse(String response) {
        List<ShopEntity> result = null;
        try {
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();

            ShopResponse shopResponse = gson.fromJson(reader, ShopResponse.class);
            result = shopResponse.result;

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

    public void getActivitiesFromServer(final GetActivitiesListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context.get());
        String url = context.get().getString(R.string.activities_url);

        StringRequest request = new StringRequest(url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("onResponse: JSON", response);
                        List<MadridActivityEntity> activityResponse = parseActivityResponse(response);
                        if (listener != null) {
                            listener.getActivityEntitiesSuccess(activityResponse);
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.getActivityEntitiesDidFail();
                        }
                    }
                }
        );

        requestQueue.add(request);
    }

    private List<MadridActivityEntity> parseActivityResponse(String response) {
        List<MadridActivityEntity> result = null;
        try {
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();

            MadridActivityResponse activityResponse = gson.fromJson(reader, MadridActivityResponse.class);
            result = activityResponse.result;

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }
}
