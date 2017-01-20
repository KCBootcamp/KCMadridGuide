package es.bhavishchandnani.kcmadridguide.model;

import android.test.AndroidTestCase;


public class MadridActivityTests extends AndroidTestCase {

    public static final String TEST_ACTIVITY_NAME = "TestActivity";
    public static final int TEST_ACTIVITY_ID = 10;
    private static final String TEST_ACTIVITY_ADDRESS = "TEST_ACTIVITY_ADDRESS";
    private static final String TEST_ACTIVITY_DESCRIPTION_ES = "TEST_ACTIVITY_DESCRIPTION_ES";
    private static final String TEST_ACTIVITY_DESCRIPTION_EN = "TEST_ACTIVITY_DESCRIPTION_EN";
    private static final String TEST_ACTIVITY_OPENINGHOURS_ES = "TEST_ACTIVITY_OPENINGHOURS_ES";
    private static final String TEST_ACTIVITY_OPENINGHOURS_EN = "TEST_ACTIVITY_OPENINGHOURS_EN";
    private static final String TEST_ACTIVITY_TELEPHONE = "TEST_ACTIVITY_TELEPHONE";
    private static final String TEST_ACTIVITY_EMAIL = "TEST_ACTIVITY_EMAIL";
    private static final String TEST_ACTIVITY_IMAGEURL = "TEST_ACTIVITY_IMAGEURL";
    private static final String TEST_ACTIVITY_LOGOURL = "TEST_ACTIVITY_LOGOURL";
    private static final String TEST_ACTIVITY_URL = "TEST_ACTIVITY_URL";
    private static final float TEST_ACTIVITY_LATITUDE = 27.03f;
    private static final float TEST_ACTIVITY_LONGITUDE = -3.2f;

    public void testCanCreateAnActivity(){
        MadridActivity sut = new MadridActivity(TEST_ACTIVITY_ID, TEST_ACTIVITY_NAME);
        assertNotNull(sut);
    }

    public void testANewActivityStoresDataCorrectly(){
        MadridActivity sut = new MadridActivity(TEST_ACTIVITY_ID, TEST_ACTIVITY_NAME);
        assertEquals(sut.getId(), TEST_ACTIVITY_ID);
        assertEquals(sut.getName(), TEST_ACTIVITY_NAME);
    }

    public void testANewActivityStoresDataInPropertiesCorrectly() {
        MadridActivity sut = new MadridActivity(TEST_ACTIVITY_ID, TEST_ACTIVITY_NAME)
                .setAddress(TEST_ACTIVITY_ADDRESS)
                .setDescription_es(TEST_ACTIVITY_DESCRIPTION_ES)
                .setDescription_en(TEST_ACTIVITY_DESCRIPTION_EN)
                .setImageUrl(TEST_ACTIVITY_IMAGEURL)
                .setLogoImgUrl(TEST_ACTIVITY_LOGOURL)
                .setUrl(TEST_ACTIVITY_URL)
                .setOpeningHours_es(TEST_ACTIVITY_OPENINGHOURS_ES)
                .setOpeningHours_en(TEST_ACTIVITY_OPENINGHOURS_EN)
                .setTelephone(TEST_ACTIVITY_TELEPHONE)
                .setEmail(TEST_ACTIVITY_EMAIL)
                .setLatitude(TEST_ACTIVITY_LATITUDE)
                .setLongitude(TEST_ACTIVITY_LONGITUDE);
        assertEquals(sut.getAddress(), TEST_ACTIVITY_ADDRESS);
        assertEquals(sut.getDescription_es(), TEST_ACTIVITY_DESCRIPTION_ES);
        assertEquals(sut.getDescription_en(), TEST_ACTIVITY_DESCRIPTION_EN);
        assertEquals(sut.getOpeningHours_en(), TEST_ACTIVITY_OPENINGHOURS_EN);
        assertEquals(sut.getOpeningHours_es(), TEST_ACTIVITY_OPENINGHOURS_ES);
        assertEquals(sut.getTelephone(), TEST_ACTIVITY_TELEPHONE);
        assertEquals(sut.getEmail(), TEST_ACTIVITY_EMAIL);
        assertEquals(sut.getUrl(), TEST_ACTIVITY_URL);
        assertEquals(sut.getLogoImgUrl(), TEST_ACTIVITY_LOGOURL);
        assertEquals(sut.getLatitude(), TEST_ACTIVITY_LATITUDE);
        assertEquals(sut.getLongitude(), TEST_ACTIVITY_LONGITUDE);
    }
}
