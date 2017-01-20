package es.bhavishchandnani.kcmadridguide.model.mappers;


import java.util.LinkedList;
import java.util.List;

import es.bhavishchandnani.kcmadridguide.manager.net.MadridActivityEntity;
import es.bhavishchandnani.kcmadridguide.model.MadridActivity;


public class MadridActivityEntityMadridActivityMapper {
    public List<MadridActivity> map(List<MadridActivityEntity> activityEntities){
        List<MadridActivity> activityList = new LinkedList<>();
        for (MadridActivityEntity entity: activityEntities) {
            MadridActivity activity = new MadridActivity(entity.getId(), entity.getName())
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
            activityList.add(activity);
        }
        return activityList;
    }

}
