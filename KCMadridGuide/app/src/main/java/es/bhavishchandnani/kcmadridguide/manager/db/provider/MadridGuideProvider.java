package es.bhavishchandnani.kcmadridguide.manager.db.provider;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import es.bhavishchandnani.kcmadridguide.manager.db.MadridActivityDAO;
import es.bhavishchandnani.kcmadridguide.manager.db.ShopDAO;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.model.Shop;


public class MadridGuideProvider extends ContentProvider {
    public static final String MADRIDGUIDE_PROVIDER = "es.bhavishchandnani.kcmadridguide.provider";

    public  static final Uri SHOPS_URI = Uri.parse("content://" + MADRIDGUIDE_PROVIDER + "/shops");
    public  static final Uri ACTIVITIES_URI = Uri.parse("content://" + MADRIDGUIDE_PROVIDER + "/activities");

    // Create the constants used to differentiate between the different URI requests.
    private static final int ALL_SHOPS = 1;
    private static final int SINGLE_SHOP = 2;
    private static final int ALL_ACTIVITIES = 3;
    private static final int SINGLE_ACTIVITY = 4;

    private static final UriMatcher uriMatcher;
    // Populate the UriMatcher object, where a URI ending in 'elements' will correspond to a request for all items, and 'elements/[rowID]' represents a single row.
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //content://es.bhavishchandnani.madridguide.provider/shops
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "shops", ALL_SHOPS);
        //content://es.bhavishchandnani.madridguide.provider/shops/212
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "shops/#", SINGLE_SHOP);
        //content://es.bhavishchandnani.madridguide.provider/shops
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "activities", ALL_ACTIVITIES);
        //content://es.bhavishchandnani.madridguide.provider/shops/212
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "activities/#", SINGLE_ACTIVITY);
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String where, String[] whereArgs, String sortOrder) {
        // Open the database.

       ShopDAO dao = new ShopDAO(getContext());
       MadridActivityDAO activityDAO = new MadridActivityDAO(getContext());
        // Replace these with valid SQL statements if necessary.
        String groupBy = null;
        String having = null;
        // Use an SQLite Query Builder to simplify constructing the database query.

        Cursor cursor = null;
        String rowID;
        // If this is a row query, limit the result set to the passed in row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP :
                rowID = uri.getPathSegments().get(1);
                cursor = dao.queryCursor(Long.parseLong(rowID));
                break;
            case ALL_SHOPS:
                cursor = dao.queryCursor();
                break;
            case SINGLE_ACTIVITY :
                rowID = uri.getPathSegments().get(1);
                cursor = activityDAO.queryCursor(Long.parseLong(rowID));
                break;
            case ALL_ACTIVITIES:
                cursor = activityDAO.queryCursor();
                break;
            default: break;
        }

        // Return the result Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);


        return cursor;
    }


    // Se implementa sobre todo cuando va a ser público y se quiere que se vea desde fuera para indicar
    // que tipo MIME se utiliza para que lo puedan usar.
    @Nullable
    @Override
    public String getType(Uri uri) {

        String type = null;

        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP:
                type = "vnd.android.cursor.item/vnd.es.bhavishchandnani.madridguide.provider";
                break;
            case ALL_SHOPS:
                type = "vnd.android.cursor.dir/vnd.es.bhavishchandnani.madridguide.provider";
                break;
            case SINGLE_ACTIVITY:
                type = "vnd.android.cursor.item/vnd.es.bhavishchandnani.madridguide.provider";
                break;
            case ALL_ACTIVITIES:
                type = "vnd.android.cursor.dir/vnd.es.bhavishchandnani.madridguide.provider";
                break;
            default:
                break;
        }

        return type;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id = -1;
        switch (uriMatcher.match(uri)) {
            case ALL_SHOPS:
                // Open a read / write database to support the transaction.

                ShopDAO dao = new ShopDAO(getContext());

                // Insert the values into the table
                Shop shop = ShopDAO.getShopFromContentValues(contentValues);
                id = dao.insert(shop);
                break;
            case ALL_ACTIVITIES:
                // Open a read / write database to support the transaction.

                MadridActivityDAO activityDAO = new MadridActivityDAO(getContext());

                // Insert the values into the table
                MadridActivity activityMadrid = MadridActivityDAO.getMadridActivityFromContentValues(contentValues);
                id = activityDAO.insert(activityMadrid);
                break;
            default:
                break;
        }

        // Construct and return the URI of the newly inserted row.
        if (id == -1) {
            return null;
        }

        // Construct and return the URI of the newly inserted row.
        Uri insertedUri = null;
        switch (uriMatcher.match(uri)) {
            case ALL_SHOPS:
                insertedUri = ContentUris.withAppendedId(SHOPS_URI, id);
                break;
            case ALL_ACTIVITIES:
                insertedUri = ContentUris.withAppendedId(ACTIVITIES_URI, id);
                break;
            default:
                break;
        }
        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);
        getContext().getContentResolver().notifyChange(insertedUri, null);

        return insertedUri;

    }

    @Override
    public int delete(Uri uri, String where, String[] whereSelection) {
        //content://es.bhavishchandnani.madridguide.provider/shops/212
        // Open a read / write database to support the transaction.
        ShopDAO dao = new ShopDAO(getContext());
        MadridActivityDAO activityDAO = new MadridActivityDAO(getContext());
        int deleteCount = 0;

        String rowID;
        // If this is a row URI, limit the deletion to the specified row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP:
                rowID = uri.getPathSegments().get(1);
                deleteCount = dao.delete(Long.parseLong(rowID));
                break;
            case ALL_SHOPS:
                deleteCount = dao.deleteAll();
                break;
            case SINGLE_ACTIVITY:
                rowID = uri.getPathSegments().get(1);
                deleteCount = activityDAO.delete(Long.parseLong(rowID));
                break;
            case ALL_ACTIVITIES:
                deleteCount = activityDAO.deleteAll();
                break;
            default:
                break;
        }

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the number of deleted items.
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int updateResult = 0;
        String rowID;
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP:
                // Open a read / write database to support the transaction.

                ShopDAO dao = new ShopDAO(getContext());

                // Insert the values into the table
                Shop shop = ShopDAO.getShopFromContentValues(contentValues);
                rowID = uri.getPathSegments().get(1);
                updateResult = dao.update(Long.parseLong(rowID), shop);
                break;
            case SINGLE_ACTIVITY:
                // Open a read / write database to support the transaction.

                MadridActivityDAO activityDAO = new MadridActivityDAO(getContext());

                // Insert the values into the table
                MadridActivity activityMadrid = activityDAO.getMadridActivityFromContentValues(contentValues);
                rowID = uri.getPathSegments().get(1);
                updateResult = activityDAO.update(Long.parseLong(rowID), activityMadrid);
                break;
            default:
                break;
        }
        return updateResult;
    }
}
