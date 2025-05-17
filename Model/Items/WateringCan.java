package Model.Items;

import Model.*;

public class WateringCan extends Tool implements Name, Price {
    protected String Jens;
    protected int EnergyUsage;
    protected String usage;
    protected int capacity;
    protected int max_Capacity;


    public WateringCan(String jens, int energyUsage, int capacity, int max_Capacity) {
        Jens = jens;
        EnergyUsage = energyUsage;
        this.capacity = capacity;
        this.max_Capacity = max_Capacity;
    }

    public String use(String direction) {
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
                return "Not a valid direction!";
            }
        }
        tileCord.setX(dir_x + tileCord.getX());
        tileCord.setY(dir_y + tileCord.getY());
        if (player.getFarmingSkill().getLevel() == 5) {
            if (player.getEnergy() >= getEnergyUsage() - 1) {
                if (isValidForFilling(tileCord)) {
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    setCapacity(getMax_Capacity());
                    return "Watering Can filled";
                } else if (isValidForWatering(tileCord)) {
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    setCapacity(getCapacity() - 1);
                    Object plant = App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY()).getInside();
                    if(plant instanceof AllCrop){
                        if(!((AllCrop)plant).isFedThisDay()){
                            ((AllCrop)plant).setFedThisDay(true);
                            return "Plant watered";
                        }else {
                            return "Plant doesn't need water";
                        }
                    } else if(plant instanceof AllTree){
                        if(!((AllTree)plant).isFedThisDay()){
                            ((AllTree)plant).setFedThisDay(true);
                            return "Plant watered";
                        }else {
                            return "Plant doesn't need water";
                        }
                    }
                } else {
                    return "No plant found";
                }
            } else {
                return "Not enough energy";
            }
        } else {
            if (player.getEnergy() >= getEnergyUsage()) {
                if (isValidForFilling(tileCord)) {
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    setCapacity(getMax_Capacity());
                    return "Watering Can filled";
                } else if (isValidForWatering(tileCord)) {
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    setCapacity(getCapacity() - 1);
                    Object plant = App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY()).getInside();
                    if(plant instanceof AllCrop){
                        if(!((AllCrop)plant).isFedThisDay()){
                            ((AllCrop)plant).setFedThisDay(true);
                            return "Plant watered";
                        }else {
                            return "Plant doesn't need water";
                        }
                    } else if(plant instanceof AllTree){
                        if(!((AllTree)plant).isFedThisDay()){
                            ((AllTree)plant).setFedThisDay(true);
                            return "Plant watered";
                        }else {
                            return "Plant doesn't need water";
                        }
                    }
                } else {
                    return "No plant found";
                }
            } else {
                return "Not enough energy";
            }
        }
        return "OMG";
    }

    public void update() {
        switch (this.getJens()) {
            case "initial":
                this.setJens("copper");
                this.setCapacity(55);
                this.setMax_Capacity(55);
                this.setEnergyUsage(4);
                break;
            case "copper":
                this.setJens("iron");
                this.setCapacity(70);
                this.setMax_Capacity(70);
                this.setEnergyUsage(3);
                break;
            case "iron":
                this.setJens("gold");
                this.setCapacity(85);
                this.setMax_Capacity(85);
                this.setEnergyUsage(2);
                break;
            case "gold":
                this.setJens("iridium");
                this.setCapacity(100);
                this.setMax_Capacity(100);
                this.setEnergyUsage(1);
                break;
            case "iridium":
                break;
            default:
                break;
        }
    }

    public static boolean isValidForFilling(Cord cord) {
        Kashi kashi = App.getCurrentGame().getMap().get(cord.getX()).get(cord.getY());
        if (kashi.getInside() instanceof Lake || kashi.getInside() instanceof GreenHouse) {
            return true;
        }
        return false;
    }

    public static boolean isValidForWatering(Cord cord) {
        Kashi kashi = App.getCurrentGame().getMap().get(cord.getX()).get(cord.getY());
        if (kashi.getInside() instanceof AllTree || kashi.getInside() instanceof AllCrop) {
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

    public String getJens() {
        return Jens;
    }

    public void setJens(String jens) {
        this.Jens = jens;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getMax_Capacity() {
        return max_Capacity;
    }

    public void setMax_Capacity(int max_Capacity) {
        this.max_Capacity = max_Capacity;
    }

    @Override
    public String getCorrectName() {
        return "wateringcan";
    }

    @Override
    public int getCorrectPrice() {
        return 0;
    }
}




