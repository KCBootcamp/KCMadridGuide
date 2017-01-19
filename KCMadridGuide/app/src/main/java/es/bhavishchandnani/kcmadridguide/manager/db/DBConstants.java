package es.bhavishchandnani.kcmadridguide.manager.db;

public class DBConstants {
    public static final String TABLE_SHOP = "SHOP";

    // Table field constants
    public static final String KEY_SHOP_ID = "_id";


    public static final String KEY_SHOP_NAME = "NAME";
    public static final String KEY_SHOP_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_SHOP_LOGO_URL = "LOGO_URL";
    public static final String KEY_SHOP_ADDRESS = "ADDRESS";
    public static final String KEY_SHOP_URL = "URL";
    public static final String KEY_SHOP_TELEPHONE = "TELEPHONE";
    public static final String KEY_SHOP_EMAIL = "EMAIL";
    public static final String KEY_SHOP_DESCRIPTION_ES = "DESCRIPTION_ES";
    public static final String KEY_SHOP_DESCRIPTION_EN = "DESCRIPTION_EN";
    public static final String KEY_SHOP_OPENINGHOURS_EN = "OPENINGHOURS_EN";
    public static final String KEY_SHOP_OPENINGHOURS_ES = "OPENINGHOURS_ES";

    // Table field constants
    public static final String KEY_SHOP_LATITUDE = "latitude";
    public static final String KEY_SHOP_LONGITUDE = "longitude";


    public static final String SQL_SCRIPT_CREATE_SHOP_TABLE =
            "create table "
                    + TABLE_SHOP + "( " + KEY_SHOP_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_NAME + " text not null,"
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text, "
                    + KEY_SHOP_URL + " text,"
                    + KEY_SHOP_LATITUDE + " real,"
                    + KEY_SHOP_LONGITUDE + " real, "
                    + KEY_SHOP_TELEPHONE + " text, "
                    + KEY_SHOP_EMAIL + " text, "
                    + KEY_SHOP_DESCRIPTION_ES + " text, "
                    + KEY_SHOP_DESCRIPTION_EN + " text, "
                    + KEY_SHOP_OPENINGHOURS_ES + " text, "
                    + KEY_SHOP_OPENINGHOURS_EN + " text "
                    + ");";

    public static final String[] CREATE_DATABASE_SCRIPTS = {
            SQL_SCRIPT_CREATE_SHOP_TABLE
    };

    public static final String DROP_DATABASE_SCRIPTS = "";

    public static final String[] ALL_COLUMNS = {
            KEY_SHOP_ID,
            KEY_SHOP_NAME,
            KEY_SHOP_IMAGE_URL,
            KEY_SHOP_LOGO_URL,
            KEY_SHOP_ADDRESS,
            KEY_SHOP_URL,
            KEY_SHOP_LATITUDE,
            KEY_SHOP_LONGITUDE,
            KEY_SHOP_TELEPHONE,
            KEY_SHOP_EMAIL,
            KEY_SHOP_DESCRIPTION_ES,
            KEY_SHOP_DESCRIPTION_EN,
            KEY_SHOP_OPENINGHOURS_ES,
            KEY_SHOP_OPENINGHOURS_EN,
    };

}
