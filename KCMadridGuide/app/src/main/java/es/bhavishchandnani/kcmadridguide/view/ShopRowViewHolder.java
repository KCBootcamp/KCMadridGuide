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
import es.bhavishchandnani.kcmadridguide.viewmodel.ShopViewModel;


public class ShopRowViewHolder extends RecyclerView.ViewHolder {

    private TextView shopNameTextView;
    private ImageView shopLogoImageView;
    private WeakReference<Context> context;

    public ShopRowViewHolder(View rowShop) {
        super(rowShop);

        context = new WeakReference<Context>(rowShop.getContext());
        bindViews(rowShop);
    }

    public void setShop(final @NonNull ShopViewModel shop){
        if (shop == null){
            return;
        }
        shopNameTextView.setText(shop.getName());
        Picasso.with(context.get())
                .load(shop.getLogoImgUrl())
                .placeholder(android.R.drawable.ic_menu_report_image)
                .into(shopLogoImageView);
    }

    private void bindViews(View rowShop) {
        shopNameTextView = (TextView) rowShop.findViewById(R.id.row_shop_name);
        shopLogoImageView = (ImageView) rowShop.findViewById(R.id.row_shop_logo);
    }

}
