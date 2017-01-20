package es.bhavishchandnani.kcmadridguide.db;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import es.bhavishchandnani.kcmadridguide.manager.db.MadridActivityDAO;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;


public class MadridActivityDAOTests extends AndroidTestCase {

    public static final String ACTIVITY_TEST_NAME = "ACTIVITY_TEST_NAME";
    public static final String ACTIVITY_TEST_ADDRESS = "ACTIVITY_TEST_ADDRESS";

    public void testCanInsertANewMadridActivity(){
        final MadridActivityDAO sut = new MadridActivityDAO(getContext());

        final int count = getCount(sut);
        final long id = insertTestMadridActivity(sut);

        assertTrue(id > 0);
        assertTrue(count + 1 == sut.queryCursor().getCount());
    }

    public void testCanUpdateAMadridActivity(){
        final MadridActivityDAO sut = new MadridActivityDAO(getContext());
        final long id = insertTestMadridActivity(sut);
        final MadridActivity shop = new MadridActivity(1, ACTIVITY_TEST_NAME + "2").setAddress(ACTIVITY_TEST_ADDRESS);
        assertEquals(1,sut.update(id,shop));

        final MadridActivity dbMadridActivity = sut.query(id);
        assertEquals(dbMadridActivity.getName(), shop.getName());
        assertEquals(dbMadridActivity.getAddress(), shop.getAddress());
    }

    public void testCanDeleteAMadridActivity(){
        final MadridActivityDAO sut = new MadridActivityDAO(getContext());
        final long id = insertTestMadridActivity(sut);
        final int count = getCount(sut);
        assertEquals(1,sut.delete(id));

        assertTrue(count-1 == sut.queryCursor().getCount());
    }

    public void testDeleteAll(){
        final MadridActivityDAO sut = new MadridActivityDAO(getContext());
        final int total = getCount(sut);
        final int deletedItems = sut.deleteAll();
        final int count = getCount(sut);
        assertEquals(deletedItems,total);
        assertEquals(0, count);
    }

    public void testQueryOneMadridActivity(){
        final MadridActivityDAO dao = new MadridActivityDAO(getContext());
        final long id = insertTestMadridActivity(dao);

        MadridActivity sut = dao.query(id);

        assertNotNull(sut);
        assertEquals(sut.getName(), ACTIVITY_TEST_NAME);
    }

    public void testQueryAllMadridActivities(){
        final MadridActivityDAO dao = new MadridActivityDAO(getContext());
        final long id = insertTestMadridActivity(dao);

        List<MadridActivity> shopList = dao.query();
        assertNotNull(shopList);
        assertTrue(shopList.size()>0);

        for (MadridActivity shop : shopList) {
            assertTrue(shop.getName().length() > 0);
        }
    }

    private long insertTestMadridActivity(MadridActivityDAO sut) {
        final MadridActivity shop = new MadridActivity(1, ACTIVITY_TEST_NAME).setAddress(ACTIVITY_TEST_ADDRESS);
        return sut.insert(shop);
    }

    private int getCount(MadridActivityDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }

}
