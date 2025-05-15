package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Cage implements Name
{

    // make it true when Cage
    protected boolean Status = false;
    protected final int MaxCapacity =4;
    protected String Level;
    protected ArrayList<CageAnimal> CageAnimals;
    protected ArrayList<Kashi> insideKashis;
    protected  int goldNeeded = 4000;
    protected  int woodNeeded =300;
    protected  int stoneNeeded = 150;
    protected String Name;

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
        return "cage";
    }
}
