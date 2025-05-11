package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class DeluxeCoop
{
    protected static int deluxeCoopAmount = 100;

    public static int getDeluxeCoopAmount() {
        return deluxeCoopAmount;
    }

    public static void setDeluxeCoopAmount(int deluxeCoopAmount) {
        DeluxeCoop.deluxeCoopAmount = deluxeCoopAmount;
    }
    
    protected final int MaxCapacity = 12;
    protected boolean Status = false;
    protected String Level;
    protected ArrayList<CageAnimal> CageAnimals;
    protected ArrayList<Kashi> insideKashis;

    public ArrayList<Kashi> getInsideKashis() {
        return insideKashis;
    }

    public void setInsideKashis(ArrayList<Kashi> insideKashis) {
        this.insideKashis = insideKashis;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public ArrayList<CageAnimal> getCageAnimals() {
        return CageAnimals;
    }

    public void setCageAnimals(ArrayList<CageAnimal> cageAnimals) {
        CageAnimals = cageAnimals;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
    public boolean getStatus() {
        return Status;
    }

    public int getMaxCapacity() {
        return MaxCapacity;
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
