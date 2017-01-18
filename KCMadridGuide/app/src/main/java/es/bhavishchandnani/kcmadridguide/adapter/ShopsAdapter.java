package es.bhavishchandnani.kcmadridguide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.model.Shops;
import es.bhavishchandnani.kcmadridguide.view.OnElementClick;
import es.bhavishchandnani.kcmadridguide.view.ShopRowViewHolder;
import es.bhavishchandnani.kcmadridguide.viewmodel.ShopViewModel;

import static android.content.ContentValues.TAG;

public class ShopsAdapter extends RecyclerView.Adapter<ShopRowViewHolder>{

    private final LayoutInflater layoutInflater;
    private final Shops shops;

    private OnElementClick<ShopViewModel> listener;

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
    public void onBindViewHolder(ShopRowViewHolder row, final int position) {

        final ShopViewModel shopViewModel = new ShopViewModel(
                shops.get(position).getName(),
                shops.get(position).getLogoImgUrl());

        row.setShop(shopViewModel);

        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked row shop in position " + position);
                if (listener != null){
                    listener.clickedOn(shopViewModel, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (int) shops.size();
    }

    public void setOnElementClickListener(@NonNull final OnElementClick listener){
        this.listener =  listener;
    }

}
