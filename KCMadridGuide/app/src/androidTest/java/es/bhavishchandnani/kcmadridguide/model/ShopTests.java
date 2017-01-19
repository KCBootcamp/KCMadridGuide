package es.bhavishchandnani.kcmadridguide.model;

import android.test.AndroidTestCase;


public class ShopTests extends AndroidTestCase {

    public static final String TEST_SHOP_NAME = "TestShop";
    public static final int TEST_SHOP_ID = 10;
    private static final String TEST_SHOP_ADDRESS = "TEST_SHOP_ADDRESS";
    private static final String TEST_SHOP_DESCRIPTION_ES = "TEST_SHOP_DESCRIPTION_ES";
    private static final String TEST_SHOP_DESCRIPTION_EN = "TEST_SHOP_DESCRIPTION_EN";
    private static final String TEST_SHOP_OPENINGHOURS_ES = "TEST_SHOP_OPENINGHOURS_ES";
    private static final String TEST_SHOP_OPENINGHOURS_EN = "TEST_SHOP_OPENINGHOURS_EN";
    private static final String TEST_SHOP_TELEPHONE = "TEST_SHOP_TELEPHONE";
    private static final String TEST_SHOP_EMAIL = "TEST_SHOP_EMAIL";
    private static final String TEST_SHOP_IMAGEURL = "TEST_SHOP_IMAGEURL";
    private static final String TEST_SHOP_LOGOURL = "TEST_SHOP_LOGOURL";
    private static final String TEST_SHOP_URL = "TEST_SHOP_URL";
    private static final float TEST_SHOP_LATITUDE = 27.03f;
    private static final float TEST_SHOP_LONGITUDE = -3.2f;

    public void testCanCreateAShop(){
        Shop sut = new Shop(TEST_SHOP_ID, TEST_SHOP_NAME);
        assertNotNull(sut);
    }

    public void testANewShopStoresDataCorrectly(){
        Shop sut = new Shop(TEST_SHOP_ID, TEST_SHOP_NAME);
        assertEquals(sut.getId(), TEST_SHOP_ID);
        assertEquals(sut.getName(), TEST_SHOP_NAME);
    }

    public void testANewShopStoresDataInPropertiesCorrectly() {
        Shop sut = new Shop(TEST_SHOP_ID, TEST_SHOP_NAME)
                .setAddress(TEST_SHOP_ADDRESS)
                .setDescription_es(TEST_SHOP_DESCRIPTION_ES)
                .setDescription_en(TEST_SHOP_DESCRIPTION_EN)
                .setImageUrl(TEST_SHOP_IMAGEURL)
                .setLogoImgUrl(TEST_SHOP_LOGOURL)
                .setUrl(TEST_SHOP_URL)
                .setOpeningHours_es(TEST_SHOP_OPENINGHOURS_ES)
                .setOpeningHours_en(TEST_SHOP_OPENINGHOURS_EN)
                .setTelephone(TEST_SHOP_TELEPHONE)
                .setEmail(TEST_SHOP_EMAIL)
                .setLatitude(TEST_SHOP_LATITUDE)
                .setLongitude(TEST_SHOP_LONGITUDE);
        assertEquals(sut.getAddress(), TEST_SHOP_ADDRESS);
        assertEquals(sut.getDescription_es(), TEST_SHOP_DESCRIPTION_ES);
        assertEquals(sut.getDescription_en(), TEST_SHOP_DESCRIPTION_EN);
        assertEquals(sut.getOpeningHours_en(), TEST_SHOP_OPENINGHOURS_EN);
        assertEquals(sut.getOpeningHours_es(), TEST_SHOP_OPENINGHOURS_ES);
        assertEquals(sut.getTelephone(), TEST_SHOP_TELEPHONE);
        assertEquals(sut.getEmail(), TEST_SHOP_EMAIL);
        assertEquals(sut.getUrl(), TEST_SHOP_URL);
        assertEquals(sut.getLogoImgUrl(), TEST_SHOP_LOGOURL);
        assertEquals(sut.getLatitude(), TEST_SHOP_LATITUDE);
        assertEquals(sut.getLongitude(), TEST_SHOP_LONGITUDE);
    }
}
