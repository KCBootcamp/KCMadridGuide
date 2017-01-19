package es.bhavishchandnani.kcmadridguide.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.bhavishchandnani.kcmadridguide.R;
import es.bhavishchandnani.kcmadridguide.adapter.ShopsAdapter;
import es.bhavishchandnani.kcmadridguide.model.Shop;
import es.bhavishchandnani.kcmadridguide.model.Shops;
import es.bhavishchandnani.kcmadridguide.view.GridAutofitLayoutManager;
import es.bhavishchandnani.kcmadridguide.view.OnElementClick;

public class ShopsFragment extends Fragment {

    private RecyclerView shopsRecyclerView;
    private ShopsAdapter adapter;
    private Shops shops;
    private OnElementClick<Shop> listener;

    public ShopsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shops, container, false);
        shopsRecyclerView = (RecyclerView) view.findViewById(R.id.shops_recycler_view);
        shopsRecyclerView.setLayoutManager(new GridAutofitLayoutManager(getActivity(), Math.round(getResources().getDimension(R.dimen.row_width))));

        return view;
    }

    private void updateUI() {
        adapter = new ShopsAdapter(shops, getActivity());
        shopsRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void elementClicked(Shop shop, int position) {
                if (listener != null){
                    listener.elementClicked(shop, position);
                }
            }
        });
    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
        updateUI();
    }

    public OnElementClick<Shop> getListener() {
        return listener;
    }

    public void setOnElementClickListener(OnElementClick<Shop> listener) {
        this.listener = listener;
    }

}
