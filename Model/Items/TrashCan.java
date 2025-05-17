package Model.Items;

import Model.App;
import Model.Inventory;
import Model.Name;
import Model.Player;

import java.util.HashMap;
import java.util.Map;

public class TrashCan extends Tool implements Name,Price
{
    protected String Jens;
    protected int EnergyUsage;
    protected String usage;

    public void trash(String objectName, int number){
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Map<Item,Integer> inventoryMap = player.getInventory().getItems();
        for (Item item : inventoryMap.keySet()){
            if (item.getClass().getSimpleName().equals(objectName)){
                if (inventoryMap.get(item) >= number){
                    int newNumber = inventoryMap.get(item) - number;
                    inventoryMap.put(item, newNumber);
                    break;
                } else{
                    //todo return number > itemCount
                    return;
                }
            } else {
                //todo return not found
                return;
            }
        }
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
    public String getJens() {
        return Jens;
    }

    public void setJens(String jens) {
        this.Jens = jens;
    }

    public int getEnergyUsage() {
        return EnergyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this.EnergyUsage = energyUsage;
    }
    @Override
    public String getCorrectName() {
        return "trashcan";
    }


    @Override
    public int getCorrectPrice() {
        return 0;
    }
}