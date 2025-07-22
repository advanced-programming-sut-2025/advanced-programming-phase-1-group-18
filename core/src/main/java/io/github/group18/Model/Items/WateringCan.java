package io.github.group18.Model.Items;

import io.github.group18.Model.*;

public class WateringCan extends Tool implements Name, Price ,PictureModel{
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

    public String use(Kashi kashi,int x,int y) {
        App.getCurrentGame().setWatering(true);
        App.getCurrentGame().setPlantx(x);
        App.getCurrentGame().setPlanty(y);
        App.getCurrentGame().setWaterTimer(1f);
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        if (player.getFarmingSkill().getLevel() == 5) {
            if (player.getEnergy() >= getEnergyUsage() - 1) {
                if (isValidForFilling(kashi)) {
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    setCapacity(getMax_Capacity());
                    return "Watering Can filled";
                } else if (isValidForWatering(kashi)) {
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    setCapacity(getCapacity() - 1);
                    Object plant = kashi.getInside();
                    if (plant instanceof AllCrop) {
                        if (!((AllCrop) plant).isFedThisDay()) {
                            ((AllCrop) plant).setFedThisDay(true);
                            return "Plant watered";
                        } else {
                            return "Plant doesn't need water";
                        }
                    } else if (plant instanceof AllTree) {
                        if (!((AllTree) plant).isFedThisDay()) {
                            ((AllTree) plant).setFedThisDay(true);
                            return "Plant watered";
                        } else {
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
                if (isValidForFilling(kashi)) {
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    setCapacity(getMax_Capacity());
                    return "Watering Can filled";
                } else if (isValidForWatering(kashi)) {
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    setCapacity(getCapacity() - 1);
                    Object plant = kashi.getInside();
                    if (plant instanceof AllCrop) {
                        if (!((AllCrop) plant).isFedThisDay()) {
                            ((AllCrop) plant).setFedThisDay(true);
                            return "Plant watered";
                        } else {
                            return "Plant doesn't need water";
                        }
                    } else if (plant instanceof AllTree) {
                        if (!((AllTree) plant).isFedThisDay()) {
                            ((AllTree) plant).setFedThisDay(true);
                            return "Plant watered";
                        } else {
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


    public static boolean isValidForFilling(Kashi kashi) {
        if (kashi.getInside() instanceof Lake || kashi.getInside() instanceof GreenHouse) {
            return true;
        }
        return false;
    }

    public static boolean isValidForWatering(Kashi kashi) {
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
                return (int) (EnergyUsage * 1.5);
            }
            case RAIN -> {
                return (int) (EnergyUsage * 1.5);
            }
            case SNOW -> {
                return (int) (EnergyUsage * 2);
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


    public void update() {
        switch (Jens) {
            case "copper":
                this.setJens("copper");
                this.setCapacity(55);
                this.setMax_Capacity(55);
                this.setEnergyUsage(4);
                break;
            case "iron":
                this.setJens("iron");
                this.setCapacity(70);
                this.setMax_Capacity(70);
                this.setEnergyUsage(3);
                break;
            case "gold":
                this.setJens("gold");
                this.setCapacity(85);
                this.setMax_Capacity(85);
                this.setEnergyUsage(2);
                break;
            case "iridium":
                this.setJens("iridium");
                this.setCapacity(100);
                this.setMax_Capacity(100);
                this.setEnergyUsage(1);
                break;
            default:
                break;
        }
    }

    @Override
    public String getPath() {
        return "Watering_Can/Watering_Can.png";
    }
}




