package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class BigCoop
{
    protected static int bigCoop = 100;

    protected boolean Status = false;
    protected final int MaxCapacity= 8;
    protected String Level;
    protected ArrayList<CageAnimal> CageAnimals;
    protected ArrayList<Kashi> insideKashis;


    public void setStatus(boolean status) {
        Status = status;
    }
    public boolean getStatus() {
        return Status;
    }

    public int getMaxCapacity() {
        return MaxCapacity;
    }
    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        this.Level = level;
    }

    public ArrayList<CageAnimal> getCageAnimals() {
        return CageAnimals;
    }

    public void setCageAnimals(ArrayList<CageAnimal> cageAnimals) {
        this.CageAnimals = cageAnimals;
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
}
