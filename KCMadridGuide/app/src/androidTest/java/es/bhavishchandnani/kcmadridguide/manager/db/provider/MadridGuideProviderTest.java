package es.bhavishchandnani.kcmadridguide.manager.db.provider;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import es.bhavishchandnani.kcmadridguide.manager.db.DBConstants;
import es.bhavishchandnani.kcmadridguide.manager.db.MadridActivityDAO;
import es.bhavishchandnani.kcmadridguide.manager.db.ShopDAO;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.model.Shop;


public class MadridGuideProviderTest extends AndroidTestCase{

    //NOTE: Do not launch at the same time than DAO test they might be conflicts.
    public void testQueryAllShops() {
        ContentResolver cr = getContext().getContentResolver();

        Cursor c = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);

        assertNotNull(c);
    }


    public void testInsertAShop() {
        final ContentResolver cr = getContext().getContentResolver();

        final Shop shop = new Shop(1, "Little shop of horrors!");
        final Cursor beforeCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        final int beforeCount = beforeCursor.getCount();
        final Uri insertedUri = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));
        assertNotNull(insertedUri);

        final Cursor afterCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        final int afterCount = afterCursor.getCount();

        assertEquals(afterCount, beforeCount +1);
    }


    public void testUpdateAShop() {
        final ContentResolver cr = getContext().getContentResolver();

        final Shop shop = new Shop(1, "Little shop of horrors!");
        final Uri insertedUri = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));
        assertNotNull(insertedUri);
        Cursor c = cr.query(insertedUri,DBConstants.ALL_COLUMNS, null, null, null);

        shop.setName("Big shop of horrors!");
        final int updateResult = cr.update(insertedUri, ShopDAO.getContentValues(shop), null, null);
        assertEquals(1, updateResult);
    }

    public void testDeleteAShop() {
        final ContentResolver cr = getContext().getContentResolver();

        final Shop shop = new Shop(1, "Little shop of horrors!");
        final Uri insertedUri = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));

        final int deletedShops = cr.delete(insertedUri, null, null);

        assertEquals(deletedShops, 1);
    }

    public void testDeleteAllShops(){
        ContentResolver cr = getContext().getContentResolver();

        Cursor c = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        int count = c.getCount();

        final int deletedShops = cr.delete(MadridGuideProvider.SHOPS_URI, null, null);

        assertEquals(deletedShops, count);
    }



    public void testQueryAllActivities() {
        ContentResolver cr = getContext().getContentResolver();

        Cursor c = cr.query(MadridGuideProvider.ACTIVITIES_URI, DBConstants.ALL_COLUMNS, null, null, null);

        assertNotNull(c);
    }


    public void testInsertAnActivity() {
        final ContentResolver cr = getContext().getContentResolver();

        final MadridActivity activity = new MadridActivity(1, "Little activity of horrors!");
        final Cursor beforeCursor = cr.query(MadridGuideProvider.ACTIVITIES_URI, DBConstants.ALL_COLUMNS, null, null, null);
        final int beforeCount = beforeCursor.getCount();
        final Uri insertedUri = cr.insert(MadridGuideProvider.ACTIVITIES_URI, MadridActivityDAO.getContentValues(activity));
        assertNotNull(insertedUri);

        final Cursor afterCursor = cr.query(MadridGuideProvider.ACTIVITIES_URI, DBConstants.ALL_COLUMNS, null, null, null);
        final int afterCount = afterCursor.getCount();

        assertEquals(afterCount, beforeCount +1);
    }


    public void testUpdateAnActivity() {
        final ContentResolver cr = getContext().getContentResolver();

        final MadridActivity activity = new MadridActivity(1, "Little activity of horrors!");
        final Uri insertedUri = cr.insert(MadridGuideProvider.ACTIVITIES_URI, MadridActivityDAO.getContentValues(activity));
        assertNotNull(insertedUri);
        Cursor c = cr.query(insertedUri,DBConstants.ALL_COLUMNS, null, null, null);

        activity.setName("Big activity of horrors!");
        final int updateResult = cr.update(insertedUri, MadridActivityDAO.getContentValues(activity), null, null);
        assertEquals(1, updateResult);
    }

    public void testDeleteAnActivity() {
        final ContentResolver cr = getContext().getContentResolver();

        final MadridActivity activity = new MadridActivity(1, "Little activity of horrors!");
        final Uri insertedUri = cr.insert(MadridGuideProvider.ACTIVITIES_URI, MadridActivityDAO.getContentValues(activity));

        final int deletedShops = cr.delete(insertedUri, null, null);

        assertEquals(deletedShops, 1);
    }

    public void testDeleteAllActivities(){
        ContentResolver cr = getContext().getContentResolver();

        Cursor c = cr.query(MadridGuideProvider.ACTIVITIES_URI, DBConstants.ALL_COLUMNS, null, null, null);
        int count = c.getCount();

        final int deletedShops = cr.delete(MadridGuideProvider.ACTIVITIES_URI, null, null);

        assertEquals(deletedShops, count);
    }
}