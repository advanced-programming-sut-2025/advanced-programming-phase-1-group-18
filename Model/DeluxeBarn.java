package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class DeluxeBarn implements Name
{

    protected final int MaxCapacity =12;
    protected boolean Status = false;
    protected ArrayList<Kashi> insideKashis;
    protected String Level;
    protected ArrayList<TavilehAnimal> TavilehAnimals;
    protected  int goldNeeded = 25000;
    protected  int woodNeeded =550;
    protected  int stoneNeeded = 300;
    protected String Name;


    public int getMaxCapacity()
    {
        return MaxCapacity;
    }

    public void setStatus(boolean status)
    {
        Status = status;
    }
    public boolean getStatus()
    {
        return Status;
    }

    public int getStoneNeeded() {
        return stoneNeeded;
    }

    public void setStoneNeeded(int stoneNeeded) {
        this.stoneNeeded = stoneNeeded;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getWoodNeeded() {
        return woodNeeded;
    }

    public void setWoodNeeded(int woodNeeded) {
        this.woodNeeded = woodNeeded;
    }

    public int getGoldNeeded() {
        return goldNeeded;
    }

    public void setGoldNeeded(int goldNeeded) {
        this.goldNeeded = goldNeeded;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        this.Level = level;
    }

    public ArrayList<TavilehAnimal> getTavilehAnimals() {
        return TavilehAnimals;
    }

    public void setTavilehAnimals(ArrayList<TavilehAnimal> tavilehAnimals) {
        this.TavilehAnimals = tavilehAnimals;
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
        return "deluxebarn";
    }
}
