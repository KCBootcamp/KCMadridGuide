package es.bhavishchandnani.kcmadridguide.model;

import java.util.List;

/**
 * Created by bhavishchandnani on 12/12/16.
 */
public interface IShopsIteratable {

    long size();
    Shop get(long index);
    List<Shop> allShops();
}
