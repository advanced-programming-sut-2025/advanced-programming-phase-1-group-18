package Model;
import java.util.ArrayList;
import java.util.HashMap;

public class Tavileh
{
    protected String Level;
    protected ArrayList<TavilehAnimal> TavilehAnimals;

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

    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
