package Model.Items;

import Model.*;

public class Scythe extends Item
{
    protected int EnergyUsage;
    protected String usage;
    public void use(String direction){
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
                return;
            }
        }
        tileCord.setX(dir_x+tileCord.getX());
        tileCord.setY(dir_y+tileCord.getY());
        if (player.getEnergy() >= getEnergyUsage()) {
            if (isValidForScythe(tileCord)) {
                player.setEnergy(player.getEnergy() - getEnergyUsage());
                //todo get crop
            }
            else{
                player.setEnergy(player.getEnergy() - getEnergyUsage());
                //todo just decrease energy
                return;
            }
        }
        else {
            return;
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
        return EnergyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this.EnergyUsage = energyUsage;
    }
}
