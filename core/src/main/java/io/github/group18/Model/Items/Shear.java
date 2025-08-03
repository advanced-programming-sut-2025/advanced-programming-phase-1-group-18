package io.github.group18.Model.Items;

import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;

public class Shear extends Tool implements Name,Price
{
    public int EnergyUsage = 4;
    public String usage;
    public int price;
    public String use(Kashi kashi){
        //Server-TODO
//        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
//
//        if (player.getEnergy() >= getEnergyUsage()) {
//            if (isValidForShear(kashi)) {
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+5);
//                GameMenuController.checkSkilRecipe();
//                return "Wool caught";
//            }
//            else{
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                return "Not Animal found";
//            }
//        }
//        else {
//            return "Not enough energy";
//        }
        return "Not enough energy";
    }

    public static boolean isValidForShear(Kashi kashi){
        if(kashi.getInside() instanceof TavilehAnimal) {
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
        return "shear";
    }


    @Override
    public int getCorrectPrice() {
        return 0;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
