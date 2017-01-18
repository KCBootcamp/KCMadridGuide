package es.bhavishchandnani.kcmadridguide.viewmodel;

public class ShopViewModel {
    private String name;
    private String logoImgUrl;

    public ShopViewModel(String name, String logoImgUrl) {
        this.name = name;
        this.logoImgUrl = logoImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public void setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
    }
}
