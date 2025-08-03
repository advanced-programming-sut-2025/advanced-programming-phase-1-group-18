package io.github.group18.Model;
import java.util.ArrayList;

public class Cage implements Name,PictureModel
{

    // make it true when Cage
    public boolean Status = false;
    public final int MaxCapacity =4;
    public String Level;
    public ArrayList<CageAnimal> CageAnimals = new ArrayList<>();
    public  int goldNeeded = 4000;
    public  int woodNeeded =300;
    public  int stoneNeeded = 150;
    public String Name;

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

    // max capacity of simple Cage
    public int getMaxCapacity() {
        return MaxCapacity;
    }
    // setter and getter for CageMoneys;
    public int getGoldNeeded() {
        return goldNeeded;
    }

    public int getWoodNeeded() {
        return woodNeeded;
    }

    public void setWoodNeeded(int woodNeeded) {
        this.woodNeeded = woodNeeded;
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

    public void setStatus(boolean status) {
        Status = status;
    }
    public boolean getStatus() {
        return Status;
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
        return "cage";
    }

    @Override
    public String getPath() {
        return "Buildings/Coop.png";
    }
}
