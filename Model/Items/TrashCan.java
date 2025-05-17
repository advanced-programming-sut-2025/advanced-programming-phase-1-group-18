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
    protected String usage;

    public TrashCan(String jens)
    {
        Jens = jens;
    }

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

    public void update(String Jens) {
        switch (Jens) {
            case "copper":
                this.setJens("copper");
                break;
            case "iron":
                this.setJens("iron");
                break;
            case "gold":
                this.setJens("gold");
                break;
            case "iridium":
                this.setJens("iridium");
                break;
            default:
                break;
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

    @Override
    public String getCorrectName() {
        return "trashcan";
    }


    @Override
    public int getCorrectPrice() {
        return 0;
    }
}