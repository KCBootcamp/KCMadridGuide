package es.bhavishchandnani.kcmadridguide.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MadridActivities implements IIteratable<MadridActivity>, IUpdatable<MadridActivity> {
    List<MadridActivity> madridActivities;

    public static @NonNull
    MadridActivities build(@NonNull final List<MadridActivity> madridActivityList){
        MadridActivities madridActivities = new MadridActivities(madridActivityList);
        if (madridActivityList == null){
            madridActivities.madridActivities = new ArrayList<>();
        }
        return madridActivities;
    }

    public static @NonNull MadridActivities build(){
        return build(new ArrayList<MadridActivity>());
    }

    // A posible solution but constructor does not respect heritage
    private MadridActivities(List<MadridActivity> madridActivities) {
        this.madridActivities = madridActivities;
    }

    private MadridActivities() {
    }

    @Override
    public long size() {
        return madridActivities.size();
    }

    @Override
    public MadridActivity get(long index) {
        return madridActivities.get((int) index);
    }

    @Override
    public List<MadridActivity> allItems() {
        return madridActivities;
    }

    @Override
    public void add(MadridActivity shop) {
        madridActivities.add(shop);
    }

    @Override
    public void delete(MadridActivity shop) {
        madridActivities.remove(shop);
    }

    @Override
    public void edit(MadridActivity newMadridActivity, long index) {
        madridActivities.set((int) index, newMadridActivity);
    }
}