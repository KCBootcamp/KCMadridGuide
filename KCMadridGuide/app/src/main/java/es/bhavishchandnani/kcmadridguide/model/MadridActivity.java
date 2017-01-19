package es.bhavishchandnani.kcmadridguide.model;

import java.io.Serializable;

public class MadridActivity implements Serializable {
    private long id;
    private String name;
    private String imageUrl;
    private String logoImgUrl;
    private String address;
    private String url;
    private String description_es;
    private String description_en;
    private String telephone;
    private String email;
    private String openingHours_es;
    private String openingHours_en;

    private float latitude;
    private float longitude;

    public MadridActivity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private MadridActivity() {
    }

    public long getId() {
        return id;
    }

    public MadridActivity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MadridActivity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MadridActivity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public MadridActivity setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public MadridActivity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MadridActivity setUrl(String url) {
        this.url = url;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public MadridActivity setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public MadridActivity setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }


    public String getTelephone() {
        return telephone;
    }

    public MadridActivity setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MadridActivity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDescription_es() {
        return description_es;
    }

    public MadridActivity setDescription_es(String description_es) {
        this.description_es = description_es;
        return this;
    }

    public String getDescription_en() {
        return description_en;
    }

    public MadridActivity setDescription_en(String description_en) {
        this.description_en = description_en;
        return this;
    }

    public String getOpeningHours_es() {
        return openingHours_es;
    }

    public MadridActivity setOpeningHours_es(String openingHours_es) {
        this.openingHours_es = openingHours_es;
        return this;
    }

    public String getOpeningHours_en() {
        return openingHours_en;
    }

    public MadridActivity setOpeningHours_en(String openingHours_en) {
        this.openingHours_en = openingHours_en;
        return this;
    }
}
