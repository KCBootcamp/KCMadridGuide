package es.bhavishchandnani.kcmadridguide.manager.net;


import com.google.gson.annotations.SerializedName;

public class MadridActivityEntity {

    @SerializedName("id") private Long id;
    @SerializedName("name") private String name;
    @SerializedName("img") private String img;
    @SerializedName("logo_img") private String logoImg;
    @SerializedName("address") private String address;
    @SerializedName("url") private String url;
    @SerializedName("description_es") private String descriptionEs;
    @SerializedName("description_en") private String descriptionEn;
    @SerializedName("opening_hours_en") private String openingHoursEn;
    @SerializedName("opening_hours_es") private String openingHoursEs;
    @SerializedName("telephone") private String telephone;
    @SerializedName("email") private String email;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public String getDescriptionEs() {
        return descriptionEs;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public String getOpeningHoursEn() {
        return openingHoursEn;
    }

    public String getOpeningHoursEs() {
        return openingHoursEs;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }
}
