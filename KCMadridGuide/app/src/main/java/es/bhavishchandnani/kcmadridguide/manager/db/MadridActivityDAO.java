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

import es.bhavishchandnani.kcmadridguide.model.MadridActivity;
import es.bhavishchandnani.kcmadridguide.model.MadridActivities;

import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.ALL_COLUMNS;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_ADDRESS;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_DESCRIPTION_EN;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_DESCRIPTION_ES;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_EMAIL;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_ID;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_IMAGE_URL;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_LATITUDE;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_LOGO_URL;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_LONGITUDE;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_NAME;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_OPENINGHOURS_EN;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_OPENINGHOURS_ES;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_TELEPHONE;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.KEY_URL;
import static es.bhavishchandnani.kcmadridguide.manager.db.DBConstants.TABLE_ACTIVITY;

public class MadridActivityDAO implements DAOPersistable<MadridActivity> {

    private WeakReference<Context> context;
    private DBHelper dbHelper ;
    private SQLiteDatabase db;

    public MadridActivityDAO(Context context, DBHelper dbHelper) {
        this.context = new WeakReference<>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();
    }

    public MadridActivityDAO(Context context) {
        this(context, DBHelper.getInstance(context));
    }

    @Override
    public long insert(@NonNull MadridActivity madridActivity) {
        if (madridActivity == null) {
            return 0;
        }

        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = db.insert(TABLE_ACTIVITY, KEY_NAME, this.getContentValues(madridActivity));
            madridActivity.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        //db.close();

        return id;
    }

    public static @NonNull
    ContentValues getContentValues(final @NonNull MadridActivity madridActivity){
        ContentValues contentValues = new ContentValues();

        if (madridActivity == null){
            return contentValues;
        }

        contentValues.put(KEY_ADDRESS, madridActivity.getAddress());
        contentValues.put(KEY_IMAGE_URL, madridActivity.getImageUrl());
        contentValues.put(KEY_LOGO_URL, madridActivity.getLogoImgUrl());
        contentValues.put(KEY_LATITUDE, madridActivity.getLatitude());
        contentValues.put(KEY_LONGITUDE, madridActivity.getLongitude());
        contentValues.put(KEY_NAME, madridActivity.getName());
        contentValues.put(KEY_URL, madridActivity.getUrl());
        contentValues.put(KEY_TELEPHONE, madridActivity.getTelephone());
        contentValues.put(KEY_EMAIL, madridActivity.getEmail());
        contentValues.put(KEY_DESCRIPTION_ES, madridActivity.getDescription_es());
        contentValues.put(KEY_DESCRIPTION_EN, madridActivity.getDescription_en());
        contentValues.put(KEY_OPENINGHOURS_EN, madridActivity.getOpeningHours_en());
        contentValues.put(KEY_OPENINGHOURS_ES, madridActivity.getOpeningHours_es());

        return contentValues;
    }

    public static @NonNull MadridActivity getMadridActivityFromContentValues(final @NonNull ContentValues contentValues){
        final MadridActivity madridActivity = new MadridActivity(1, "");
        //madridActivity.setId(contentValues.getAsInteger(KEY_ID));
        madridActivity.setName(contentValues.getAsString(KEY_NAME));
        madridActivity.setAddress(contentValues.getAsString(KEY_ADDRESS));
        madridActivity.setImageUrl(contentValues.getAsString(KEY_IMAGE_URL));
        madridActivity.setLogoImgUrl(contentValues.getAsString(KEY_LOGO_URL));
        madridActivity.setUrl(contentValues.getAsString(KEY_URL));
        madridActivity.setLatitude(contentValues.getAsFloat(KEY_LATITUDE));
        madridActivity.setLongitude(contentValues.getAsFloat(KEY_LONGITUDE));
        madridActivity.setTelephone(contentValues.getAsString(KEY_TELEPHONE));
        madridActivity.setEmail(contentValues.getAsString(KEY_EMAIL));
        madridActivity.setDescription_en(contentValues.getAsString(KEY_DESCRIPTION_ES));
        madridActivity.setDescription_es(contentValues.getAsString(KEY_DESCRIPTION_EN));
        madridActivity.setOpeningHours_es(contentValues.getAsString(KEY_OPENINGHOURS_ES));
        madridActivity.setOpeningHours_en(contentValues.getAsString(KEY_OPENINGHOURS_EN));
        return madridActivity;
    }

    @Override
    public int update(long id, @NonNull MadridActivity madridActivity) {
        if (madridActivity == null) {
            return 0;
        }
        return db.update(TABLE_ACTIVITY, getContentValues(madridActivity), KEY_ID + " = ?", new String[]{"" + id});
    }

    @Override
    public int delete(long id) {
        return db.delete(TABLE_ACTIVITY, KEY_ID + " = ?", new String[]{"" + id});
    }

    @Override
    public int deleteAll() {
        int rowsDeleted = 0;
        db.beginTransaction();
        try {
            rowsDeleted = db.delete(TABLE_ACTIVITY, null, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return rowsDeleted;
    }

    @Nullable
    @Override
    public Cursor queryCursor() {
        Cursor c = db.query(TABLE_ACTIVITY, ALL_COLUMNS, null, null, null, null, KEY_ID);
        if (c!=null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor queryCursor(long id) {
        Cursor c = db.query(TABLE_ACTIVITY, ALL_COLUMNS, KEY_ID + "= " + id, null, null, null, KEY_ID);
        if (c!=null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    @Override
    public @Nullable MadridActivity query(final long id) {

        Cursor c = db.query(TABLE_ACTIVITY, ALL_COLUMNS, KEY_ID +"= " + id, null, null, null, KEY_ID);
        if (c!=null && c.getCount() == 1) {
            c.moveToFirst();
        } else {
            return null;
        }

        MadridActivity madridActivity = getMadridActivityFromCursor(c);

        return madridActivity;

    }

    @NonNull
    public static MadridActivity getMadridActivityFromCursor(final Cursor c) {
        long identifier = c.getLong(c.getColumnIndex(KEY_ID));
        String name = c.getString(c.getColumnIndex(KEY_NAME));
        MadridActivity madridActivity = new MadridActivity(identifier, name);

        madridActivity.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
        madridActivity.setImageUrl(c.getString(c.getColumnIndex(KEY_IMAGE_URL)));
        madridActivity.setLogoImgUrl(c.getString(c.getColumnIndex(KEY_LOGO_URL)));
        madridActivity.setLatitude(c.getFloat(c.getColumnIndex(KEY_LATITUDE)));
        madridActivity.setLongitude(c.getFloat(c.getColumnIndex(KEY_LONGITUDE)));
        madridActivity.setUrl(c.getString(c.getColumnIndex(KEY_URL)));
        madridActivity.setUrl(c.getString(c.getColumnIndex(KEY_TELEPHONE)));
        madridActivity.setUrl(c.getString(c.getColumnIndex(KEY_EMAIL)));
        madridActivity.setDescription_es(c.getString(c.getColumnIndex(KEY_DESCRIPTION_ES)));
        madridActivity.setDescription_en(c.getString(c.getColumnIndex(KEY_DESCRIPTION_EN)));
        madridActivity.setOpeningHours_es(c.getString(c.getColumnIndex(KEY_OPENINGHOURS_ES)));
        madridActivity.setOpeningHours_en(c.getString(c.getColumnIndex(KEY_OPENINGHOURS_EN)));
        return madridActivity;
    }

    @NonNull
    public static MadridActivities getMadridActivitiesFromCursor(final Cursor data) {
        List<MadridActivity> madridActivityList = new LinkedList<>();

        while (data.moveToNext()){
            MadridActivity madridActivity = getMadridActivityFromCursor(data);
            madridActivityList.add(madridActivity);
        }
        return MadridActivities.build(madridActivityList);
    }

    @Nullable
    @Override
    public List<MadridActivity> query() {
        Cursor c = queryCursor();
        if (c ==null || !c.moveToFirst()) {
            return null;
        }

        List<MadridActivity> madridActivities = new LinkedList<>();
        c.moveToFirst();
        do {
            madridActivities.add(getMadridActivityFromCursor(c));
        }
        while (c.moveToNext());

        return madridActivities;
    }
}
