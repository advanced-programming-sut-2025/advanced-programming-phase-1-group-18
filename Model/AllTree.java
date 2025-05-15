package Model;

import enums.AllCropsEnums;
import enums.AllTreesEnums;
import enums.MixedSeedsEnums;
import enums.TreeSeedEnums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllTree extends Tree {
    AllTreesEnums type;
    TreeSeedEnums source;
    private int daysGrowCounter;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private int FruitHarvestCycle;
    private boolean canBecomeGiant;
    private boolean fedThisDay;
    private boolean edible;
    private int Energy;
    private int baseSellPrice;


    public int getDaysGrowCounter() {
        return daysGrowCounter;
    }

    public void setDaysGrowCounter(int daysGrowCounter) {
        this.daysGrowCounter = daysGrowCounter;
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Integer> stages) {
        this.stages = stages;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public void setTotalHarvestTime(int totalHarvestTime) {
        this.totalHarvestTime = totalHarvestTime;
    }

    public int getFruitHarvestCycle() {
        return FruitHarvestCycle;
    }

    public void setFruitHarvestCycle(int fruitHarvestCycle) {
        FruitHarvestCycle = fruitHarvestCycle;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }

    public void setCanBecomeGiant(boolean canBecomeGiant) {
        this.canBecomeGiant = canBecomeGiant;
    }

    public boolean isFedThisDay() {
        return fedThisDay;
    }

    public void setFedThisDay(boolean fedThisDay) {
        this.fedThisDay = fedThisDay;
    }

    public boolean isEdible() {
        return edible;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        Energy = energy;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public void setBaseSellPrice(int baseSellPrice) {
        this.baseSellPrice = baseSellPrice;
    }

    public TreeSeedEnums getSource() {
        return source;
    }

    public void setSource(TreeSeedEnums source) {
        this.source = source;
    }

    public AllTreesEnums getType() {
        return type;
    }

    public void setType(AllTreesEnums type) {
        this.type = type;
    }

    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(false);
        kashi.setEnterance(false);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getAllTrees().add(this);
    }

    public void initilizeCrop(TreeSeedEnums treeSeedEnums) {
        this.setSource(treeSeedEnums);
        this.setDaysGrowCounter(0);
        this.setFedThisDay(false);
        switch (treeSeedEnums) {
            case ApricotSapling: {
                this.setType(AllTreesEnums.ApricotTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(59);
                this.setEnergy(38);
                this.setEdible(true);
                break;
            }
            case CherrySapling: {
                this.setType(AllTreesEnums.CherryTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(80);
                this.setEnergy(38);
                this.setEdible(true);
                break;
            }
            case BananaSapling: {
                this.setType(AllTreesEnums.BananaTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(150);
                this.setEnergy(75);
                this.setEdible(true);
                break;
            }
            case MangoSapling: {
                this.setType(AllTreesEnums.MangoTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(130);
                this.setEnergy(100);
                this.setEdible(true);
                break;
            }
            case OrangeSapling: {
                this.setType(AllTreesEnums.OrangeTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(100);
                this.setEnergy(38);
                this.setEdible(true);
                break;
            }
            case PeachSapling: {
                this.setType(AllTreesEnums.PeachTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(140);
                this.setEnergy(38);
                this.setEdible(true);
                break;
            }
            case AppleSapling: {
                this.setType(AllTreesEnums.AppleTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(100);
                this.setEnergy(38);
                this.setEdible(true);
                break;
            }
            case PomegranateSapling: {
                this.setType(AllTreesEnums.PomegranateTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(140);
                this.setEnergy(38);
                this.setEdible(true);
                break;
            }
            case Acorns: {
                this.setType(AllTreesEnums.OakTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(7);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(150);
                this.setEnergy(0); // Not edible
                this.setEdible(false);
                break;
            }
            case MapleSeeds: {
                this.setType(AllTreesEnums.MapleTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(9);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(200);
                this.setEnergy(0); // Not edible
                this.setEdible(false);
                break;
            }
            case PineCones: {
                this.setType(AllTreesEnums.PineTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(5);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(100);
                this.setEnergy(0); // Not edible
                this.setEdible(false);
                break;
            }
            case MahoganySeeds: {
                this.setType(AllTreesEnums.MahoganyTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(2);
                this.setEnergy(-2);
                this.setEdible(true);
                break;
            }
            case MushroomTreeSeeds: {
                this.setType(AllTreesEnums.MushroomTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(40);
                this.setEnergy(38);
                this.setEdible(true);
                break;
            }
            case MysticTreeSeeds: {
                this.setType(AllTreesEnums.MysticTree);
                this.setStages(new ArrayList<>(List.of(7, 7, 7, 7)));
                this.setTotalHarvestTime(28);
                this.setFruitHarvestCycle(7);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(1000);
                this.setEnergy(500);
                this.setEdible(true);
                break;
            }
        }
    }

}
