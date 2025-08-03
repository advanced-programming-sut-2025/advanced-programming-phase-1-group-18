package io.github.group18.Model;

import java.util.ArrayList;

public class Well implements Name
{
    public final int goldNeeded =1000;
    public final int stoneNeeded = 75;
    public int getStoneNeeded() {
        return stoneNeeded;
    }
    public int getGoldNeeded() {
        return goldNeeded;
    }
    public void adaptMap(ArrayList<Cord> cords)
    {
        ArrayList<Kashi> kashis = new ArrayList<>();
        for (Cord cord : cords) {
            Kashi kashi = new Kashi();
            kashi.setShokhmZadeh(false);
            kashi.setEnterance(false);
            kashi.setInside(this);
            kashi.setWalkable(false);
            kashis.add(kashi);
            App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
        }
    }

    @Override
    public String getCorrectName() {
        return "well";
    }
}
