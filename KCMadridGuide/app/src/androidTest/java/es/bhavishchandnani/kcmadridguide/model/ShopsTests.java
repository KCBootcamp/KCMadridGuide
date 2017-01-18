package es.bhavishchandnani.kcmadridguide.model;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;


public class ShopsTests extends AndroidTestCase {

    public void testCreatingAShopsWithNullListReturnsNonNullShops(){
        Shops sut = Shops.build(null);
        assertNotNull(sut);
        assertNotNull(sut.allShops());
    }

    public void testCreatingAShopsWithAListReturnsNonNullShops(){
        List<Shop> data = getShops();

        Shops sut = Shops.build(data);
        assertNotNull(sut);
        assertNotNull(sut.allShops());
        assertEquals(sut.allShops(), data);
        assertEquals(sut.allShops().size(), data.size());
    }

    @NonNull
    private List<Shop> getShops() {
        List<Shop> data = new ArrayList<>();
        data.add(new Shop(1,"1").setAddress("add1"));
        data.add(new Shop(2,"2").setAddress("add2"));
        return data;
    }
}
