package Model.Items;

import Model.*;

public class MilkPail extends Tool implements Name, Price {
    protected int EnergyUsage = 4;
    protected String usage;
    protected int price;

    public String  use(String direction) {
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
        tileCord.setX(dir_x + tileCord.getX());
        tileCord.setY(dir_y + tileCord.getY());
        if (player.getEnergy() >= getEnergyUsage()) {
            if (isValidForMilking(tileCord)) {
                player.setEnergy(player.getEnergy() - getEnergyUsage());
                //todo get crop
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel() + 5);
                return "Milk achived";
            } else {
                player.setEnergy(player.getEnergy() - getEnergyUsage());
                //todo just decrease energy
                return "Not animal found. Energy: " + getEnergyUsage();
            }
        } else {
            //todo return error for not enough energy
            return "Not Enough energy";
        }
    }

    public static boolean isValidForMilking(Cord cord) {
        Kashi kashi = App.getCurrentGame().getMap().get(cord.getX()).get(cord.getY());
        if (kashi.getInside() instanceof TavilehAnimal) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getCorrectName() {
        return "milkpail";
    }


    @Override
    public int getCorrectPrice() {
        return price;
    }
}