package io.github.group18.Model.Items;

import io.github.group18.Controller.GameMenuController;

import io.github.group18.Model.*;

public class MilkPail extends Tool implements Name, Price, PictureModel {
    protected int EnergyUsage = 4;
    protected String usage;
    protected int price;

    public String use(Kashi kashi) {
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        if (player.getEnergy() >= getEnergyUsage()) {
            if (isValidForMilking(kashi)) {
                player.setEnergy(player.getEnergy() - getEnergyUsage());
                //todo get crop
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel() + 5);
                GameMenuController.checkSkilRecipe();
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

    public static boolean isValidForMilking(Kashi kashi) {
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

    @Override
    public String getPath() {
        return "Tools/Milk_Pail.png";
    }
}
