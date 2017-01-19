package es.bhavishchandnani.kcmadridguide.manager.db;

public class DBConstants {

    //SHOPS
    public static final String TABLE_SHOP = "SHOP";

    //ACTIVITIES
    public static final String TABLE_ACTIVITY = "ACTIVITY";

    //COMMON
    // Table field constants
    public static final String KEY_ID = "_id";


    public static final String KEY_NAME = "NAME";
    public static final String KEY_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_LOGO_URL = "LOGO_URL";
    public static final String KEY_ADDRESS = "ADDRESS";
    public static final String KEY_URL = "URL";
    public static final String KEY_TELEPHONE = "TELEPHONE";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_DESCRIPTION_ES = "DESCRIPTION_ES";
    public static final String KEY_DESCRIPTION_EN = "DESCRIPTION_EN";
    public static final String KEY_OPENINGHOURS_EN = "OPENINGHOURS_EN";
    public static final String KEY_OPENINGHOURS_ES = "OPENINGHOURS_ES";

    // Table field constants
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";

    public static final String SQL_SCRIPT_CREATE_SHOP_TABLE =
            "create table "
                    + TABLE_SHOP + "( " + KEY_ID + " integer primary key autoincrement, "
                    + KEY_NAME + " text not null,"
                    + KEY_IMAGE_URL + " text, "
                    + KEY_LOGO_URL + " text, "
                    + KEY_ADDRESS + " text, "
                    + KEY_URL + " text,"
                    + KEY_LATITUDE + " real,"
                    + KEY_LONGITUDE + " real, "
                    + KEY_TELEPHONE + " text, "
                    + KEY_EMAIL + " text, "
                    + KEY_DESCRIPTION_ES + " text, "
                    + KEY_DESCRIPTION_EN + " text, "
                    + KEY_OPENINGHOURS_ES + " text, "
                    + KEY_OPENINGHOURS_EN + " text "
                    + ");";

    public static final String SQL_SCRIPT_CREATE_ACTIVITY_TABLE =
            "create table "
                    + TABLE_ACTIVITY + "( " + KEY_ID + " integer primary key autoincrement, "
                    + KEY_NAME + " text not null,"
                    + KEY_IMAGE_URL + " text, "
                    + KEY_LOGO_URL + " text, "
                    + KEY_ADDRESS + " text, "
                    + KEY_URL + " text,"
                    + KEY_LATITUDE + " real,"
                    + KEY_LONGITUDE + " real, "
                    + KEY_TELEPHONE + " text, "
                    + KEY_EMAIL + " text, "
                    + KEY_DESCRIPTION_ES + " text, "
                    + KEY_DESCRIPTION_EN + " text, "
                    + KEY_OPENINGHOURS_ES + " text, "
                    + KEY_OPENINGHOURS_EN + " text "
                    + ");";

    public static final String[] CREATE_DATABASE_SCRIPTS = {
            SQL_SCRIPT_CREATE_SHOP_TABLE,
            SQL_SCRIPT_CREATE_ACTIVITY_TABLE
    };

    public static final String DROP_DATABASE_SCRIPTS = "";

    public static final String[] ALL_COLUMNS = {
            KEY_ID,
            KEY_NAME,
            KEY_IMAGE_URL,
            KEY_LOGO_URL,
            KEY_ADDRESS,
            KEY_URL,
            KEY_LATITUDE,
            KEY_LONGITUDE,
            KEY_TELEPHONE,
            KEY_EMAIL,
            KEY_DESCRIPTION_ES,
            KEY_DESCRIPTION_EN,
            KEY_OPENINGHOURS_ES,
            KEY_OPENINGHOURS_EN,
    };

}
