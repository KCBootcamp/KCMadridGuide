package es.bhavishchandnani.kcmadridguide.model;

import java.util.List;

/**
 * Created by bhavishchandnani on 12/12/16.
 */
public interface IIteratable<T> {

    long size();
    T get(long index);
    List<T> allItems();
}
