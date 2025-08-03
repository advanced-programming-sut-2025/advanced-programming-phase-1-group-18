package io.github.group18.Model;

import java.util.ArrayList;

public class BigCoop implements Name,PictureModel
{
    public boolean Status = false;
    public final int MaxCapacity= 8;
    public String Level;
    public ArrayList<CageAnimal> CageAnimals = new ArrayList<>();
    public final int goldNeeded = 10000;
    public final int woodNeeded =400;
    public final int stoneNeeded = 150;

    public void setStatus(boolean status) {
        Status = status;
    }
    public boolean getStatus() {
        return Status;
    }

    public int getStoneNeeded() {
        return stoneNeeded;
    }

    public int getWoodNeeded() {
        return woodNeeded;
    }

    public int getGoldNeeded() {
        return goldNeeded;
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

    public void adaptMap(ArrayList<Cord> cords) {
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
        return "bigcoop";
    }

    @Override
    public String getPath() {
        return "Buildings/BigCoop.png";
    }
}
