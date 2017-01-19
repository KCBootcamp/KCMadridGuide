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
import es.bhavishchandnani.kcmadridguide.model.Shop;
import es.bhavishchandnani.kcmadridguide.model.Shops;
import es.bhavishchandnani.kcmadridguide.view.OnElementClick;
import es.bhavishchandnani.kcmadridguide.view.ShopRowViewHolder;
import es.bhavishchandnani.kcmadridguide.viewmodel.ShopViewModel;

import static android.content.ContentValues.TAG;

public class ShopsAdapter extends RecyclerView.Adapter<ShopRowViewHolder>{

    private final LayoutInflater layoutInflater;
    private final Shops shops;

    private List<OnElementClick<Shop>> listeners;

    public ShopsAdapter(Shops shops, Context context) {
        this.shops = shops;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ShopRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_shop, parent, false);
        return new ShopRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShopRowViewHolder row, final int position) {

        final Shop shop = shops.get(position);

        row.setShop(new ShopViewModel(shop.getName(), shop.getLogoImgUrl()));

        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked row shop in position " + position);
                for (OnElementClick<Shop> listener: getListeners()) {
                    listener.elementClicked(shop, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (int) shops.size();
    }

    public List<OnElementClick<Shop>> getListeners(){
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
