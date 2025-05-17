package Model.Items;

import Model.*;

public class Scythe extends Tool implements Name,Price
{
    protected int EnergyUsage;
    protected String usage;
    public String  use(String direction){
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Cord tileCord = new Cord(player.getX(), player.getY());
        int dir_x = -1;
        int dir_y = -1;
        switch (direction.toLowerCase()) {
            case "n": {
                dir_x = 0;
                dir_y = -1;
                break;
            }
            case "ne": {
                dir_x = 1;
                dir_y = -1;
                break;
            }
            case "e": {
                dir_x = 1;
                dir_y = 0;
                break;
            }
            case "se": {
                dir_x = 1;
                dir_y = 1;
                break;
            }
            case "s": {
                dir_x = 0;
                dir_y = 1;
                break;
            }
            case "sw": {
                dir_x = -1;
                dir_y = 1;
                break;
            }
            case "w": {
                dir_x = -1;
                dir_y = 0;
                break;
            }
            case "nw": {
                dir_x = -1;
                dir_y = -1;
                break;
            }
            default: {
                return "Not a valid direction";
            }
        }
        tileCord.setX(dir_x+tileCord.getX());
        tileCord.setY(dir_y+tileCord.getY());
        if (player.getEnergy() >= getEnergyUsage()) {
            if (isValidForScythe(tileCord)) {
                player.setEnergy(player.getEnergy() - getEnergyUsage());
                Kashi kashi = App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY());
                Object plant =  kashi.getInside();
                if(plant instanceof AllTree){
                    if(((AllTree)plant).getTotalHarvestTime() == ((AllTree)plant).getDaysGrowCounter()){
//                            player.getInventory().addItem(((Tre)),1);
                        player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+5);
                        return "Fruit caught";
                    } else {
                        return "Fruit isn't ready";
                    }
                } else if(plant instanceof AllCrop){
                    if(((AllCrop)plant).getTotalHarvestTime() == ((AllCrop)plant).getDaysGrowCounter()){
                        player.getInventory().addItem(((AllCrop)plant), 1);
                        player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+5);
                        return "Crop caught";
                    }else{
                        return "Crop isn't ready";
                    }
                }else {
                    return "Not crop found";
                }
            } else{
                player.setEnergy(player.getEnergy() - getEnergyUsage());
                return "Not Crop found";
            }
        } else {
            return "not Enough energy";
        }
    }

    public static boolean isValidForScythe(Cord cord){
        Kashi kashi = App.getCurrentGame().getMap().get(cord.getX()).get(cord.getY());
        if(kashi.getInside() instanceof AllTree || kashi.getInside() instanceof AllCrop) {
            return true;
        }
        return false;
    }
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
    public int getEnergyUsage() {
        switch (App.getCurrentGame().getCurrentWeather()) {
            case SUNNY -> {
                return EnergyUsage;
            }
            case STORM -> {
                return (int) (EnergyUsage*1.5);
            }
            case RAIN -> {
                return (int) (EnergyUsage *1.5);
            }
            case SNOW -> {
                return (int) (EnergyUsage *2);
            }
        }
        return EnergyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this.EnergyUsage = energyUsage;
    }
    @Override
    public String getCorrectName() {
        return "scythe";
    }


    @Override
    public int getCorrectPrice() {
        return 0;
    }
}