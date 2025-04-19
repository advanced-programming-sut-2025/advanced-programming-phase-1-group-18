package Model.Items;
import java.util.ArrayList;
import java.util.List;

public class FoodCooking extends Item
{

    String Name;
    ArrayList<Item> Ingredients;
    int Energy;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<Item> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(ArrayList<Item> ingredients) {
        this.Ingredients = ingredients;
    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        this.Energy = energy;
    }

}
