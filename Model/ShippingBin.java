package Model;

import java.util.ArrayList;

public class ShippingBin
{
    protected ArrayList<Kashi> insideKashis;

    protected final int goldNeeded =250;
    protected final int woodNeeded = 150;


    public int getGoldNeeded() {
        return goldNeeded;
    }

    public int getWoodNeeded() {
        return woodNeeded;
    }
    public void adaptMap(ArrayList<Cord> cords)
    {
        this.insideKashis = new ArrayList<>();
        ArrayList<Kashi> kashis = new ArrayList<>();
        for (Cord cord : cords)
        {
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
}
