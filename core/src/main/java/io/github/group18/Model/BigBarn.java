package io.github.group18.Model;

import java.util.ArrayList;

public class BigBarn implements Name,PictureModel
{

    protected final int MaxCapacity = 8;
    protected boolean Status = false ;
    protected String Level;
    protected ArrayList<TavilehAnimal> TavilehAnimals = new ArrayList<>();
    protected  int goldNeeded = 12000;
    protected  int woodNeeded =450;
    protected  int stoneNeeded = 200;
    protected  String Name;

    public int getMaxCapacity()
    {
        return MaxCapacity;
    }

    public void setStatus(boolean status)
    {
        Status = status;
    }

    public boolean getStatus() {
        return Status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStoneNeeded() {
        return stoneNeeded;
    }

    public void setStoneNeeded(int stoneNeeded) {
        this.stoneNeeded = stoneNeeded;
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
        return "bigbarn";
    }

    @Override
    public String getPath() {
        return "Buildings/BigBarn.png";
    }
}
