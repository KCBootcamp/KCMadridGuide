package es.bhavishchandnani.kcmadridguide.db;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import es.bhavishchandnani.kcmadridguide.manager.db.ShopDAO;
import es.bhavishchandnani.kcmadridguide.model.Shop;


public class ShopDAOTests extends AndroidTestCase {

    public static final String SHOP_TEST_NAME = "SHOP_TEST_NAME";
    public static final String SHOP_TEST_ADDRESS = "SHOP_TEST_ADDRESS";

    public void testCanInsertANewShop(){
        final ShopDAO sut = new ShopDAO(getContext());

        final int count = getCount(sut);
        final long id = insertTestShop(sut);

        assertTrue(id > 0);
        assertTrue(count + 1 == sut.queryCursor().getCount());
    }

    public void testCanUpdateAShop(){
        final ShopDAO sut = new ShopDAO(getContext());
        final long id = insertTestShop(sut);
        final Shop shop = new Shop(1, SHOP_TEST_NAME + "2").setAddress(SHOP_TEST_ADDRESS);
        assertEquals(1,sut.update(id,shop));

        final Shop dbShop = sut.query(id);
        assertEquals(dbShop.getName(), shop.getName());
        assertEquals(dbShop.getAddress(), shop.getAddress());
    }

    public void testCanDeleteAShop(){
        final ShopDAO sut = new ShopDAO(getContext());
        final long id = insertTestShop(sut);
        final int count = getCount(sut);
        assertEquals(1,sut.delete(id));

        assertTrue(count-1 == sut.queryCursor().getCount());
    }

    public void testDeleteAll(){
        final ShopDAO sut = new ShopDAO(getContext());
        final int total = getCount(sut);
        final int deletedItems = sut.deleteAll();
        final int count = getCount(sut);
        assertEquals(deletedItems,total);
        assertEquals(0, count);
    }

    public void testQueryOneShop(){
        final ShopDAO dao = new ShopDAO(getContext());
        final long id = insertTestShop(dao);

        Shop sut = dao.query(id);

        assertNotNull(sut);
        assertEquals(sut.getName(), SHOP_TEST_NAME);
    }

    public void testQueryAllShops(){
        final ShopDAO dao = new ShopDAO(getContext());
        final long id = insertTestShop(dao);

        List<Shop> shopList = dao.query();
        assertNotNull(shopList);
        assertTrue(shopList.size()>0);

        for (Shop shop : shopList) {
            assertTrue(shop.getName().length() > 0);
        }
    }

    private long insertTestShop(ShopDAO sut) {
        final Shop shop = new Shop(1, SHOP_TEST_NAME).setAddress(SHOP_TEST_ADDRESS);
        return sut.insert(shop);
    }

    private int getCount(ShopDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }

}
