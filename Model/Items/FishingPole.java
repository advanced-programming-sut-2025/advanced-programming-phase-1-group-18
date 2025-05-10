package Model.Items;

import Model.*;
import enums.CommonFishesEnums;
import enums.LegendaryFishesEnums;
import enums.Quality;

import java.util.ArrayList;
import java.util.Random;

public class FishingPole extends Item implements Name, Price {
    private static final int MAX_LEVEL = 5;
    protected String Jens;
    protected int EnergyUsage;
    protected String usage;

    public String use() {
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Cord tileCord = new Cord(player.getX(), player.getY());
        boolean isFound = false;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                tileCord.setX(x + tileCord.getX());
                tileCord.setY(y + tileCord.getY());
                if (isValidForFishing(tileCord)) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                break;
            }
        }
        if (!isFound) {
            int skill = player.getFishingSkill().getLevel();
            if (skill == MAX_LEVEL) {
                if (player.getEnergy() >= getEnergyUsage() - 1) {
                    player.setEnergy(getEnergyUsage() - this.EnergyUsage);
                    return "No place for fishing found";
                } else {
                    return "Not enough enough energy to fish";
                }
            } else {
                if (player.getEnergy() >= getEnergyUsage()) {
                    player.setEnergy(getEnergyUsage() - this.EnergyUsage);
                    return "No place for fishing found";
                } else {
                    return "Not enough enough energy to fish";
                }
            }
        }

        //weather effect in fishing
        double M;
        switch (App.getCurrentGame().getCurrentWeather()) {
            case SUNNY -> M = 0.5;
            case RAIN -> M = 1.2;
            case STORM -> M = 1.5;
            default -> M = 1;
        }

        //pole jens effect in fish quality
        double pole = 1;
        switch (getJens()) {
            case "Training" -> pole = 0.1;
            case "Bamboo" -> pole = 0.5;
            case "Iridium" -> pole = 1.2;
            case "Fiberglass" -> pole = 0.9;
        }
        ;
        Random rand = new Random();
        double R = rand.nextDouble();
        int skill = player.getFishingSkill().getLevel();
        int fishCount = (int) Math.ceil((2 + skill) * (M * R));
        fishCount = Math.min(fishCount, 6);

        ArrayList<Fish> availableFish = new ArrayList<>();
        for (CommonFishesEnums fish : CommonFishesEnums.values()) {
            if (fish.getSeason().equals(App.getCurrentGame().getCurrentSeason())) {
                availableFish.add(new Fish(fish.name()));
            }
        }
        if (!this.getJens().equals("Training") && skill == MAX_LEVEL) {
            for (LegendaryFishesEnums fish : LegendaryFishesEnums.values()) {
                if (fish.getSeason().equals(App.getCurrentGame().getCurrentSeason())) {
                    availableFish.add(new Fish(fish.name()));
                }
            }
        }

        if (skill == MAX_LEVEL) {
            if (player.getEnergy() >= getEnergyUsage() - 1) {
                if (isValidForFishing(tileCord)) {
                    StringBuilder result = new StringBuilder();
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    return Fishing(M, pole, rand, R, skill, fishCount, availableFish, result);
                } else {
                    return "No place for fishing found";
                }
            } else {
                return "Not enough enough energy to fish";
            }
        } else {
            if (player.getEnergy() >= getEnergyUsage()) {
                if (isValidForFishing(tileCord)) {
                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
                    StringBuilder result = new StringBuilder();
                    return Fishing(M, pole, rand, R, skill, fishCount, availableFish, result);
                } else {
                    return "No place for fishing found";
                }
            } else {
                return "Not enough enough energy to fish";
            }
        }
    }

    private String Fishing(double m, double pole, Random rand, double r, int skill, int fishCount, ArrayList<Fish> availableFish, StringBuilder result) {
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        for (int i = 0; i < fishCount; i++) {
            Fish caught = availableFish.get(rand.nextInt(availableFish.size()));
            double qualityScore = (r * (skill + 2) * pole) / (7 - m);

            Quality quality;
            if (qualityScore > 0.5) {
                quality = Quality.Normal;
                caught.setQuality(Quality.Normal);
                caught.setBasePrice(caught.getBasePrice() * 1);
            } else if (qualityScore > 0.5 && qualityScore < 0.7) {
                quality = Quality.Silver;
                caught.setQuality(Quality.Silver);
                caught.setBasePrice(caught.getBasePrice() * 1.25);
            } else if (qualityScore > 0.7 && qualityScore < 0.9) {
                quality = Quality.Gold;
                caught.setQuality(Quality.Gold);
                caught.setBasePrice(caught.getBasePrice() * 1.5);
            } else {
                quality = Quality.Iridium;
                caught.setQuality(Quality.Iridium);
                caught.setBasePrice(caught.getBasePrice() * 2);
            }
            result.append(caught.getName()).append(" with quality: ").append(quality).append("caught")
                    .append("\n");
            Inventory inventory = player.getInventory();
            inventory.addItem(caught, 1);

        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public int getPrice() {
        return switch (getJens()) {
            case "Training" -> 25;
            case "Bamboo" -> 500;
            case "Iridium" -> 1800;
            case "Fiberglass" -> 7500;
            default -> 0;
        };
    }

    public static boolean isValidForFishing(Cord cord) {
        Kashi kashi = App.getCurrentGame().getMap().get(cord.getX()).get(cord.getY());
        if (kashi.getInside() instanceof Lake) {
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
        return EnergyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this.EnergyUsage = energyUsage;
    }


    @Override
    public String getCorrectName() {
        return "fishingpole";
    }

    @Override
    public int getCorrectPrice() {
        return getPrice();
    }
}