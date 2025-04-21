package Model;
import java.util.ArrayList;
import java.util.HashMap;

public class Cage
{

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

    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
