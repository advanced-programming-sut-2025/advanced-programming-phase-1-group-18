package Model;
import java.util.ArrayList;
import java.util.HashMap;

public class Tavileh
{
    protected String Level;

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

    protected ArrayList<TavilehAnimal> TavilehAnimals;
    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
