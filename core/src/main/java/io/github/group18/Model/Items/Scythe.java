package io.github.group18.Model.Items;

import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;

import java.util.ArrayList;

public class Scythe extends Tool implements Name, Price, PictureModel {
    protected int EnergyUsage;
    protected String usage;

    public String use(ArrayList<Kashi> kashis, Kashi kashi) {
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        if (player.getEnergy() >= getEnergyUsage()) {
            if (isValidForScythe(kashi)) {
                player.setEnergy(player.getEnergy() - getEnergyUsage());
                Object plant = kashi.getInside();
                if (plant instanceof AllTree allTree) {
                    if (allTree.getTotalHarvestTime() == allTree.getDaysGrowCounter()) {
//                        player.getInventory().addItem(allTree, 1);
                        player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel() + 5);
                        allTree.setDaysGrowCounter(0);
                        GameMenuController.checkSkilRecipe();

                        return "Fruit caught";
                    } else {
                        return "Fruit isn't ready";
                    }
                } else if (plant instanceof AllCrop) {
                    if (((AllCrop) plant).getTotalHarvestTime() == ((AllCrop) plant).getDaysGrowCounter()) {
                        int count = 0;
                        for (Kashi kashi1 : kashis) {
                            if (kashi1 != null && kashi1.getInside() != null && kashi1.getInside() instanceof AllCrop allCrop) {
                                count++;
                                if (allCrop.isOneTime()) {
                                    kashi1.setInside(null);
                                } else {
                                    allCrop.setDaysGrowCounter(0);
                                }

                            }
                        }
                        player.getInventory().addItem(((AllCrop) plant), count);
                        player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel() + 5);
                        if (((AllCrop) plant).isOneTime()) {
                            kashi.setInside(null);
                        } else {
                            ((AllCrop) plant).setDaysGrowCounter(0);
                        }
                        GameMenuController.checkSkilRecipe();

                        return "Crop caught";
                    } else {
                        return "Crop isn't ready";
                    }
                } else {
                    if (plant instanceof ForagingSeed) {
                        player.getInventory().addItem(((ForagingSeed) plant), 1);
                        kashi.setInside(null);
                        GameMenuController.checkSkilRecipe();
                        player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel() + 5);

                        return "Picked up ForagingSeed";
                    } else if (plant instanceof ForagingCrop) {
                        player.getInventory().addItem(((ForagingCrop) plant), 1);
                        kashi.setInside(null);
                        GameMenuController.checkSkilRecipe();
                        player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel() + 5);

                        return "Picked up ForagingCrop";
                    } else if (plant instanceof Mineral) {
                        player.getInventory().addItem(((Mineral) plant), 1);
                        kashi.setInside(null);
                        GameMenuController.checkSkilRecipe();
                        player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel() + 5);

                        return "Picked up ForagingMineral";
                    } else {
                        return "Not a valid plant type";
                    }
                }
            } else {
                player.setEnergy(player.getEnergy() - getEnergyUsage());
                return "Not Crop found";
            }
        } else {
            return "not Enough energy";
        }
    }

    public static boolean isValidForScythe(Kashi kashi) {
        if (kashi.getInside() instanceof AllTree || kashi.getInside() instanceof AllCrop || kashi.getInside() instanceof ForagingSeed || kashi.getInside() instanceof ForagingCrop || kashi.getInside() instanceof Mineral) {
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

    @Override
    public String getCorrectName() {
        return "scythe";
    }


    @Override
    public int getCorrectPrice() {
        return 200;
    }

    @Override
    public String getPath() {
        return "Tools/Scythe.png";
    }
}
