package io.github.group18.Model;

import java.util.ArrayList;

public interface adaptMapMarket {
    public default void adaptMap(ArrayList<Cord> cords, int Enterancex, int Enterancey, int borderlowx, int borderlowy, int borderhighx, int borderhighy) {
        ArrayList<Kashi> kashis = new ArrayList<>();
        for (Cord cord : cords) {
            Kashi kashi = new Kashi();
            kashi.setShokhmZadeh(false);
            if (cord.getX() == Enterancex && cord.getY() == Enterancey) {
                kashi.setEnterance(true);
            } else {
                kashi.setEnterance(false);
            }
            App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
            kashi.setInside(this);
            kashi.setWalkable(true);
            kashis.add(kashi);
        }
    }
}
