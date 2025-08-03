package io.github.group18.Model.Items;

import io.github.group18.Model.App;
import io.github.group18.Model.Name;
import io.github.group18.Model.PictureModel;
import io.github.group18.Model.Player;

import java.util.Map;

public class TrashCan extends Tool implements Name,Price, PictureModel
{
    public String Jens;
    public int EnergyUsage;
    public String usage;



    public TrashCan(String jens)
    {
        Jens = jens;
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

//    public void trash(String objectName, int number){
//        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
//        Map<Item,Integer> inventoryMap = player.getInventory().getItems();
//        for (Item item : inventoryMap.keySet()){
//            if (item.getClass().getSimpleName().equals(objectName)){
//                if (inventoryMap.get(item) >= number){
//                    int newNumber = inventoryMap.get(item) - number;
//                    inventoryMap.put(item, newNumber);
//                    break;
//                } else{
//
//                    return;
//                }
//            } else {
//
//                return;
//            }
//        }
//    }

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
        switch (Jens.toLowerCase()) {
            case "initial":
                return 200;
            case "iron":
            case "copper":
                return 250;
            case "gold":
                return 300;
            case "iridium":
                return 400;
            default:
                return 200;
        }
    }

    @Override
    public String getPath() {
        return "Chest.png";
    }
}
