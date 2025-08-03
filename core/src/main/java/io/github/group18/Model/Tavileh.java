package io.github.group18.Model;
import java.util.ArrayList;

public class Tavileh implements Name,PictureModel
{
    public final int MaxCapacity = 4;
    public boolean Status = false;
    public String Level;
    public ArrayList<TavilehAnimal> TavilehAnimals = new ArrayList<>();
    public  int goldNeeded = 6000;
    public  int woodNeeded =350;
    public  int stoneNeeded = 150;
    public String Name;


    public int getMaxCapacity() {
        return MaxCapacity;
    }

    public void setStatus(boolean status) {
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

    public void adaptMap(ArrayList<Cord> cords) {
        ArrayList<Kashi> kashis = new ArrayList<>();
        for (Cord cord : cords) {
            //System.out.println("1");
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
        return "barn";
    }

    @Override
    public String getPath() {
        return "Buildings/Barn.png";
    }
}
