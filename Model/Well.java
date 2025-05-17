package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Well implements Name
{
    protected ArrayList<Kashi> insideKashis;
    protected final int goldNeeded =1000;
    protected final int stoneNeeded = 75;
    public int getStoneNeeded() {
        return stoneNeeded;
    }
    public int getGoldNeeded() {
        return goldNeeded;
    }
    public void adaptMap(ArrayList<Cord> cords)
    {
        this.insideKashis = new ArrayList<>();
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
        this.insideKashis.addAll(kashis);
    }

    @Override
    public String getCorrectName() {
        return "well";
    }
}
