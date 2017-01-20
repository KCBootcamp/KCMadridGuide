package es.bhavishchandnani.kcmadridguide.manager.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import es.bhavishchandnani.kcmadridguide.model.Shop;
import es.bhavishchandnani.kcmadridguide.model.Shops;

import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.*;

public class ShopDAO implements DAOPersistable<Shop> {

    private WeakReference<Context> context;
    private DBHelper dbHelper ;
    private SQLiteDatabase db;

    public ShopDAO(Context context, DBHelper dbHelper) {
        this.context = new WeakReference<>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();
    }

    public ShopDAO(Context context) {
        this(context, DBHelper.getInstance(context));
    }

    @Override
    public long insert(@NonNull Shop shop) {
        if (shop == null) {
            return 0;
        }

        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = db.insert(TABLE_SHOP, KEY_NAME, this.getContentValues(shop));
            shop.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        //db.close();

        return id;
    }

    public static @NonNull
    ContentValues getContentValues(final @NonNull Shop shop){
        ContentValues contentValues = new ContentValues();

        if (shop == null){
            return contentValues;
        }

        contentValues.put(KEY_ADDRESS, shop.getAddress());
        contentValues.put(KEY_IMAGE_URL, shop.getImageUrl());
        contentValues.put(KEY_LOGO_URL, shop.getLogoImgUrl());
        contentValues.put(KEY_LATITUDE, shop.getLatitude());
        contentValues.put(KEY_LONGITUDE, shop.getLongitude());
        contentValues.put(KEY_NAME, shop.getName());
        contentValues.put(KEY_URL, shop.getUrl());
        contentValues.put(KEY_TELEPHONE, shop.getTelephone());
        contentValues.put(KEY_EMAIL, shop.getEmail());
        contentValues.put(KEY_DESCRIPTION_ES, shop.getDescription_es());
        contentValues.put(KEY_DESCRIPTION_EN, shop.getDescription_en());
        contentValues.put(KEY_OPENINGHOURS_EN, shop.getOpeningHours_en());
        contentValues.put(KEY_OPENINGHOURS_ES, shop.getOpeningHours_es());

        return contentValues;
    }

    public static @NonNull Shop getShopFromContentValues(final @NonNull ContentValues contentValues){
        final Shop shop = new Shop(1, "");
        //shop.setId(contentValues.getAsInteger(KEY_ID));
        shop.setName(contentValues.getAsString(KEY_NAME));
        shop.setAddress(contentValues.getAsString(KEY_ADDRESS));
        shop.setImageUrl(contentValues.getAsString(KEY_IMAGE_URL));
        shop.setLogoImgUrl(contentValues.getAsString(KEY_LOGO_URL));
        shop.setUrl(contentValues.getAsString(KEY_URL));
        shop.setLatitude(contentValues.getAsFloat(KEY_LATITUDE));
        shop.setLongitude(contentValues.getAsFloat(KEY_LONGITUDE));
        shop.setTelephone(contentValues.getAsString(KEY_TELEPHONE));
        shop.setEmail(contentValues.getAsString(KEY_EMAIL));
        shop.setDescription_en(contentValues.getAsString(KEY_DESCRIPTION_ES));
        shop.setDescription_es(contentValues.getAsString(KEY_DESCRIPTION_EN));
        shop.setOpeningHours_es(contentValues.getAsString(KEY_OPENINGHOURS_ES));
        shop.setOpeningHours_en(contentValues.getAsString(KEY_OPENINGHOURS_EN));
        return shop;
    }

    @Override
    public int update(long id, @NonNull Shop shop) {
        if (shop == null) {
            return 0;
        }
        return db.update(TABLE_SHOP, getContentValues(shop), KEY_ID + " = ?", new String[]{"" + id});
    }

    @Override
    public int delete(long id) {
        return db.delete(TABLE_SHOP, KEY_ID + " = ?", new String[]{"" + id});
    }

    @Override
    public int deleteAll() {
        int rowsDeleted = 0;
        db.beginTransaction();
        try {
            rowsDeleted = db.delete(TABLE_SHOP, null, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return rowsDeleted;
    }

    @Nullable
    @Override
    public Cursor queryCursor() {
        Cursor c = db.query(TABLE_SHOP, ALL_COLUMNS, null, null, null, null, KEY_ID);
        if (c!=null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor queryCursor(long id) {
        Cursor c = db.query(TABLE_SHOP, ALL_COLUMNS, KEY_ID + "= " + id, null, null, null, KEY_ID);
        if (c!=null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    @Override
    public @Nullable Shop query(final long id) {

        Cursor c = db.query(TABLE_SHOP, ALL_COLUMNS, KEY_ID +"= " + id, null, null, null, KEY_ID);
        if (c!=null && c.getCount() == 1) {
            c.moveToFirst();
        } else {
            return null;
        }

        Shop shop = getShopFromCursor(c);

        return shop;

    }

    @NonNull
    public static Shop getShopFromCursor(final Cursor c) {
        long identifier = c.getLong(c.getColumnIndex(KEY_ID));
        String name = c.getString(c.getColumnIndex(KEY_NAME));
        Shop shop = new Shop(identifier, name);

        shop.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
        shop.setImageUrl(c.getString(c.getColumnIndex(KEY_IMAGE_URL)));
        shop.setLogoImgUrl(c.getString(c.getColumnIndex(KEY_LOGO_URL)));
        shop.setLatitude(c.getFloat(c.getColumnIndex(KEY_LATITUDE)));
        shop.setLongitude(c.getFloat(c.getColumnIndex(KEY_LONGITUDE)));
        shop.setUrl(c.getString(c.getColumnIndex(KEY_URL)));
        shop.setUrl(c.getString(c.getColumnIndex(KEY_TELEPHONE)));
        shop.setUrl(c.getString(c.getColumnIndex(KEY_EMAIL)));
        shop.setDescription_es(c.getString(c.getColumnIndex(KEY_DESCRIPTION_ES)));
        shop.setDescription_en(c.getString(c.getColumnIndex(KEY_DESCRIPTION_EN)));
        shop.setOpeningHours_es(c.getString(c.getColumnIndex(KEY_OPENINGHOURS_ES)));
        shop.setOpeningHours_en(c.getString(c.getColumnIndex(KEY_OPENINGHOURS_EN)));
        return shop;
    }

    @NonNull
    public static Shops getShopsFromCursor(final Cursor data) {
        List<Shop> shopList = new LinkedList<>();

        while (data.moveToNext()){
            Shop shop = getShopFromCursor(data);
            shopList.add(shop);
        }
        return Shops.build(shopList);
    }

    @Nullable
    @Override
    public List<Shop> query() {
        Cursor c = queryCursor();
        if (c ==null || !c.moveToFirst()) {
            return null;
        }

        List<Shop> shops = new LinkedList<>();
        c.moveToFirst();
        do {
            shops.add(getShopFromCursor(c));
        }
        while (c.moveToNext());

        return shops;
    }
}
