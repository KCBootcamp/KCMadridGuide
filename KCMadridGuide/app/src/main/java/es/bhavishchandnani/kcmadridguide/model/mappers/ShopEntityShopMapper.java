package es.bhavishchandnani.kcmadridguide.model.mappers;


import java.util.LinkedList;
import java.util.List;

import es.bhavishchandnani.kcmadridguide.model.Shop;
import es.bhavishchandnani.kcmadridguide.manager.net.ShopEntity;


public class ShopEntityShopMapper {
    public List<Shop> map(List<ShopEntity> shopEntities){
        List<Shop> shopList = new LinkedList<>();
        for (ShopEntity entity: shopEntities) {
            Shop shop = new Shop(entity.getId(), entity.getName())
                    .setAddress(entity.getAddress())
                    .setLogoImgUrl(entity.getLogoImg())
                    .setDescription_es(entity.getDescriptionEs())
                    .setDescription_en(entity.getDescriptionEn())
                    .setOpeningHours_en(entity.getOpeningHoursEn())
                    .setOpeningHours_es(entity.getOpeningHoursEs())
                    .setTelephone(entity.getTelephone())
                    .setEmail(entity.getEmail())
                    .setImageUrl(entity.getImg())
                    .setLogoImgUrl(entity.getLogoImg())
                    .setUrl(entity.getUrl());
            shopList.add(shop);
        }
        return shopList;
    }

}
